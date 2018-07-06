package com.isxxc.client;

import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.service.UserRoleRelationService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RestController
public class UserRoleRelationClientImpl implements UserRoleRelationClient {

    @Resource
    private UserRoleRelationService userRoleRelationService;

    @Override
    public Result userRoleRelation(Integer userId, String roleCodes) {
        return userRoleRelationService.userRoleRelation(userId, roleCodes);
    }

    @Override
    public Result save(@RequestBody UserRoleRelationDO userRoleRelationDO) {
        return userRoleRelationService.insert(userRoleRelationDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
