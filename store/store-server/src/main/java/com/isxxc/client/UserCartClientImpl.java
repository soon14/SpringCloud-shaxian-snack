package com.isxxc.client;


import com.isxxc.domain.entity.UserCartDO;
import com.isxxc.service.UserCartService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 会员购物车 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class UserCartClientImpl implements UserCartClient {

    @Resource
    private UserCartService userCartService;

    @Override
    public Result list(Integer userId) {
        return userCartService.list(userId);
    }

    @Override
    public Result updateNum(Integer userId, Integer id, Integer num) {
        return userCartService.updateNum(userId, id, num);
    }

    @Override
    public Result deleteById(Integer userId, Integer id) {
        return userCartService.deleteByIdAndUserId(userId, id);
    }

    @Override
    public Result deleteByIds(Integer userId, String ids) {
        return userCartService.deleteByIds(userId, ids);
    }

    @Override
    public Result save(@RequestBody UserCartDO userCartDO) {
        return userCartService.save(userCartDO);
    }
}
