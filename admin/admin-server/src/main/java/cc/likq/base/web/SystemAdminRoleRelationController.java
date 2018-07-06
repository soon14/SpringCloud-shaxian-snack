package cc.likq.base.web;


import cc.likq.base.domain.entity.SystemAdminRoleRelationDO;
import cc.likq.base.service.SystemAdminRoleRelationService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 角色管理
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/systemAdminRoleRelation")
public class SystemAdminRoleRelationController {

    @Resource
    private SystemAdminRoleRelationService systemAdminRoleRelationService;

    /**
     * 用户与角色相关联
     * @param adminId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "adminRoleRelation", method = RequestMethod.POST)
    public Result adminRoleRelation(Integer adminId, Integer roleId){
        if (adminId == null) {
            return ResponseResult.paramNotNull("账号ID不能为空");
        }
        if (roleId == null) {
            return ResponseResult.paramNotNull("角色ID不能为空");
        }
        SystemAdminRoleRelationDO entity = new SystemAdminRoleRelationDO();
        entity.setAdminId(adminId);
        entity.setRoleId(roleId);
        return systemAdminRoleRelationService.insert(entity) ? ResponseResult.success() : ResponseResult.failResult(ResultCodeEnum.SERVER_ERROR, "关联失败");
    }
}
