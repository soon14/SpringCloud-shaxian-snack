package com.isxxc.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;


/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RequestMapping("/openUserClient")
public interface OpenUserClient {

    /***
     * 小吃铺授权登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "xiaoChiPuLogin", method = RequestMethod.GET)
    Result xiaoChiPuLogin(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("userAgent") String userAgent);

    /***
     * 小吃铺帐号绑定手机
     * @param openUserId
     * @param verifyCode
     * @param userAgent
     * @return
     */
    @RequestMapping(value = "xiaoChiPuBindMobile", method = RequestMethod.GET)
    Result xiaoChiPuBindMobile(@RequestParam("openUserId") Integer openUserId, @RequestParam("mobileNo") String mobileNo, @RequestParam("verifyCode") String verifyCode, @RequestParam("userAgent") String userAgent);
}
