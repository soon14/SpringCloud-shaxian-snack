package com.isxxc.domain.dto;

import java.util.List;

/**
 * <p>
 * 提交商店订单信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
public class SubmitStoreOrderDTO {
    /***
     * 商店ID
     */
    private Integer storeId;
    /**
     * 运费(分)
     */
    private Long freightPrice;
    /**
     * 总金额(分)
     */
    private Long totalPrice;
    /**
     * 实付(分)
     */
    private Long actualPrice;
    /***
     * 商品SKU信息项
     */
    private List<SubmitOrderSkuDTO> skuList;
    /**
     * 会员备注
     */
    private String remarkUser;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Long getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Long freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SubmitOrderSkuDTO> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SubmitOrderSkuDTO> skuList) {
        this.skuList = skuList;
    }

    public String getRemarkUser() {
        return remarkUser;
    }

    public void setRemarkUser(String remarkUser) {
        this.remarkUser = remarkUser;
    }

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }
}
