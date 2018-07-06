package com.isxxc.domain.dto;

import com.isxxc.domain.entity.OrderInfoDO;

import java.util.Date;
import java.util.List;

/**
 * 订单信息
 *
 * @author 泥水佬
 * @date 2018/2/6
 */
public class OrderInfoDTO extends OrderInfoDO {
    /***
     * 商店名称
     */
    private String storeName;
    /***
     * 商店号码
     */
    private String storeMobileNo;
    /***
     * 买家名称
     */
    private String userNickname;
    /***
     * 订单商品详细信息
     */
    private List<OrderItemDTO> itemList;

    public OrderInfoDTO() {
    }

    public OrderInfoDTO(Integer id, String orderNo, Integer userId, Integer storeId, Integer masterState, Integer deliverState, Integer paymentState, Integer expressState, Integer returnState, Integer cancelState, Integer commentState, Integer orderSource, Long totalPrice, Long actualPrice, Long couponPrice, Integer couponId, Long activityDiscountPrice, Integer activityDiscountId, Long freightPrice, Integer paymentType, Date paymentTime, String paymentNo, String expressNo, String expressCode, String expressName, String consigneeName, String consigneeMobile, String consigneeAddress, Date deliverTime, String remarkUser, String remarkService, Integer isDeleted, Date gmtModified, Date gmtCreate, String storeName, String storeMobileNo, String userNickname) {
        super(id, orderNo, userId, storeId, masterState, deliverState, paymentState, expressState, returnState, cancelState, commentState, orderSource, totalPrice, actualPrice, couponPrice, couponId, activityDiscountPrice, activityDiscountId, freightPrice, paymentType, paymentTime, paymentNo, expressNo, expressCode, expressName, consigneeName, consigneeMobile, consigneeAddress, deliverTime, remarkUser, remarkService, isDeleted, gmtModified, gmtCreate);
        this.storeName = storeName;
        this.storeMobileNo = storeMobileNo;
        this.userNickname = userNickname;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreMobileNo() {
        return storeMobileNo;
    }

    public void setStoreMobileNo(String storeMobileNo) {
        this.storeMobileNo = storeMobileNo;
    }

    public List<OrderItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDTO> itemList) {
        this.itemList = itemList;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
