package com.isxxc.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * 短信服务
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
@RequestMapping("smsClient")
public interface SmsClient {

    /***
     * 获取注册验证码
     * @param mobileNo
     * @return
     */
    @RequestMapping(value = "registerVerifycode", method = RequestMethod.POST)
    Result registerVerifycode(@RequestParam("mobileNo") String mobileNo);

    /***
     * 获取登录验证码
     * @param mobileNo
     * @return
     */
    @RequestMapping(value = "loginVerifycode", method = RequestMethod.POST)
    Result loginVerifycode(@RequestParam("mobileNo") String mobileNo);

    /***
     * 获取登录验证码
     * @param mobileNo
     * @return
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    Result resetPassword(@RequestParam("mobileNo") String mobileNo);

    /***
     * 帐号绑定手机
     * @param mobileNo
     * @return
     */
    @RequestMapping(value = "bindMobile", method = RequestMethod.POST)
    Result bindMobile(@RequestParam("mobileNo") String mobileNo);
}
