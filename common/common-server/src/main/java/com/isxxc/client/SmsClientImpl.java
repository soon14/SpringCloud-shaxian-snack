package com.isxxc.client;

import com.isxxc.service.SmsService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * 省市区管理
 *
 * @author 泥水佬
 * @date 2018/1/8
 */
@RestController
public class SmsClientImpl implements SmsClient {

    @Resource
    private SmsService smsService;


    @Override
    public Result registerVerifycode(String mobileNo) {
        return smsService.registerVerifycode(mobileNo);
    }

    @Override
    public Result loginVerifycode(String mobileNo) {
        return smsService.loginVerifycode(mobileNo);
    }

    @Override
    public Result resetPassword(String mobileNo) {
        return smsService.resetPassword(mobileNo);
    }

    @Override
    public Result bindMobile(String mobileNo) {
        return smsService.bindMobile(mobileNo);
    }
}
