package com.isxxc.client;


import com.isxxc.domain.entity.StoreExtractAmountAuditLogDO;
import com.isxxc.service.StoreExtractAmountAuditLogService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额日志 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-1-05
 */
@RestController
public class StoreExtractAmountAuditLogClientImpl implements StoreExtractAmountAuditLogClient {

    @Resource
    private StoreExtractAmountAuditLogService storeExtractAmountAuditLogService;

    @Override
    public Result audit(@RequestBody StoreExtractAmountAuditLogDO auditLogDO) {
        return storeExtractAmountAuditLogService.audit(auditLogDO);
    }
}
