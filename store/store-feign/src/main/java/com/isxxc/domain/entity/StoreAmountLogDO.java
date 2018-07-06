package com.isxxc.domain.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-05
 */
@TableName("store_amount_log")
public class StoreAmountLogDO extends Model<StoreAmountLogDO> {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 总金额ID
	 */
	@TableField("store_amount_id")
	private Integer storeAmountId;
	/**
	 * 订单号
	 */
	@TableField("order_no")
	private String orderNo;
	/**
	 * 金额
	 */
	private Long amount;
	/**
	 * 之前金额
	 */
	@TableField("bofore_amount")
	private Long boforeAmount;
	/**
	 * 类型(0:收入,1:退款,2:提现)
	 */
	private Integer type;
	/**
	 * 更新时间
	 */
	@TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
	/**
	 * 创建时间
	 */
	@TableField(value = "gmt_create", fill = FieldFill.INSERT)
	private Date gmtCreate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStoreAmountId() {
		return storeAmountId;
	}

	public void setStoreAmountId(Integer storeAmountId) {
		this.storeAmountId = storeAmountId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getBoforeAmount() {
		return boforeAmount;
	}

	public void setBoforeAmount(Long boforeAmount) {
		this.boforeAmount = boforeAmount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
		return "StoreAmountLog{" +
				", id=" + id +
				", storeAmountId=" + storeAmountId +
				", orderNo=" + orderNo +
				", amount=" + amount +
				", boforeAmount=" + boforeAmount +
				", type=" + type +
				", gmtModified=" + gmtModified +
				", gmtCreate=" + gmtCreate +
				"}";
	}
}
