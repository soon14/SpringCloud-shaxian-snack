package com.isxxc.client;

import com.isxxc.domain.entity.StoreExtractAmountAuditLogDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额审核日志 服务治理
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@RequestMapping("/storeExtractAmountAuditLogClient")
public interface StoreExtractAmountAuditLogClient {

    /***
     * 审核
     * @param auditLogDO
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result audit(StoreExtractAmountAuditLogDO auditLogDO);
}
