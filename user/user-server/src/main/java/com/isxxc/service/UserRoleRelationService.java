package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserRoleRelationDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-14
 */
public interface UserRoleRelationService extends IService<UserRoleRelationDO> {
    /***
     * 会员与角色关联
     * @param userId
     * @param roleCodes
     * @return
     */
    Result userRoleRelation(Integer userId, String roleCodes);
}
