package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

@TableName("order_refund_log")
public class OrderRefundLogDO extends Model<OrderRefundLogDO>{

    /**
     * 日志id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 退款单号
     */
    @TableId(value = "order_refund_no")
    private String orderRefundNo;

    /**
     * 我们平台的订单号
     */
    @TableId(value = "order_nos")
    private String orderNos;

    /**
     * 订单号，这里填微信支付单号，就是transaction_id
     */
    @TableId(value = "order_commit_no")
    private String orderCommitNo;

    /**
     *
     * 退款方式，相对微信支付的单据是整单退款，还是部分退款
     */
    @TableId(value = "return_type")
    private Integer returnType;

    /**
     *
     * 支付方式(0:微信,1:支付宝)
     */
    @TableId(value = "payment_type")
    private Integer paymentType;

    /**
     *
     * 退款状态 1，默认状态 还未向微信发起退款申请，2已经向微信发起申请，且返回成功。 12，已经向微信发起申请，但是微信返回未成功，3微信回调退款成功 13，微信回调退款失败
     */
    @TableId(value = "return_state")
    private Integer returnState;

    /**
     *
     * 请求报文明文
     */
    @TableId(value = "request_plaintext")
    private String requestPlaintext;

    /**
     *
     * 请求报文密文
     */
    @TableId(value = "request_ciphertext")
    private String requestCiphertext;

    /**
     *
     * 返回报文编码
     */
    @TableId(value = "response_code")
    private String responseCode;

    /**
     *
     * 返回报文密文
     */
    @TableId(value = "response_ciphertext")
    private String responseCiphertext;

    /**
     *
     * 返回报文明文
     */
    @TableId(value = "response_plaintext")
    private String responsePlaintext;

    /**
     *
     * 退款人
     */
    @TableId(value = "refund_person")
    private String refundPerson;

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

    public OrderRefundLogDO() {
    }

    public OrderRefundLogDO(Integer id, String orderRefundNo, String orderNos, String orderCommitNo, Integer returnType, Integer paymentType, Integer returnState, String requestPlaintext, String requestCiphertext, String responseCode, String responseCiphertext, String responsePlaintext, String refundPerson, Date createTime) {
        this.id = id;
        this.orderRefundNo = orderRefundNo;
        this.orderNos = orderNos;
        this.orderCommitNo = orderCommitNo;
        this.returnType = returnType;
        this.paymentType = paymentType;
        this.returnState = returnState;
        this.requestPlaintext = requestPlaintext;
        this.requestCiphertext = requestCiphertext;
        this.responseCode = responseCode;
        this.responseCiphertext = responseCiphertext;
        this.responsePlaintext = responsePlaintext;
        this.refundPerson = refundPerson;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderRefundNo() {
        return orderRefundNo;
    }

    public void setOrderRefundNo(String orderRefundNo) {
        this.orderRefundNo = orderRefundNo;
    }

    public String getOrderNos() {
        return orderNos;
    }

    public void setOrderNos(String orderNos) {
        this.orderNos = orderNos;
    }

    public String getOrderCommitNo() {
        return orderCommitNo;
    }

    public void setOrderCommitNo(String orderCommitNo) {
        this.orderCommitNo = orderCommitNo;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public String getRequestPlaintext() {
        return requestPlaintext;
    }

    public void setRequestPlaintext(String requestPlaintext) {
        this.requestPlaintext = requestPlaintext;
    }

    public String getRequestCiphertext() {
        return requestCiphertext;
    }

    public void setRequestCiphertext(String requestCiphertext) {
        this.requestCiphertext = requestCiphertext;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCiphertext() {
        return responseCiphertext;
    }

    public void setResponseCiphertext(String responseCiphertext) {
        this.responseCiphertext = responseCiphertext;
    }

    public String getResponsePlaintext() {
        return responsePlaintext;
    }

    public void setResponsePlaintext(String responsePlaintext) {
        this.responsePlaintext = responsePlaintext;
    }

    public String getRefundPerson() {
        return refundPerson;
    }

    public void setRefundPerson(String refundPerson) {
        this.refundPerson = refundPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderRefundLogDO{" +
                "id=" + id +
                ", orderRefundNo='" + orderRefundNo + '\'' +
                ", orderNos='" + orderNos + '\'' +
                ", orderCommitNo='" + orderCommitNo + '\'' +
                ", returnType=" + returnType +
                ", paymentType=" + paymentType +
                ", returnState=" + returnState +
                ", requestPlaintext='" + requestPlaintext + '\'' +
                ", requestCiphertext='" + requestCiphertext + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseCiphertext='" + responseCiphertext + '\'' +
                ", responsePlaintext='" + responsePlaintext + '\'' +
                ", refundPerson='" + refundPerson + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

