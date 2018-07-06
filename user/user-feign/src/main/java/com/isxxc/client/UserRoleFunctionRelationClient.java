package com.isxxc.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * 角色与功能菜单关联
 *
 * @author 泥水佬
 * @date 2017/12/22
 */
@RequestMapping("/userRoleFunctionRelationClient")
public interface UserRoleFunctionRelationClient {

    /***
     * 角色菜单关联
     * @param roleId 角色ID
     * @param functionIds 功能菜单ID,多个以逗号分隔
     * @return
     */
    @RequestMapping(value = "roleFunctionRelation", method = RequestMethod.POST)
    Result roleFunctionRelation(@RequestParam("roleId") Integer roleId, @RequestParam("functionIds") String functionIds);
}
