package com.isxxc.web;


import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.isxxc.domain.dto.*;
import com.isxxc.service.feign.store.OrderRefundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单退款 前端控制器
 * </p>
 *
 * @author mc
 * @since 2018-05-11
 */
@RestController
@RequestMapping("/orderRefund")
public class OrderRefundController {

    @Resource
    private OrderRefundService orderRefundService;

    /***
     * 客户申请退款
     * @param
     * @return
     */
    @RequestMapping(value = "consumerApplyRefund", method = RequestMethod.POST)
    public Result consumerApplyRefund(@RequestBody  OrderRefundListDTO orderRefundListDTO) {
        if (orderRefundListDTO == null) {
            return ResponseResult.paramNotNull("退款订单信息不能为空");
        }
        if (orderRefundListDTO.getListorderRefundDTO().size() < 1) {
            return ResponseResult.paramNotNull("没有传入订单信息");
        }

        return orderRefundService.consumerApplyRefund(orderRefundListDTO);
    }

    /***
     * 退款信息查询 dao层分客户和商家列表查询
     * @param pager
     * @param pager
     * @return
     */
    @RequestMapping(value = "consumerRefundPagelist", method = RequestMethod.POST)
    public Result consumerRefundPagelist(@RequestBody Pager pager) {
        //首先将userId转为字符串，因为refund中没有把userid放进去，只用filed1扩展字段存放userid，但是数据类型是String

        return orderRefundService.consumerRefundPagelist(pager);
    }


    /***
     * 商家第一次审核退款
     * @param
     * @return
     */
    @RequestMapping(value = "sellerFirstAuditRefund", method = RequestMethod.POST)
    public Result sellerFirstAuditRefund(@RequestBody  OrderRefundDTO orderRefundDTO) {
        //商家第一次审核步骤
        //1，前端传入只能是每次一个订单，这个订单不是支付订单，而是我们系统的订单
        //2,每次订单信息传入后，会有商家补充的字段信息。商家前端会有三个选择，1，该订单全部退款，2，扣除邮费退款，3，自定义金额退款
        //****不支持一个订单多次退款，不支持选择单个商品退款*******
        if (orderRefundDTO == null) {
            return ResponseResult.paramNotNull("退款订单信息不能为空");
        }
        return orderRefundService.sellerFirstAuditRefund(orderRefundDTO);
    }


    /**
     * 微信退款回调
     */
    @RequestMapping(value = "wxRefundResult")
    public Result wxQRPayResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        System.out.println("微信退款回调信息: " + resultXml);

//        resultXml = "<xml><return_code>SUCCESS</return_code><appid><![CDATA[wx1a6f17457ba47405]]></appid><mch_id><![CDATA[1495022012]]></mch_id><nonce_str><![CDATA[51fb35c4e52b315d107964dee6a04ff3]]></nonce_str><req_info><![CDATA[g9rhHiX7OILGOIlGXO5LOUV/HyWY7WheRzcth8w1GH6AgGsc/7xAtbBstt9T14cepwR3op5eUEwkXSp1SyVVn7yzDotwOfgCfEleO4R/8IOM5AR+g6jbSV0K8EeLJG1HZZu+yeG+NKCyjrgYG/nr6xqPie1Dwq2RIIZ9VKIZm+ffwlP2UOlv/QD3psSPSBUmqQvuXq/OMByFomXe5bE2HD037EKBuHTXQXpptHda/AM/pB2JY3Qw976yqub6fhJ3rvagAwt44WWaUhHmoexS/d/p2SJmwQzoRFMXmmDIJjFctG5zemfS9+GtpfjVC3n+DbbPWl0f9VzEp8nFwkH+Kh6hzJcIIjiwsjL0NfFM0SoQEPRrQP75GHTSMxdHRclaOZGnhLlvxCgYoLvu84arBQDXQ9yiduiVXTuYXZlGni6j0xZIe8bVsIrbYyaud8T8m1vjZK5+cwSxKTK6jIzn0H6Etbvge5tnggi/Jyhewb2jKFegMdLleXsv4j7frZz0s9AtxxDdPbv0OQEAeS49KK4KhPfZRHVU77uwhMAel4GeVF+8scVRCYm23JKRBJaESRXdtUqXZKFcVTo3KEjt7GnKMsilzP6ZvwWgu/rql2oqYByjb8Gfg5Mhl9uAczXatWqtNiwcSpjPb20d7Kc3f/O0Drcw4cyp2mbkHoM30iFvEt5RmLxBCBqLZ09UQV1wM+HtBVyrKAydPH36t+to9LNUSH3SJl44dQPyp6gmrhX83/86OFZdcTb/lvHppmlL7NMoz6Ib6nn20vHSc7aQJsrUCDzi8+awYtwdoj0XAoRLGK/" +
//                "AP0gIgWplNOWYINWxadlZHDlKfAHfsh4FQF6kcgxdL8OHpCfjkR1FB+t4mv12zVlRZUG03fG1QVy5Mu11xHQmMzhX7W0a9mGVxlqRj2mVoFh1BycNc1tlB/x94DrlF4qy3pIeYA+YnpBuZDtrOmVAR+Vo+F2hZ8hkGmPum0h792WCd82TqQbFRxVCcE/zbDD8fbG2wnpYRWq56DrBUKqZ6bkmJOjMEVoIgaTAqTk2rD70D36VWmk0p+grEuY03o9zBcHXT4my10MyU3y6]]></req_info></xml>";

        // 支付结果
        Map<String, String> paramsMap = new HashMap<>(1);
        paramsMap.put("resultXml", resultXml);
        Result resXml = orderRefundService.wxRefundResult(paramsMap);
        String resstr = resXml.getData().toString();
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resstr.getBytes());
        out.flush();
        out.close();
        System.out.println("系统响应信息: " + resXml);
        System.out.println("***********************微信退款回调结束**********************");
        return resXml;
    }



    /***
     * 测试退款回调
     * @param id
     * @return
     */
    @RequestMapping(value = "payRefund", method = RequestMethod.POST)
    public Result payRefund(@RequestParam("id") Integer id, @RequestParam("userId") Integer userId,@RequestParam("returnPerson") String returnPerson) {
        //开始校验（主要校验后台是否能够退款，并返回原因）


        //判断是单个单据支付，还是整个多个单据支付

        //如果是单个单据支付，首先检查是部分退款还是全部退款，新增一个实体属性，由前端传入，判断是全退，不退邮费，还是自定义退款，自定义退款金额需要做限制，不能大于还能退款金额
        //退款之后，需要做两个地方修改，一个是修改订单，将订单信息改为已经退款，不让再次退款。一个是商家金额表的数据修改和记录，将退款金额减掉


        //如果是多个单据支付，也分三种情况退款，和单个订单退款的区别在于，修改订单的时候和商家金额的时候需要根据order_pay_merger表得到支付时提交的合并订单号
        //其他操作全部需要，具体到每个订单信息，需要做遍历


        Pager pager = new Pager();
        pager.putParam("id",id);
        pager.putParam("userId",userId);
        pager.putParam("returnPerson",returnPerson);
        return orderRefundService.payrefund(pager);

    }


}