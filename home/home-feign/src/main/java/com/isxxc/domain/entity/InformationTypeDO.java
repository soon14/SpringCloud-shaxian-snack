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
 * @author likq
 * @since 2017-12-06
 */
@TableName("information_type")
public class InformationTypeDO extends Model<InformationTypeDO> {
	private static final long serialVersionUID = 1L;

	/**
	 * 新闻资讯类型ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类别(0:首页—新闻,1:首页—公告,2:首页—文化服务商,3:首页—产品设计,4:小吃文化—美食品鉴,5:小吃文化—美食故事,6:小吃文化—舌尖旅行)
	 */
	private Integer category;
	/**
	 * 删除(0:未删除,1:已删除)
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
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
		return "InformationType{" +
				", id=" + id +
				", name=" + name +
				", isDeleted=" + isDeleted +
				", category=" + category +
				", gmtModified=" + gmtModified +
				", gmtCreate=" + gmtCreate +
				"}";
	}
}
