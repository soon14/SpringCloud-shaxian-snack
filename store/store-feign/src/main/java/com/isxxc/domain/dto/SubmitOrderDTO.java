package com.isxxc.domain.dto;

import java.util.List;

/**
 * <p>
 * 提交订单信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
public class SubmitOrderDTO {
    /***
     * 会员ID
     */
    private Integer userId;
    /**
     * 订单来源:(0:WEB、1:Android、2:IOS、3:WEIXIN)
     */
    private Integer orderSource;
    /**
     * 实付(分)
     */
    private Long actualPrice;
    /**
     * 收件人姓名
     */
    private String consigneeName;
    /**
     * 收件人号码
     */
    private String consigneeMobile;
    /**
     * 收件人地址
     */
    private String consigneeAddress;
    /***
     * 商店订单信息
     */
    private List<SubmitStoreOrderDTO> storeOrderList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public List<SubmitStoreOrderDTO> getStoreOrderList() {
        return storeOrderList;
    }

    public void setStoreOrderList(List<SubmitStoreOrderDTO> storeOrderList) {
        this.storeOrderList = storeOrderList;
    }
}
