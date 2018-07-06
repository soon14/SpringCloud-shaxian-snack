package com.isxxc.web;

import com.isxxc.service.feign.UserAuthService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * 用户授权
 *
 * @author 泥水佬
 * @date 2018/1/13
 */
@RestController
@RequestMapping("/userAuth")
public class UserAuthController {

    @Resource
    private UserAuthService userAuthService;

    @RequestMapping(value = "authLogin", method = RequestMethod.GET)
    public Result authLogin(String authAccount, String authPassword, String account, String password) {
        if (StringUtils.isBlank(authAccount)) {
            ResponseResult.paramNotNull("授权帐号不能为空");
        }
        if (StringUtils.isBlank(authPassword)) {
            ResponseResult.paramNotNull("授权密码不能为空");
        }
        if (StringUtils.isBlank(account)) {
            ResponseResult.paramNotNull("会员帐号不能为空");
        }
        if (StringUtils.isBlank(password)) {
            ResponseResult.paramNotNull("会员密码不能为空");
        }
        return userAuthService.authLogin(authAccount, authPassword, account, password);
    }
}
