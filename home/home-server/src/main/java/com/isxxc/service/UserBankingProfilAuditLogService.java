package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserBankingProfilAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserBankingProfilAuditLogService extends IService<UserBankingProfilAuditLogDO> {

    /***
     * 档案查询
     * @param bankingProfilId
     * @return
     */
    Result auditLogByBankingProfilId(Integer bankingProfilId);
}
