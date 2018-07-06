package com.isxxc.domain.dto;

import java.util.Date;

/**
 * 订单统计信息
 *
 * @author 泥水佬
 * @date 2018/2/6
 */
public class OrderStatisticsDTO {

    /***
     * 日期
     */
    private Date date;

    /***
     * 会员订单数
     */
    private Integer userCount;

    /***
     * 订单数
     */
    private Integer orderCount;

    /***
     * 订单总金额
     */
    private Integer totalAmount;

    /***
     * 会员订单支付数
     */
    private Integer userPayCount;

    /***
     * 订单支付数
     * */
    private Integer orderPayCount;

    /***
     * 订单支付总金额
     */
    private Integer payTotalAmount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getUserPayCount() {
        return userPayCount;
    }

    public void setUserPayCount(Integer userPayCount) {
        this.userPayCount = userPayCount;
    }

    public Integer getOrderPayCount() {
        return orderPayCount;
    }

    public void setOrderPayCount(Integer orderPayCount) {
        this.orderPayCount = orderPayCount;
    }

    public Integer getPayTotalAmount() {
        return payTotalAmount;
    }

    public void setPayTotalAmount(Integer payTotalAmount) {
        this.payTotalAmount = payTotalAmount;
    }
}
