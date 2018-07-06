package com.isxxc.web;


import com.isxxc.service.feign.SmsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.ValidatorUtil;

/**
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    /***
     * 获取注册验证码
     */
    @RequestMapping(value = "registerVerifycode", method = RequestMethod.POST)
    public Result registerVerifycode(String mobileNo) {
        if (StringUtils.isBlank(mobileNo)) {
            return ResponseResult.paramNotNull("手机号不能为空");
        }
        if (!ValidatorUtil.isMobile(mobileNo)) {
            return ResponseResult.paramNotNull("手机号格式不正确");
        }
        return smsService.registerVerifycode(mobileNo);
    }

    /***
     * 获取登录验证码
     */
    @RequestMapping(value = "loginVerifycode", method = RequestMethod.POST)
    public Result loginVerifycode(String mobileNo) {
        if (StringUtils.isBlank(mobileNo)) {
            return ResponseResult.paramNotNull("手机号不能为空");
        }
        if (!ValidatorUtil.isMobile(mobileNo)) {
            return ResponseResult.paramNotNull("手机号格式不正确");
        }
        return smsService.loginVerifycode(mobileNo);
    }


    /***
     * 重置密码验证码
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public Result resetPassword(String mobileNo) {
        if (StringUtils.isBlank(mobileNo)) {
            return ResponseResult.paramNotNull("手机号不能为空");
        }
        if (!ValidatorUtil.isMobile(mobileNo)) {
            return ResponseResult.paramNotNull("手机号格式不正确");
        }
        return smsService.resetPassword(mobileNo);
    }

    /***
     * 帐号绑定验证码
     */
    @RequestMapping(value = "bindMobile", method = RequestMethod.POST)
    public Result bindMobile(String mobileNo) {
        if (StringUtils.isBlank(mobileNo)) {
            return ResponseResult.paramNotNull("手机号不能为空");
        }
        if (!ValidatorUtil.isMobile(mobileNo)) {
            return ResponseResult.paramNotNull("手机号格式不正确");
        }
        return smsService.bindMobile(mobileNo);
    }

}
