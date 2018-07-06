package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ShopTenantAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
public interface ShopTenantAuditLogService extends IService<ShopTenantAuditLogDO> {

    /***
     * 查询日志
     * @param tenantId
     * @return
     */
    Result getAuditLogByTenantId(Integer tenantId);

    /***
     * 审核
     * @param shopTenantAuditLogDO
     * @return
     */
    Result audit(ShopTenantAuditLogDO shopTenantAuditLogDO);
}
