package com.isxxc.domain.dto;

import com.isxxc.domain.entity.StoreExtractAmountLogDO;

import java.util.Date;

/**
 * 商店提取金额日志
 *
 * @author 泥水佬
 * @date 2018/3/7
 */
public class StoreExtractAmountLogDTO extends StoreExtractAmountLogDO {
    /***
     * 商店名称
     */
    private String storeName;

    public StoreExtractAmountLogDTO() {
    }

    public StoreExtractAmountLogDTO(Integer id, Integer storeId, String bankName, String bankUserName, String bankNo, Long extractAmount, Long poundage, Long settlementAmount, String remarkService, Integer adminId, Integer auditState, Integer settlementState, Date gmtModified, Date gmtCreate, String storeName) {
        super(id, storeId, bankName, bankUserName, bankNo, extractAmount, poundage, settlementAmount, remarkService, adminId, auditState, settlementState, gmtModified, gmtCreate);
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
