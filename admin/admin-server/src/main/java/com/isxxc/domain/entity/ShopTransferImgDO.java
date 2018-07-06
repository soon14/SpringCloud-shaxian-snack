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
 * 
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@TableName("shop_transfer_img")
public class ShopTransferImgDO extends Model<ShopTransferImgDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺转让/求租图片ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 店铺转让/求租ID
     */
	@TableField("transfer_id")
	private Integer transferId;
    /**
     * 名称
     */
	private String name;
    /**
     * 排序
     */
	private Integer sort;
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

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
		return "ShopTransferImg{" +
			", id=" + id +
			", transferId=" + transferId +
			", name=" + name +
			", sort=" + sort +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
