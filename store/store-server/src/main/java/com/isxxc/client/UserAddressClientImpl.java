package com.isxxc.client;


import com.isxxc.domain.entity.UserAddressDO;
import com.isxxc.service.UserAddressService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 会员收货地址 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class UserAddressClientImpl implements UserAddressClient {

    @Resource
    private UserAddressService userAddressService;

    @Override
    public Result save(@RequestBody UserAddressDO userAddressDO) {
        return userAddressService.save(userAddressDO);
    }

    @Override
    public Result updateInfo(@RequestBody UserAddressDO userAddressDO) {
        return userAddressService.updateInfo(userAddressDO);
    }

    @Override
    public Result list(Integer userId) {
        return userAddressService.list(userId);
    }

    @Override
    public Result getInfoByDefault(Integer userId) {
        return userAddressService.getInfoByDefault(userId);
    }

    @Override
    public Result deleteByUserId(Integer userId, Integer id) {
        return userAddressService.deleteByUserId(userId, id);
    }
}
