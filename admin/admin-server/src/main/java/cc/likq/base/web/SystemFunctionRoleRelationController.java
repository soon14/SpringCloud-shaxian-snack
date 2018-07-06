package cc.likq.base.web;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.base.service.SystemFunctionRoleRelationService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/systemFunctionRoleRelation")
public class SystemFunctionRoleRelationController {

    @Resource
    private SystemFunctionRoleRelationService systemFunctionRoleRelationService;

    /***
     * 角色与菜单关系
     */
    @RequestMapping(value = "roleFunctionRelation", method = RequestMethod.POST)
    public Result roleFunctionRelation(Integer roleId, String functionIds) {
        if (roleId == null) {
            return ResponseResult.paramNotNull("角色ID不能为空");
        }
        if (StringUtils.isBlank(functionIds)) {
            return ResponseResult.paramNotNull("菜单IDS不能为空");
        }
        return systemFunctionRoleRelationService.roleFunctionRelation(roleId, functionIds);
    }
}
