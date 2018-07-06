package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author mc
 * @since 2018-05-10
 */
@TableName("order_refund")
public class OrderRefundDO extends Model<OrderRefundDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 退款ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 退款单号
     */
    @TableField("refund_no")
    private String refundNo;


    /**
     * 退款方式：1，全额退款 ，2，扣除邮费退款  3，自定义金额退款
     */
    @TableField("refund_mode")
    private String refundMode;


    /**
     * 自定义退款金额
     */
    @TableField("manual_refund_price")
    private Long manualRefundPrice;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 订单状态:(0:支付状态、1:配送状态、2:交易完成、3:取消订单状态、4:交易关闭)
     */
    @TableField("master_state")
    private Integer masterState;

    /**
     * 订单来源:(0:WEB、1:Android、2:IOS、3:WEIXIN)
     */
    @TableField("order_source")
    private Integer orderSource;

    /**
     * 支付交易号
     */
    @TableField("payment_no")
    private String paymentNo;

    /**
     * 支付状态:(0:待支付、1:已支付、2:退款中、3:已退款、4:已作废、5:部份支付)
     */
    @TableField("payment_state")
    private Integer paymentState;
    /**
     * 支付方式(0:微信,1:支付宝)
     */
    @TableField("payment_type")
    private Integer paymentType;

    /**
     * 支付时间
     */
    @TableField("payment_time")
    private Date paymentTime;
    /**
     * 客服备注
     */
    @TableField("remark_service")
    private String remarkService;

    /**
     * 会员备注
     */
    @TableField("remark_user")
    private String remarkUser;

    /**
     * 商店ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 商店手机号码
     */
    @TableField("store_mobileno")
    private String storeMobileno;
    /**
     * 商店名称
     */
    @TableField("store_name")
    private String storeName;
    /**
     * 会员昵称
     */
    @TableField("user_nickname")
    private String userNickname;
    /**
     * 总金额(分)
     */
    @TableField("total_price")
    private Long totalPrice;

    /**
     * 实付(分)
     */
    @TableField("actual_price")
    private Long actualPrice;

    /**
     * 运费(分)
     */
    @TableField("freight_price")
    private Long freightPrice;

    /**
     * 优惠券优惠金额(分)
     */
    @TableField("coupon_price")
    private Long couponPrice;

    /**
     * 活动优惠金额(分)
     */
    @TableField("activity_discount_price")
    private Long activityDiscountPrice;
    /**
     * 优惠券ID
     */
    @TableField("coupon_id")
    private Integer couponId;


    /**
     * 活动优惠ID
     */
    @TableField("activity_discount_id")
    private Integer activityDiscountId;

    /**
     * 收件人姓名
     */
    @TableField("consignee_name")
    private String consigneeName;
    /**
     * 收件人号码
     */
    @TableField("consignee_mobile")
    private String consigneeMobile;

    /**
     * 收件人地址
     */
    @TableField("consignee_address")
    private String consigneeAddress;


    /**
     * 发送状态(0:待发货、1:已发货、2:已收货)
     */
    @TableField("deliver_state")
    private Integer deliverState;
    /**
     * 发货时间
     */
    @TableField("deliver_time")
    private Date deliverTime;

    /**
     * 快递公司名称
     */
    @TableField("express_name")
    private String expressName;

    /**
     * 快递公司编码
     */
    @TableField("express_code")
    private String expressCode;
    /**
     * 快递单号
     */
    @TableField("express_no")
    private String expressNo;

    /**
     * 物流状态:(0:待揽件、1:已揽件、2:在途、3:派件、4:已签收、5:退签、6:退回、7:疑难)
     */
    @TableField("express_state")
    private Integer expressState;


    /**
     * 订单id 关联子订单条件 order_iterm的order_info_id
     */
    @TableField("order_id")
    private Integer orderId;

    /**
     * 订单创建时间
     */
    @TableField("order_gmtcreate")
    private Date orderGmtcreate;

    /**
     * 订单更新时间
     */
    @TableField("order_gmtmodified")
    private Date orderGmtmodified;


    /**
     * 退款申请状态（1提交申请，2商家审核同意退款。3，平台审核同意退款，12，商家不同意退款 13，平台不同意退款）
     */
    @TableField("apply_state")
    private Integer applyState;

    /**
     * 退款类型 1，整单退款。2，部分退款
     */
    @TableField("return_type")
    private Integer returnType;

    /**
     * 退款状态 1，默认状态 还未向微信发起退款申请，2已经向微信发起申请，且返回成功。 12，已经向微信发起申请，但是微信返回未成功，3微信回调退款成功 13，微信回调退款失败
     */
    @TableField("return_state")
    private Integer returnState;

    /**
     * 退款记录更新时间
     */
    @TableField(value = "return_create", fill = FieldFill.INSERT)
    private Date returnCreate;
    /**
     * 退款记录创建时间
     */
    @TableField(value = "return_update", fill = FieldFill.INSERT_UPDATE)
    private Date returnUpdate;


    /**
     * 审核人1
     */
    @TableField("auditor1")
    private String auditor1;

    /**
     * 审核人2
     */
    @TableField("auditor2")
    private String auditor2;

    /**
     * 审核时间1
     */
    @TableField("audit_time1")
    private Date auditTime1;

    /**
     * 审核时间2
     */
    @TableField("audit_time2")
    private Date auditTime2;

    /**
     * 审核说明1，同意退款
     */
    @TableField("audit_reason1")
    private String auditReason1;

    /**
     * 审核说明2 通过
     */
    @TableField("audit_reason2")
    private String auditReason2;

    /**
     * 退款人
     */
    @TableField("refund_person")
    private String refundPerson;

    /**
     * 退款时间
     */
    @TableField("refund_time")
    private Date refundTime;



    /**
     * 扩展字段1(用户userid)
     */
    @TableField("field1")
    private String field1;

    /**
     * 扩展字段2，用户提交的退款物流公司
     */
    @TableField("field2")
    private String field2;

    /**
     * 扩展字段3 用户提交的退款物流单号
     */
    @TableField("field3")
    private String field3;

    /**
     * 扩展字段4
     */
    @TableField("field4")
    private String field4;

    /**
     * 扩展字段5
     */
    @TableField("field5")
    private String field5;

    public OrderRefundDO() {
    }

    public OrderRefundDO(Integer id, String refundNo, String refundMode, Long manualRefundPrice, String orderNo, Integer masterState, Integer orderSource, String paymentNo, Integer paymentState, Integer paymentType, Date paymentTime, String remarkService, String remarkUser, Integer storeId, String storeMobileno, String storeName, String userNickname, Long totalPrice, Long actualPrice, Long freightPrice, Long couponPrice, Long activityDiscountPrice, Integer couponId, Integer activityDiscountId, String consigneeName, String consigneeMobile, String consigneeAddress, Integer deliverState, Date deliverTime, String expressName, String expressCode, String expressNo, Integer expressState, Integer orderId, Date orderGmtcreate, Date orderGmtmodified, Integer applyState, Integer returnType, Integer returnState, Date returnCreate, Date returnUpdate, String auditor1, String auditor2, Date auditTime1, Date auditTime2, String auditReason1, String auditReason2, String refundPerson, Date refundTime, String field1, String field2, String field3, String field4, String field5) {
        this.id = id;
        this.refundNo = refundNo;
        this.refundMode = refundMode;
        this.manualRefundPrice = manualRefundPrice;
        this.orderNo = orderNo;
        this.masterState = masterState;
        this.orderSource = orderSource;
        this.paymentNo = paymentNo;
        this.paymentState = paymentState;
        this.paymentType = paymentType;
        this.paymentTime = paymentTime;
        this.remarkService = remarkService;
        this.remarkUser = remarkUser;
        this.storeId = storeId;
        this.storeMobileno = storeMobileno;
        this.storeName = storeName;
        this.userNickname = userNickname;
        this.totalPrice = totalPrice;
        this.actualPrice = actualPrice;
        this.freightPrice = freightPrice;
        this.couponPrice = couponPrice;
        this.activityDiscountPrice = activityDiscountPrice;
        this.couponId = couponId;
        this.activityDiscountId = activityDiscountId;
        this.consigneeName = consigneeName;
        this.consigneeMobile = consigneeMobile;
        this.consigneeAddress = consigneeAddress;
        this.deliverState = deliverState;
        this.deliverTime = deliverTime;
        this.expressName = expressName;
        this.expressCode = expressCode;
        this.expressNo = expressNo;
        this.expressState = expressState;
        this.orderId = orderId;
        this.orderGmtcreate = orderGmtcreate;
        this.orderGmtmodified = orderGmtmodified;
        this.applyState = applyState;
        this.returnType = returnType;
        this.returnState = returnState;
        this.returnCreate = returnCreate;
        this.returnUpdate = returnUpdate;
        this.auditor1 = auditor1;
        this.auditor2 = auditor2;
        this.auditTime1 = auditTime1;
        this.auditTime2 = auditTime2;
        this.auditReason1 = auditReason1;
        this.auditReason2 = auditReason2;
        this.refundPerson = refundPerson;
        this.refundTime = refundTime;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    public Long getManualRefundPrice() {
        return manualRefundPrice;
    }

    public void setManualRefundPrice(Long manualRefundPrice) {
        this.manualRefundPrice = manualRefundPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getMasterState() {
        return masterState;
    }

    public void setMasterState(Integer masterState) {
        this.masterState = masterState;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getRemarkService() {
        return remarkService;
    }

    public void setRemarkService(String remarkService) {
        this.remarkService = remarkService;
    }

    public String getRemarkUser() {
        return remarkUser;
    }

    public void setRemarkUser(String remarkUser) {
        this.remarkUser = remarkUser;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreMobileno() {
        return storeMobileno;
    }

    public void setStoreMobileno(String storeMobileno) {
        this.storeMobileno = storeMobileno;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Long getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Long freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Long getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Long couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Long getActivityDiscountPrice() {
        return activityDiscountPrice;
    }

    public void setActivityDiscountPrice(Long activityDiscountPrice) {
        this.activityDiscountPrice = activityDiscountPrice;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getActivityDiscountId() {
        return activityDiscountId;
    }

    public void setActivityDiscountId(Integer activityDiscountId) {
        this.activityDiscountId = activityDiscountId;
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

    public Integer getDeliverState() {
        return deliverState;
    }

    public void setDeliverState(Integer deliverState) {
        this.deliverState = deliverState;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Integer getExpressState() {
        return expressState;
    }

    public void setExpressState(Integer expressState) {
        this.expressState = expressState;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderGmtcreate() {
        return orderGmtcreate;
    }

    public void setOrderGmtcreate(Date orderGmtcreate) {
        this.orderGmtcreate = orderGmtcreate;
    }

    public Date getOrderGmtmodified() {
        return orderGmtmodified;
    }

    public void setOrderGmtmodified(Date orderGmtmodified) {
        this.orderGmtmodified = orderGmtmodified;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public Date getReturnCreate() {
        return returnCreate;
    }

    public void setReturnCreate(Date returnCreate) {
        this.returnCreate = returnCreate;
    }

    public Date getReturnUpdate() {
        return returnUpdate;
    }

    public void setReturnUpdate(Date returnUpdate) {
        this.returnUpdate = returnUpdate;
    }

    public String getAuditor1() {
        return auditor1;
    }

    public void setAuditor1(String auditor1) {
        this.auditor1 = auditor1;
    }

    public String getAuditor2() {
        return auditor2;
    }

    public void setAuditor2(String auditor2) {
        this.auditor2 = auditor2;
    }

    public Date getAuditTime1() {
        return auditTime1;
    }

    public void setAuditTime1(Date auditTime1) {
        this.auditTime1 = auditTime1;
    }

    public Date getAuditTime2() {
        return auditTime2;
    }

    public void setAuditTime2(Date auditTime2) {
        this.auditTime2 = auditTime2;
    }

    public String getAuditReason1() {
        return auditReason1;
    }

    public void setAuditReason1(String auditReason1) {
        this.auditReason1 = auditReason1;
    }

    public String getAuditReason2() {
        return auditReason2;
    }

    public void setAuditReason2(String auditReason2) {
        this.auditReason2 = auditReason2;
    }

    public String getRefundPerson() {
        return refundPerson;
    }

    public void setRefundPerson(String refundPerson) {
        this.refundPerson = refundPerson;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "OrderRefundDO{" +
                "id=" + id +
                ", refundNo='" + refundNo + '\'' +
                ", refundMode='" + refundMode + '\'' +
                ", manualRefundPrice='" + manualRefundPrice + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", masterState=" + masterState +
                ", orderSource=" + orderSource +
                ", paymentNo='" + paymentNo + '\'' +
                ", paymentState=" + paymentState +
                ", paymentType=" + paymentType +
                ", paymentTime=" + paymentTime +
                ", remarkService='" + remarkService + '\'' +
                ", remarkUser='" + remarkUser + '\'' +
                ", storeId=" + storeId +
                ", storeMobileno='" + storeMobileno + '\'' +
                ", storeName='" + storeName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", totalPrice=" + totalPrice +
                ", actualPrice=" + actualPrice +
                ", freightPrice=" + freightPrice +
                ", couponPrice=" + couponPrice +
                ", activityDiscountPrice=" + activityDiscountPrice +
                ", couponId=" + couponId +
                ", activityDiscountId=" + activityDiscountId +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeMobile='" + consigneeMobile + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", deliverState=" + deliverState +
                ", deliverTime=" + deliverTime +
                ", expressName='" + expressName + '\'' +
                ", expressCode='" + expressCode + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", expressState=" + expressState +
                ", orderId=" + orderId +
                ", orderGmtcreate=" + orderGmtcreate +
                ", orderGmtmodified=" + orderGmtmodified +
                ", applyState=" + applyState +
                ", returnType=" + returnType +
                ", returnState=" + returnState +
                ", returnCreate=" + returnCreate +
                ", returnUpdate=" + returnUpdate +
                ", auditor1='" + auditor1 + '\'' +
                ", auditor2='" + auditor2 + '\'' +
                ", auditTime1=" + auditTime1 +
                ", auditTime2=" + auditTime2 +
                ", auditReason1='" + auditReason1 + '\'' +
                ", auditReason2='" + auditReason2 + '\'' +
                ", refundPerson='" + refundPerson + '\'' +
                ", refundTime=" + refundTime +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                '}';
    }
}
