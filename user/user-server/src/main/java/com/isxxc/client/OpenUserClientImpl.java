package com.isxxc.client;

import com.isxxc.service.OpenUserService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RestController
public class OpenUserClientImpl implements OpenUserClient {

    @Resource
    private OpenUserService openUserService;


    @Override
    public Result xiaoChiPuLogin(String account, String password, String userAgent) {
        return openUserService.xiaoChiPuLogin(account, password, userAgent);
    }

    @Override
    public Result xiaoChiPuBindMobile(Integer openUserId, String mobileNo, String verifyCode, String userAgent) {
        return openUserService.xiaoChiPuBindMobile(openUserId, mobileNo, verifyCode, userAgent);
    }
}
