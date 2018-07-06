package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserStoreProfilAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserStoreProfilAuditLogService extends IService<UserStoreProfilAuditLogDO> {

    /***
     * 查询审核日志列表
     * @param storeProfilId
     * @return
     */
    Result auditLogByStoreProfilId(Integer storeProfilId);
}
