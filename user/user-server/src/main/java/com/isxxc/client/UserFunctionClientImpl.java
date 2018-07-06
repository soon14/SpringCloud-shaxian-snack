package com.isxxc.client;

import com.isxxc.domain.entity.UserFunctionDO;
import com.isxxc.service.UserFunctionService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2017/12/23
 */
@RestController
public class UserFunctionClientImpl implements UserFunctionClient {

    @Resource
    private UserFunctionService userFunctionService;

    @Override
    public Result save(@RequestBody UserFunctionDO userFunctionDO) {
        return userFunctionService.save(userFunctionDO);
    }

    @Override
    public Result functionTree() {
        return userFunctionService.functionTree();
    }

    @Override
    public Result updateInfo(@RequestBody UserFunctionDO userFunctionDO) {
        return userFunctionService.updateInfo(userFunctionDO);
    }
}
