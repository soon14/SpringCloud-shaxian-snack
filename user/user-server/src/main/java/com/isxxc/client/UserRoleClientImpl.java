package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.UserRoleDO;
import com.isxxc.service.UserRoleService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RestController
public class UserRoleClientImpl implements UserRoleClient {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Result save(@RequestBody UserRoleDO userRoleDO) {
        return userRoleService.save(userRoleDO);
    }

    @Override
    public Result updateInfo(@RequestBody UserRoleDO userRoleDO) {
        return userRoleService.updateInfo(userRoleDO);
    }

    @Override
    public Result listPage(@RequestBody Page<UserRoleDO> page) {
        return userRoleService.listPage(page);
    }

    @Override
    public Result deleted(Integer id) {
        return userRoleService.deleted(id);
    }
}
