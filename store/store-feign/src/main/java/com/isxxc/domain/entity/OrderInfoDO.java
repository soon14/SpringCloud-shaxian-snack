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
 * 订单信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@TableName("order_info")
public class OrderInfoDO extends Model<OrderInfoDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 会员ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 商店ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 订单状态:(0:支付状态、1:配送状态、2:交易完成、3:取消订单状态、4:交易关闭)
     */
    @TableField("master_state")
    private Integer masterState;
    /**
     * 配送状态(0:待发货、1:已发货、2:已收货)
     */
    @TableField("deliver_state")
    private Integer deliverState;
    /**
     * 支付状态:(0:待支付、1:已支付、2:退款中、3:已退款、4:已作废、5:部份支付)
     */
    @TableField("payment_state")
    private Integer paymentState;
    /**
     * 物流状态:(0:待揽收、1:已揽件、2:在途、3:派件、4:已签收、5:退签、6:退回、7:疑难)
     */
    @TableField("express_state")
    private Integer expressState;
    /**
     * 退货状态:(0:部份退货、1:整单退货)
     */
    @TableField("return_state")
    private Integer returnState;
    /**
     * 取消状态(0:审核中、1:已通过、2:不通过)
     */
    @TableField("cancel_state")
    private Integer cancelState;
    /**
     * 评价状态:(0:待评价、1:已评价)
     */
    @TableField("comment_state")
    private Integer commentState;
    /**
     * 订单来源:(0:WEB、1:Android、2:IOS、3:WEIXIN)
     */
    @TableField("order_source")
    private Integer orderSource;
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
     * 优惠券优惠金额(分)
     */
    @TableField("coupon_price")
    private Long couponPrice;
    /**
     * 优惠券ID
     */
    @TableField("coupon_id")
    private Integer couponId;
    /**
     * 活动优惠金额(分)
     */
    @TableField("activity_discount_price")
    private Long activityDiscountPrice;
    /**
     * 活动优惠ID
     */
    @TableField("activity_discount_id")
    private Integer activityDiscountId;
    /**
     * 运费(分)
     */
    @TableField("freight_price")
    private Long freightPrice;
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
     * 交易号
     */
    @TableField("payment_no")
    private String paymentNo;
    /**
     * 快递单号
     */
    @TableField("express_no")
    private String expressNo;
    /**
     * 快递公司编码
     */
    @TableField("express_code")
    private String expressCode;
    /**
     * 快递公司名称
     */
    @TableField("express_name")
    private String expressName;
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
     * 发货时间
     */
    @TableField("deliver_time")
    private Date deliverTime;
    /**
     * 会员备注
     */
    @TableField("remark_user")
    private String remarkUser;
    /**
     * 客服备注
     */
    @TableField("remark_service")
    private String remarkService;
    /**
     * 删除状态(0:未删除,1:已删除)
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    /**
     * 更新时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    public OrderInfoDO() {
    }

    public OrderInfoDO(Integer id, String orderNo, Integer userId, Integer storeId, Integer masterState, Integer deliverState, Integer paymentState, Integer expressState, Integer returnState, Integer cancelState, Integer commentState, Integer orderSource, Long totalPrice, Long actualPrice, Long couponPrice, Integer couponId, Long activityDiscountPrice, Integer activityDiscountId, Long freightPrice, Integer paymentType, Date paymentTime, String paymentNo, String expressNo, String expressCode, String expressName, String consigneeName, String consigneeMobile, String consigneeAddress, Date deliverTime, String remarkUser, String remarkService, Integer isDeleted, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.storeId = storeId;
        this.masterState = masterState;
        this.deliverState = deliverState;
        this.paymentState = paymentState;
        this.expressState = expressState;
        this.returnState = returnState;
        this.cancelState = cancelState;
        this.commentState = commentState;
        this.orderSource = orderSource;
        this.totalPrice = totalPrice;
        this.actualPrice = actualPrice;
        this.couponPrice = couponPrice;
        this.couponId = couponId;
        this.activityDiscountPrice = activityDiscountPrice;
        this.activityDiscountId = activityDiscountId;
        this.freightPrice = freightPrice;
        this.paymentType = paymentType;
        this.paymentTime = paymentTime;
        this.paymentNo = paymentNo;
        this.expressNo = expressNo;
        this.expressCode = expressCode;
        this.expressName = expressName;
        this.consigneeName = consigneeName;
        this.consigneeMobile = consigneeMobile;
        this.consigneeAddress = consigneeAddress;
        this.deliverTime = deliverTime;
        this.remarkUser = remarkUser;
        this.remarkService = remarkService;
        this.isDeleted = isDeleted;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getMasterState() {
        return masterState;
    }

    public void setMasterState(Integer masterState) {
        this.masterState = masterState;
    }

    public Integer getDeliverState() {
        return deliverState;
    }

    public void setDeliverState(Integer deliverState) {
        this.deliverState = deliverState;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public Integer getExpressState() {
        return expressState;
    }

    public void setExpressState(Integer expressState) {
        this.expressState = expressState;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public Integer getCancelState() {
        return cancelState;
    }

    public void setCancelState(Integer cancelState) {
        this.cancelState = cancelState;
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
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

    public Long getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Long couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Long getActivityDiscountPrice() {
        return activityDiscountPrice;
    }

    public void setActivityDiscountPrice(Long activityDiscountPrice) {
        this.activityDiscountPrice = activityDiscountPrice;
    }

    public Integer getActivityDiscountId() {
        return activityDiscountId;
    }

    public void setActivityDiscountId(Integer activityDiscountId) {
        this.activityDiscountId = activityDiscountId;
    }

    public Long getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Long freightPrice) {
        this.freightPrice = freightPrice;
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

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
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

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getRemarkUser() {
        return remarkUser;
    }

    public void setRemarkUser(String remarkUser) {
        this.remarkUser = remarkUser;
    }

    public String getRemarkService() {
        return remarkService;
    }

    public void setRemarkService(String remarkService) {
        this.remarkService = remarkService;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                ", id=" + id +
                ", orderNo=" + orderNo +
                ", userId=" + userId +
                ", storeId=" + storeId +
                ", masterState=" + masterState +
                ", deliverState=" + deliverState +
                ", paymentState=" + paymentState +
                ", expressState=" + expressState +
                ", returnState=" + returnState +
                ", cancelState=" + cancelState +
                ", commentState=" + commentState +
                ", orderSource=" + orderSource +
                ", totalPrice=" + totalPrice +
                ", actualPrice=" + actualPrice +
                ", couponPrice=" + couponPrice +
                ", couponId=" + couponId +
                ", activityDiscountPrice=" + activityDiscountPrice +
                ", activityDiscountId=" + activityDiscountId +
                ", freightPrice=" + freightPrice +
                ", paymentType=" + paymentType +
                ", paymentTime=" + paymentTime +
                ", paymentNo=" + paymentNo +
                ", expressNo=" + expressNo +
                ", expressCode=" + expressCode +
                ", expressName=" + expressName +
                ", consigneeName=" + consigneeName +
                ", consigneeMobile=" + consigneeMobile +
                ", consigneeAddress=" + consigneeAddress +
                ", deliverTime=" + deliverTime +
                ", remarkUser=" + remarkUser +
                ", remarkService=" + remarkService +
                ", isDeleted=" + isDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
