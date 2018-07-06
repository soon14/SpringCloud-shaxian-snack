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
 * @author 泥水佬
 * @since 2017-12-10
 */
@TableName("financing_info_img")
public class FinancingInfoImgDO extends Model<FinancingInfoImgDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 融资信息图片
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 融资信息ID
     */
	@TableField("financing_info_id")
	private Integer financingInfoId;
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

	public Integer getFinancingInfoId() {
		return financingInfoId;
	}

	public void setFinancingInfoId(Integer financingInfoId) {
		this.financingInfoId = financingInfoId;
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
		return "FinancingInfoImg{" +
			", id=" + id +
			", financingInfoId=" + financingInfoId +
			", name=" + name +
			", sort=" + sort +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
