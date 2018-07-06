package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ShopTransferAuditLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
public interface ShopTransferAuditLogService extends IService<ShopTransferAuditLogDO> {

    /***
     * 获取审核日志
     * @param transferId
     * @return
     */
    Result getAuditLogByTransferId(Integer transferId);

    /***
     * 审核
     * @param shopTransferAuditLogDO
     * @return
     */
    Result audit(ShopTransferAuditLogDO shopTransferAuditLogDO);
}
