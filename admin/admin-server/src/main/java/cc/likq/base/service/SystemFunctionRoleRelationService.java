package cc.likq.base.service;

import com.baomidou.mybatisplus.service.IService;

import cc.likq.base.domain.entity.SystemFunctionRoleRelationDO;
import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
public interface SystemFunctionRoleRelationService extends IService<SystemFunctionRoleRelationDO> {

    /***
     * 角色与菜单关联
     */
    Result roleFunctionRelation(Integer roleId, String functionIds);
}
