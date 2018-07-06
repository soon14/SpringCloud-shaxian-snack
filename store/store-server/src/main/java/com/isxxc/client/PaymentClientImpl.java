package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.PaymentService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 支付 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class PaymentClientImpl implements PaymentClient {

    @Resource
    private PaymentService paymentService;


    @Override
    public Result generateWxPayByOrderNo(String clientIp, String orderNo) {
        return paymentService.generateWxPayByOrderNo(clientIp, orderNo);
    }

    @Override
    public Result generateWxPayByMergerNo(String clientIp, String mergerNo) {
        return paymentService.generateWxPayByMergerNo(clientIp, mergerNo);
    }

    @Override
    public String wxQRPayResult(@RequestParam Map<String, String> paramsMap) {
        return paymentService.wxQRPayResult(paramsMap);
    }


    @Override
    public Result skipAlipayPayment(String orderNo) throws Exception{
        return paymentService.skipAlipayPayment(orderNo);
    }


    @Override
    public Result notifyAlipayPayment(@RequestBody Pager pager) throws Exception{
        return paymentService.notifyAlipayPayment(pager);
    }
}
