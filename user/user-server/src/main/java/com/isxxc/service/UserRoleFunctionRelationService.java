package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserRoleFunctionRelationDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-14
 */
public interface UserRoleFunctionRelationService extends IService<UserRoleFunctionRelationDO> {

    /***
     * 角色菜单关联
     */
    Result roleFunctionRelation(Integer roleId, String functionIds);
}
