package com.isxxc.client;

import com.isxxc.service.UserRoleFunctionRelationService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;
import feign.Param;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RestController
public class UserRoleFunctionClientImpl implements UserRoleFunctionRelationClient {

    @Resource
    private UserRoleFunctionRelationService userRoleFunctionRelationService;

    @Override
    public Result roleFunctionRelation(@Param("roleId") Integer roleId, @Param("functionIds") String functionIds) {
        return userRoleFunctionRelationService.roleFunctionRelation(roleId, functionIds);
    }
}
