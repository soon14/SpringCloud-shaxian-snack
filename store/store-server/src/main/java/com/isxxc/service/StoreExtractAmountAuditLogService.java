package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.StoreExtractAmountAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 提现审核日志 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
public interface StoreExtractAmountAuditLogService extends IService<StoreExtractAmountAuditLogDO> {

    /***
     * 审核
     * @param auditLogDO
     * @return
     */
    Result audit(StoreExtractAmountAuditLogDO auditLogDO);
}
