package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserSupportingAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserSupportingAuditLogService extends IService<UserSupportingAuditLogDO> {
    /***
     * 查询审核日志
     * @param supportingProfilId
     * @return
     */
    Result auditLogBySupportingProfilId(Integer supportingProfilId);
}
