package com.isxxc.web;


import com.isxxc.domain.entity.UserFunctionDO;
import com.isxxc.service.feign.user.UserFunctionService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员菜单管理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-21
 */
@RestController
@RequestMapping("/userFunction")
public class UserFunctionController {

    @Resource
    private UserFunctionService userFunctionService;

    /***
     * 添加会员菜单
     * @param userFunctionDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserFunctionDO userFunctionDO) {
        if (userFunctionDO.getType() == null) {
            return ResponseResult.paramNotNull("类型不能为空");
        }
        if (userFunctionDO.getParentId() == null) {
            return ResponseResult.paramNotNull("父功能菜单ID不能为空");
        }
        if (StringUtils.isBlank(userFunctionDO.getName())) {
            return ResponseResult.paramNotNull("菜单名称不能为空");
        }
        if (StringUtils.isBlank(userFunctionDO.getCode())) {
            return ResponseResult.paramNotNull("功能唯一标识不能为空");
        }
        if (userFunctionDO.getLevel() == null) {
            return ResponseResult.paramNotNull("层级不能为空");
        }
        if (StringUtils.isBlank(userFunctionDO.getFunctionUrl())) {
            return ResponseResult.paramNotNull("功能菜单URL不能为空");
        }
        return userFunctionService.save(userFunctionDO);
    }

    /***
     * 更新会员菜单
     * @param userFunctionDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(UserFunctionDO userFunctionDO) {
        if (userFunctionDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (userFunctionDO.getType() == null) {
            return ResponseResult.paramNotNull("类型不能为空");
        }
        if (userFunctionDO.getParentId() == null) {
            return ResponseResult.paramNotNull("父功能菜单ID不能为空");
        }
        if (StringUtils.isBlank(userFunctionDO.getName())) {
            return ResponseResult.paramNotNull("菜单名称不能为空");
        }
        if (StringUtils.isBlank(userFunctionDO.getCode())) {
            return ResponseResult.paramNotNull("功能唯一标识不能为空");
        }
        if (userFunctionDO.getLevel() == null) {
            return ResponseResult.paramNotNull("层级不能为空");
        }
        if (StringUtils.isBlank(userFunctionDO.getFunctionUrl())) {
            return ResponseResult.paramNotNull("功能菜单URL不能为空");
        }
        return userFunctionService.updateInfo(userFunctionDO);
    }

    /***
     * 查询所有菜单，树结构
     * @return
     */
    @RequestMapping(value = "functionTree", method = RequestMethod.GET)
    public Result functionTree() {
        return userFunctionService.functionTree();
    }
}
