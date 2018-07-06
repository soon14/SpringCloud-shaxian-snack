package com.isxxc.domain.dto;

import com.isxxc.domain.entity.OrderRefundDO;

import java.util.Date;
import java.util.List;


/**
 * <p>
 *添加退款信息实体
 * </p>
 *
 * @author mc
 * @since 2018-05-10
 */
public class OrderRefundDTO extends OrderRefundDO {

    /***
     * 订单商品详细信息
     */
    private List<OrderItemDTO> itemList;

    public OrderRefundDTO() {

    }

    public OrderRefundDTO(List<OrderItemDTO> itemList) {
        this.itemList = itemList;
    }

    public OrderRefundDTO(Integer id, String refundNo, String refundMode, Long manualRefundPrice, String orderNo, Integer masterState, Integer orderSource, String paymentNo, Integer paymentState, Integer paymentType, Date paymentTime, String remarkService, String remarkUser, Integer storeId, String storeMobileno, String storeName, String userNickname, Long totalPrice, Long actualPrice, Long freightPrice, Long couponPrice, Long activityDiscountPrice, Integer couponId, Integer activityDiscountId, String consigneeName, String consigneeMobile, String consigneeAddress, Integer deliverState, Date deliverTime, String expressName, String expressCode, String expressNo, Integer expressState, Integer orderId, Date orderGmtcreate, Date orderGmtmodified, Integer applyState, Integer returnType, Integer returnState, Date returnCreate, Date returnUpdate, String auditor1, String auditor2, Date auditTime1, Date auditTime2, String auditReason1, String auditReason2, String refundPerson, Date refundTime, String field1, String field2, String field3, String field4, String field5) {
        super(id, refundNo, refundMode, manualRefundPrice, orderNo, masterState, orderSource, paymentNo, paymentState, paymentType, paymentTime, remarkService, remarkUser, storeId, storeMobileno, storeName, userNickname, totalPrice, actualPrice, freightPrice, couponPrice, activityDiscountPrice, couponId, activityDiscountId, consigneeName, consigneeMobile, consigneeAddress, deliverState, deliverTime, expressName, expressCode, expressNo, expressState, orderId, orderGmtcreate, orderGmtmodified, applyState, returnType, returnState, returnCreate, returnUpdate, auditor1, auditor2, auditTime1, auditTime2, auditReason1, auditReason2, refundPerson, refundTime, field1, field2, field3, field4, field5);
    }


    public OrderRefundDTO(Integer id, String refundNo, String refundMode, Long manualRefundPrice, String orderNo, Integer masterState, Integer orderSource, String paymentNo, Integer paymentState, Integer paymentType, Date paymentTime, String remarkService, String remarkUser, Integer storeId, String storeMobileno, String storeName, String userNickname, Long totalPrice, Long actualPrice, Long freightPrice, Long couponPrice, Long activityDiscountPrice, Integer couponId, Integer activityDiscountId, String consigneeName, String consigneeMobile, String consigneeAddress, Integer deliverState, Date deliverTime, String expressName, String expressCode, String expressNo, Integer expressState, Integer orderId, Date orderGmtcreate, Date orderGmtmodified, Integer applyState, Integer returnType, Integer returnState, Date returnCreate, Date returnUpdate, String auditor1, String auditor2, Date auditTime1, Date auditTime2, String auditReason1, String auditReason2, String refundPerson, Date refundTime, String field1, String field2, String field3, String field4, String field5, List<OrderItemDTO> itemList) {
        super(id, refundNo, refundMode, manualRefundPrice, orderNo, masterState, orderSource, paymentNo, paymentState, paymentType, paymentTime, remarkService, remarkUser, storeId, storeMobileno, storeName, userNickname, totalPrice, actualPrice, freightPrice, couponPrice, activityDiscountPrice, couponId, activityDiscountId, consigneeName, consigneeMobile, consigneeAddress, deliverState, deliverTime, expressName, expressCode, expressNo, expressState, orderId, orderGmtcreate, orderGmtmodified, applyState, returnType, returnState, returnCreate, returnUpdate, auditor1, auditor2, auditTime1, auditTime2, auditReason1, auditReason2, refundPerson, refundTime, field1, field2, field3, field4, field5);
        this.itemList = itemList;
    }

    public List<OrderItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDTO> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "OrderRefundDTO{" +
                "itemList=" + itemList +
                '}';
    }
}
