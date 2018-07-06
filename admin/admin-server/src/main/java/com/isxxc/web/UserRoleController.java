package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.UserRoleDO;
import com.isxxc.service.feign.user.UserRoleFunctionRelationService;
import com.isxxc.service.feign.user.UserRoleService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员角色管理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserRoleFunctionRelationService userRoleFunctionRelationService;

    /***
     * 添加角色
     * @param userRoleDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserRoleDO userRoleDO) {
        if (StringUtils.isBlank(userRoleDO.getName())) {
            return ResponseResult.paramNotNull("角色名称不能为空");
        }
        if (StringUtils.isBlank(userRoleDO.getCode())) {
            return ResponseResult.paramNotNull("Code唯一标识不能为空");
        }
        return userRoleService.save(userRoleDO);
    }

    /***
     * 更新角色信息
     * @param userRoleDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(UserRoleDO userRoleDO) {
        if (userRoleDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(userRoleDO.getName())) {
            return ResponseResult.paramNotNull("角色名称不能为空");
        }
        if (StringUtils.isBlank(userRoleDO.getCode())) {
            return ResponseResult.paramNotNull("Code唯一标识不能为空");
        }
        return userRoleService.updateInfo(userRoleDO);
    }

    /***
     * 查询列表，带分页
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page<UserRoleDO> page) {
        return userRoleService.listPage(page);
    }

    /***
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping(value = "deleted", method = RequestMethod.POST)
    public Result deleted(Integer id) {
        return userRoleService.deleted(id);
    }

    /***
     * 添加及角色与菜单关联
     */
    @RequestMapping(value = "roleFunctionRelation", method = RequestMethod.POST)
    public Result roleFunctionRelation(Integer roleId, String functionIds) {
        if (roleId == null) {
            return ResponseResult.paramNotNull("角色ID不能为空");
        }
        if (StringUtils.isBlank(functionIds)) {
            return ResponseResult.paramNotNull("菜单ID不能为空");
        }
        return userRoleFunctionRelationService.roleFunctionRelation(roleId, functionIds);
    }




}
