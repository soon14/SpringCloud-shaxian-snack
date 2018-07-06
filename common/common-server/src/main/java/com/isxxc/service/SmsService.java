package com.isxxc.service;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2018/1/8
 */
public interface SmsService {
    /***
     * 获取注册验证码
     * @param mobileNo
     * @return
     */
    Result registerVerifycode(String mobileNo);

    /***
     * 获取登录验证码
     * @param mobileNo
     * @return
     */
    Result loginVerifycode(String mobileNo);

    /***
     * 获取登录验证码
     * @param mobileNo
     * @return
     */
    Result resetPassword(String mobileNo);

    /***
     * 手机绑定
     * @param mobileNo
     * @return
     */
    Result bindMobile(String mobileNo);
}
