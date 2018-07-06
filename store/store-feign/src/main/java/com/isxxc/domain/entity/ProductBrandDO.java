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
 * 商品品牌
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@TableName("product_brand")
public class ProductBrandDO extends Model<ProductBrandDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 店铺ID
     */
	@TableField("store_id")
	private Integer storeId;
    /**
     * 名称
     */
	private String name;
    /**
     * 描述
     */
	private String description;
    /**
     * Logo图片
     */
	private String logo;
    /**
     * 删除状态(0:未删除,1:已删除)
     */
	@TableField("is_deleted")
	private Integer isDeleted;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
		return "ProductBrand{" +
			", id=" + id +
			", storeId=" + storeId +
			", name=" + name +
			", description=" + description +
			", logo=" + logo +
			", isDeleted=" + isDeleted +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
