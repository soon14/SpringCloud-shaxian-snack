package com.isxxc.web;


import cc.likq.result.ResultCodeEnum;
import cc.likq.util.JxlsExcelView;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductBaseInfoDTO;
import com.isxxc.service.feign.store.PaymentService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.NetworkUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 支付 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@RestController
    @RequestMapping("/payment")
public class PaymentController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService paymentService;

    /***
     * 根据订单号生成微信预支付信息
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "generateWxPayByOrderNo", method = RequestMethod.GET)
    public Result generateWxPayByOrderNo(HttpServletRequest request, String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        String clientIp = NetworkUtils.getIpAddress(request);
        return paymentService.generateWxPayByOrderNo(clientIp, orderNo);
    }

    /***
     * 根据合并号生成微信预支付信息
     * @param mergerNo
     * @return
     */
    @RequestMapping(value = "generateWxPayByMergerNo", method = RequestMethod.GET)
    public Result generateWxPayByMergerNo(HttpServletRequest request, String mergerNo) {
        if (StringUtils.isBlank(mergerNo)) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        String clientIp = NetworkUtils.getIpAddress(request);
        return paymentService.generateWxPayByMergerNo(clientIp, mergerNo);
    }

    /**
     * 微信二维码支付回调
     */
    @RequestMapping(value = "wxQRPayResult")
    public void wxQRPayResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("***********************微信支付回调开始**********************");
        String resultXml = "";
        String inputLine;
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                resultXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("微信回调信息: " + resultXml);

        // 支付结果
        Map<String, String> paramsMap = new HashMap<>(1);
        paramsMap.put("resultXml", resultXml);
        String resXml = paymentService.wxQRPayResult(paramsMap);
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
        System.out.println("系统响应信息: " + resXml);
        System.out.println("***********************微信支付回调结束**********************");
    }



    /**
     * 支付宝支付
     */
    @RequestMapping(value = "skipAlipayPayment", method = RequestMethod.GET)
    public Result skipAlipayPayment(String orderNo){
        try {
            if (StringUtils.isBlank(orderNo)) {
                return ResponseResult.paramNotNull("订单号不能为空");
            }
            //跳转支付宝支付页面处理
            Result result = paymentService.skipAlipayPayment(orderNo);
            //如果返回结果为空或者状态码不是200
            if (result == null || result.getCode() != 200) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, result.getMsg());
            }
            return ResponseResult.success(result.getData());
        } catch ( Exception e ) {
            logger.error( e.toString() , e );
            System.out.println(e.toString());
            return ResponseResult.failResult(ResultCodeEnum.NO_FOUND, "跳转支付宝失败");
        }
    }

    /**
     * 支付宝异步回调地址
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/notify_url.pay", method = RequestMethod.POST)
    public void notifyAlipayPayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //判断请求的requesthe response不为空
        Map<String,String[]> map = request.getParameterMap();
        Map<String,Object> params = new HashMap<>();
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String name = (String)iterator.next();
            String[] values = (String[]) map.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            /*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
            params.put(name, valueStr);
        }
        logger.error(params.toString());
        PrintWriter writer = response.getWriter();
        if (params != null && params.size() > 0) {
            /**
             * 处理结果
             */
            Pager pager = new Pager();
            pager.setParamMap(params);
            Result result = paymentService.notifyAlipayPayment(pager);
            if (result != null && 200 == result.getCode()) {
                logger.info("**************回调成功，订单支付状态为已支付****************");
                writer.print("success");
            } else {
                logger.info("**************回调成功，订单支付未支付成功,原因：" + result.getMsg());
                writer.print("fail");
            }
        } else {
            writer.println("fail");
        }
        writer.close();
    }

    @RequestMapping(value = "/export")
    public ModelAndView export (){
        Map<String, Object> map = new HashMap<>();
        List<ProductBaseInfoDTO> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ProductBaseInfoDTO productBaseInfoDTO = new ProductBaseInfoDTO();
            productBaseInfoDTO.setSpuId(i);
            productBaseInfoDTO.setTitle(String.valueOf(i * 2));
            productBaseInfoDTO.setPrice(Long.valueOf(i * 3));
            productBaseInfoDTO.setSalesVolume(i * 4);
            list.add(productBaseInfoDTO);
        }
        map.put("lists", list);
        return new ModelAndView(new JxlsExcelView("template/fillingLineExport.xlsx", "excel"), map);
    }
}
