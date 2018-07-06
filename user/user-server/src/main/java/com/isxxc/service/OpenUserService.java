package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.OpenUserDO;

import cc.likq.result.Result;

/**
 * <p>
 * 第三方登录信息 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-22
 */
public interface OpenUserService extends IService<OpenUserDO> {

    /***
     * 小吃铺授权登录
     * @param account
     * @param password
     * @return
     */
    Result xiaoChiPuLogin(String account, String password, String userAgent);

    /***
     * 小吃铺绑定手机
     * @param openUserId
     * @param verifyCode
     * @param userAgent
     * @return
     */
    Result xiaoChiPuBindMobile(Integer openUserId, String mobileNo, String verifyCode, String userAgent);
}
