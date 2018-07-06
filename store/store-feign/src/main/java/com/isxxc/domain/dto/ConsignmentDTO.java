package com.isxxc.domain.dto;

/**
 * 发货订单信息
 *
 * @author 泥水佬
 * @date 2018/2/7
 */
public class ConsignmentDTO {
    /***
     * 商店ID
     */
    private Integer storeId;
    /***
     * 订单号
     */
    private String orderNo;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 快递公司编码
     */
    private String expressCode;
    /**
     * 快递公司名称
     */
    private String expressName;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
}
