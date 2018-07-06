package com.isxxc.service;

import cc.likq.result.Result;

/**
 * 会员授权服务
 *
 * @author 泥水佬
 * @date 2018/1/13
 */
public interface UserAuthService {
    /***
     * 授权登录
     * @param authAccount
     * @param authPassword
     * @param account
     * @param password
     * @return
     */
    Result authLogin(String authAccount, String authPassword, String account, String password);
}
