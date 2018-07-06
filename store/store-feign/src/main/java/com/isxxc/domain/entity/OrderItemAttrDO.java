package com.isxxc.domain.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 订单子项属性
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@TableName("order_item_attr")
public class OrderItemAttrDO extends Model<OrderItemAttrDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单项ID
     */
	@TableField("order_item_id")
	private Integer orderItemId;
    /**
     * 属性名称
     */
	@TableField("attr_name")
	private String attrName;
    /**
     * 属性值
     */
	@TableField("attr_value")
	private String attrValue;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
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
		return "OrderItemAttr{" +
			", id=" + id +
			", orderItemId=" + orderItemId +
			", attrName=" + attrName +
			", attrValue=" + attrValue +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
