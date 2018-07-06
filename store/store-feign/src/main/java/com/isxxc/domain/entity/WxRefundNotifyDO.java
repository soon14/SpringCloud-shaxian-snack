package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("wx_refund_notify")
public class WxRefundNotifyDO extends Model<WxRefundNotifyDO>{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 我们平台退款单号
     */
    @TableId(value = "out_refund_no")
    private String outRefundNo;

    /**
     * 我们平台的订单号
     */
    @TableId(value = "out_trade_no")
    private String outTradeNo;

    /**
     *
     */
    @TableId(value = "refund_account")
    private String refundAccount;

    /**
     *
     * 退款金额
     */
    @TableId(value = "refund_fee")
    private String refundFee;

    /**
     *
     * 微信退款id
     */
    @TableId(value = "refund_id")
    private String refundId;



    /**
     *
     *
     */
    @TableId(value = "refund_recv_accout")
    private String refundRecvAccout;

    /**
     *
     * 退款去向
     */
    @TableId(value = "refund_request_source")
    private String refundRequestSource;

    /**
     *
     * 退款状态
     */
    @TableId(value = "refund_status")
    private String refundStatus;

    /**
     *
     * 退款金额
     */
    @TableId(value = "settlement_refund_fee")
    private String settlementRefundFee;

    /**
     *
     *
     */
    @TableId(value = "settlement_total_fee")
    private String settlementTotalFee;

    /**
     *
     * 退款成功时间
     */
    @TableId(value = "success_time")
    private String successTime;

    /**
     *
     * 微信订单总金额
     */
    @TableId(value = "total_fee")
    private String totalFee;

    /**
     *
     * 支付返回的微信订单id
     */
    @TableId(value = "transaction_id")
    private String transactionId;

    /**
     *
     * 退款人
     */
    @TableId(value = "create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

    public WxRefundNotifyDO() {
    }

    public WxRefundNotifyDO(Integer id, String outRefundNo, String outTradeNo, String refundAccount, String refundFee, String refundId, String refundRecvAccout, String refundRequestSource, String refundStatus, String settlementRefundFee, String settlementTotalFee, String successTime, String totalFee, String transactionId, Date createTime) {
        this.id = id;
        this.outRefundNo = outRefundNo;
        this.outTradeNo = outTradeNo;
        this.refundAccount = refundAccount;
        this.refundFee = refundFee;
        this.refundId = refundId;
        this.refundRecvAccout = refundRecvAccout;
        this.refundRequestSource = refundRequestSource;
        this.refundStatus = refundStatus;
        this.settlementRefundFee = settlementRefundFee;
        this.settlementTotalFee = settlementTotalFee;
        this.successTime = successTime;
        this.totalFee = totalFee;
        this.transactionId = transactionId;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public String getRefundRequestSource() {
        return refundRequestSource;
    }

    public void setRefundRequestSource(String refundRequestSource) {
        this.refundRequestSource = refundRequestSource;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(String settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public String getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(String settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WxRefundNotifyDO{" +
                "id=" + id +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", refundAccount='" + refundAccount + '\'' +
                ", refundFee='" + refundFee + '\'' +
                ", refundId='" + refundId + '\'' +
                ", refundRecvAccout='" + refundRecvAccout + '\'' +
                ", refundRequestSource='" + refundRequestSource + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", settlementRefundFee='" + settlementRefundFee + '\'' +
                ", settlementTotalFee='" + settlementTotalFee + '\'' +
                ", successTime='" + successTime + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

