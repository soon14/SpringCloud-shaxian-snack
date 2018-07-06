package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.FinancingInfoAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
public interface FinancingInfoAuditLogService extends IService<FinancingInfoAuditLogDO> {

    /***
     * 查询日志列表
     * @param financingInfoId
     * @return
     */
    Result getAuditLogByFinancingInfoId(Integer financingInfoId);

    /***
     *审核
     * @param financingInfoAuditLogDO
     * @return
     */
    Result audit(FinancingInfoAuditLogDO financingInfoAuditLogDO);
}
