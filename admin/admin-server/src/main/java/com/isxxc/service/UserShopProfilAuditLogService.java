package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserShopProfilAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserShopProfilAuditLogService extends IService<UserShopProfilAuditLogDO> {

    /***
     * 查询审核日志
     * @param shopProfilId
     * @return
     */
    Result auditLogByShopProfilId(Integer shopProfilId);
}
