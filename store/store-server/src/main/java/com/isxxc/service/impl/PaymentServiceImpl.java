package com.isxxc.service.impl;

import cc.likq.util.AliPayCommonUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.isxxc.constant.*;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.OrderInfoDO;
import com.isxxc.domain.entity.OrderPayMergerDO;
import com.isxxc.service.OrderInfoService;
import com.isxxc.service.OrderPayMergerService;
import com.isxxc.service.PaymentService;
import com.isxxc.service.StoreAmountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.PayCommonUtil;
import cc.likq.util.WeiXinPayCommonUtils;

/**
 * <p>
 * 支付信息 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    private final static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);


    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderPayMergerService orderPayMergerService;

    @Resource
    private StoreAmountService storeAmountService;

    @Override
    public Result generateWxPayByMergerNo(String clientIp, String mergerNo) {
        EntityWrapper<OrderPayMergerDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("merger_no", mergerNo);
        OrderPayMergerDO orderPayMergerDO = orderPayMergerService.selectOne(entityWrapper);
        if (orderPayMergerDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "合并ID错误");
        }
        //拆分订单号
        List<String> orderNoList = Arrays.stream(orderPayMergerDO.getOrderIds().split(",")).map(String::trim).distinct().collect(Collectors.toList());
        //查询所有订单信息
        List<OrderInfoDO> orderInfoDOList = orderInfoService.selectByOrderNoList(orderNoList);
        //所有订单价格
        Long actualPayPrice = 0L;
        for (OrderInfoDO orderInfoDO : orderInfoDOList) {
            if (orderInfoDO.getPaymentState() != OrderStateEnum.PaymentState.UNPAY.code) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "合并订单中,已包含已支付或已关闭的订单,订单号: " + orderInfoDO.getOrderNo());
            }
            actualPayPrice += orderInfoDO.getActualPrice();
        }
        if (actualPayPrice <= 0) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单价格有误,请联系商家");
        }
        String description = "合并支付订单:" + orderNoList.toString();
        //生成预支付信息
        Map<String, String> payInfoMap = WeiXinPayCommonUtils.generateQRcodePayment(mergerNo, description, clientIp, actualPayPrice, WeixinConstant.getNotifyUrl(), "1");
        if ("SUCCESS".equals(payInfoMap.get("returnCode"))) {
            return ResponseResult.success(payInfoMap);
        } else {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "预支付信息生成失败");
        }
    }

    @Override
    public Result generateWxPayByOrderNo(String clientIp, String orderNo) {
        EntityWrapper<OrderInfoDO> orderInfoDOEntityWrapper = new EntityWrapper<>();
        orderInfoDOEntityWrapper.eq("order_no", orderNo);
        OrderInfoDO orderInfoDO = orderInfoService.selectOne(orderInfoDOEntityWrapper);
        if (orderInfoDO == null) {
            return ResponseResult.paramNotNull("订单不存在");
        }
        if (orderInfoDO.getPaymentState() != OrderStateEnum.PaymentState.UNPAY.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单: " + orderInfoDO.getOrderNo() + " ,已支付或已关闭");
        }
        //生成预支付信息
        Map<String, String> payInfoMap = WeiXinPayCommonUtils.generateQRcodePayment(orderInfoDO.getOrderNo(), "购物订单:" + orderInfoDO.getOrderNo(), clientIp, orderInfoDO.getActualPrice(), WeixinConstant.getNotifyUrl(), "0");
        if ("SUCCESS".equals(payInfoMap.get("returnCode"))) {
            return ResponseResult.success(payInfoMap);
        } else {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "预支付信息生成失败");
        }
    }

    @Override
    public String wxQRPayResult(Map<String, String> paramsMap) {
        SortedMap<String, String> requestParams = PayCommonUtil.doXMLParse(paramsMap.get("resultXml"));
        String resXml;
        if (WeiXinPayCommonUtils.verifySign(requestParams)) {
            Integer payType = Integer.parseInt(requestParams.get("attach"));
            //单个订单支付
            if (payType == 0) {
                String orderNo = requestParams.get("out_trade_no");
                EntityWrapper<OrderInfoDO> orderInfoDOEntityWrapper = new EntityWrapper<>();
                orderInfoDOEntityWrapper.eq("order_no", orderNo);
                OrderInfoDO orderInfoDO = orderInfoService.selectOne(orderInfoDOEntityWrapper);
                updateOrderPayInfo(orderInfoDO, requestParams.get("transaction_id"));
                logger.debug("***********************微信支付,订单支付状态更新成功! " + requestParams.get("out_trade_no") + "**********************");
                resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
            } else if (payType == 1) {
                //合并支付
                String mergerNo = requestParams.get("out_trade_no");
                EntityWrapper<OrderPayMergerDO> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("merger_no", mergerNo);
                OrderPayMergerDO orderPayMergerDO = orderPayMergerService.selectOne(entityWrapper);
                //拆分订单号
                List<String> orderNoList = Arrays.stream(orderPayMergerDO.getOrderIds().split(",")).map(String::trim).distinct().collect(Collectors.toList());
                //查询所有订单信息,并更新订单信息
                List<OrderInfoDO> orderInfoDOList = orderInfoService.selectByOrderNoList(orderNoList);
                orderInfoDOList.forEach(orderInfoDO -> {
                    if (orderInfoDO.getMasterState().equals(OrderStateEnum.MasterState.PAYMENT_STATE.code) && orderInfoDO.getPaymentState().equals(OrderStateEnum.PaymentState.UNPAY.code)) {
                        updateOrderPayInfo(orderInfoDO, requestParams.get("transaction_id"));
                    }
                });
                orderPayMergerDO.setState(OrderStateEnum.MergerPayState.HAVE_PAY.code);
                orderPayMergerService.updateById(orderPayMergerDO);
                logger.debug("***********************微信支付,订单支付状态更新成功! " + requestParams.get("out_trade_no") + "**********************");
                resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
            } else {
                logger.debug("***********************微信支付,订单支付状态更新失败! 支付类型错误" + requestParams.get("out_trade_no") + "**********************");
                resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
            }
        } else {
            logger.debug("***********************微信支付,订单支付状态更新失败! 校验签名失败" + requestParams.get("out_trade_no") + "**********************");
            resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
        }
        return resXml;
    }

    private void updateOrderPayInfo(OrderInfoDO orderInfoDO, String paymentNo) {
        if (orderInfoDO.getMasterState().equals(OrderStateEnum.MasterState.PAYMENT_STATE.code) && orderInfoDO.getPaymentState().equals(OrderStateEnum.PaymentState.UNPAY.code)) {
            orderInfoDO.setPaymentNo(paymentNo);
            orderInfoDO.setPaymentType(PaymentEnum.PayType.WEI_XIN.code);
            orderInfoDO.setPaymentTime(new Date());
            orderInfoDO.setPaymentState(OrderStateEnum.PaymentState.HAVE_PAY.code);
            orderInfoDO.setDeliverState(OrderStateEnum.DeliverState.WAIT_DELIVERY.code);
            orderInfoDO.setMasterState(OrderStateEnum.MasterState.DELIVER_STATE.code);
            orderInfoService.updateById(orderInfoDO);
            //订单入帐
            storeAmountService.updateAmount(orderInfoDO.getStoreId(), orderInfoDO.getActualPrice(), orderInfoDO.getOrderNo(), StoreAmountEnum.Type.IN_AMOUNT);
        }
    }


    /**
     * 跳转支付宝支付
     * @param orderNo
     * @return
     * @throws Exception
     */
    @Override
    public Result skipAlipayPayment(String orderNo) throws Exception {

        Long amountPrice = 0L;
        //TODO 通过orderNo查询出订单金额
        //orderNo可能是合并的订单号也可能就是订单号，所以要先进行判断;
        EntityWrapper<OrderPayMergerDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("merger_no", orderNo);
        OrderPayMergerDO orderPayMergerDO = orderPayMergerService.selectOne(entityWrapper);
        //如果为空则说明不是合并的订单，那么就orderNo就是订单号
        if (orderPayMergerDO == null) {
            //通过orderNo查询出订单的详情
            EntityWrapper<OrderInfoDO> orderInfoDOEntityWrapper = new EntityWrapper<>();
            orderInfoDOEntityWrapper.eq("order_no", orderNo);
            OrderInfoDO orderInfoDO = orderInfoService.selectOne(orderInfoDOEntityWrapper);
            if (orderInfoDO == null) {
                return ResponseResult.paramNotNull("订单不存在");
            }
            if (orderInfoDO.getPaymentState() != OrderStateEnum.PaymentState.UNPAY.code) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单: " + orderInfoDO.getOrderNo() + " ,已支付或已关闭");
            }
            amountPrice = orderInfoDO.getActualPrice();
        } else {
            List<String> list = Arrays.asList(orderPayMergerDO.getOrderIds().split(","));
            //查询所有订单信息
            List<OrderInfoDO> orderInfoDOList = orderInfoService.selectByOrderNoList(list);
            if (orderInfoDOList == null || orderInfoDOList.size() == 0) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "合并ID错误");
            }
            for (OrderInfoDO orderInfoDO : orderInfoDOList) {
                amountPrice += orderInfoDO.getActualPrice();
            }
        }

        //判断订单金额是否正确
        if (amountPrice <= 0) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单价格有误,请联系商家");
        }

        String result = AliPayCommonUtils.skipAlipayPayment(orderNo, amountPrice);
        return ResponseResult.success(result);
    }


    /**
     * 支付宝异步通知支付结果
     *
     * @param pager
     * @return
     * @throws Exception
     */
    @Override
    public Result notifyAlipayPayment(Pager pager) throws Exception {
        Result result = AliPayCommonUtils.notifyAlipayPayment(pager);
        Map<String, Object> map = pager.getParamMap();
        logger.error(map.toString());

        if (result.getCode() == 200 && map.get("refund_fee") != null && "TRADE_SUCCESS".equals(map.get("trade_status"))) {
            //TODO 部分退款才有这个参数，此处写部分退款逻辑
            logger.info("********部分退款成功,此次退款金额={},退款单号={}***********",map.get("refund_fee"),map.get("out_biz_no"));

        }

        if (result.getCode() == 200 && map.get("refund_fee") == null && "TRADE_SUCCESS".equals(map.get("trade_status"))) {
            //TODO 支付成功逻辑
            //TODO 如果支付成功，则需要进行相应的修改
            logger.info("*******订单支付成功，商家订单号={},支付金额={}*******", map.get("out_trade_no"), map.get("total_amount"));



        } else if (result.getCode() == 200 && map.get("refund_fee") == null && "TRADE_FINISHED".equals(map.get("trade_status"))) {
            //TODO 交易关闭逻辑
            logger.info("*******订单交易关闭，关闭订单号={},交易金额={}*******", map.get("out_trade_no"), map.get("total_amount"));


        }


        return result;
    }

}
