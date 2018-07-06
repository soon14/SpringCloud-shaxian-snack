package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.LoansInfoAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
public interface LoansInfoAuditLogService extends IService<LoansInfoAuditLogDO> {

    /***
     * 查询日志列表
     * @param loansInfoId
     * @return
     */
    Result getAuditLogByLoansInfoId(Integer loansInfoId);
}
