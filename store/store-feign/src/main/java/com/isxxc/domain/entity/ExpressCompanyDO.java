package com.isxxc.domain.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-06
 */
@TableName("express_company")
public class ExpressCompanyDO extends Model<ExpressCompanyDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 快递公司ID
     */
	@TableId(value="express_id", type= IdType.AUTO)
	private Integer expressId;
    /**
     * 快递公司名称
     */
	private String name;
    /**
     * 快递公司Code
     */
	private String code;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.expressId;
	}

	@Override
	public String toString() {
		return "ExpressCompany{" +
			", expressId=" + expressId +
			", name=" + name +
			", code=" + code +
			", createTime=" + createTime +
			"}";
	}
}
