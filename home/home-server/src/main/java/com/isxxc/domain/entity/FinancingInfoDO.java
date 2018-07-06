package com.isxxc.domain.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2017-12-10
 */
@TableName("financing_info")
public class FinancingInfoDO extends Model<FinancingInfoDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 融资信息ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 会员ID
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 名称
     */
	private String name;
    /**
     * 融资方式(0:股权融资,1:债权融资)
     */
	private Integer category;
    /**
     * 融资金额
     */
	private BigDecimal amount;
    /**
     * 简介
     */
	private String description;
    /**
     * 特色
     */
	private String characteristic;
    /**
     * 备注
     */
	private String remark;
    /**
     * 关键词(多个以英文","分隔)
     */
	private String keyword;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 联系电话
     */
	@TableField("mobile_no")
	private String mobileNo;
    /**
     * 核状态(0:未审核,1:已通过,2:已驳回)
     */
	@TableField("audit_state")
	private Integer auditState;
    /**
     * 发布状态(0:未发布,1:已发布,2:已下线
     */
	@TableField("puslish_state")
	private Integer puslishState;
    /**
     * 发布时间
     */
	@TableField("publish_time")
	private Date publishTime;
    /**
     * 是否推荐(0:否,1:是)
     */
	@TableField("is_recommend")
	private Integer isRecommend;
    /**
     * 推荐时间
     */
	@TableField("recommend_time")
	private Date recommendTime;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getAuditState() {
		return auditState;
	}

	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}

	public Integer getPuslishState() {
		return puslishState;
	}

	public void setPuslishState(Integer puslishState) {
		this.puslishState = puslishState;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
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
		return "FinancingInfo{" +
			", id=" + id +
			", userId=" + userId +
			", name=" + name +
			", category=" + category +
			", amount=" + amount +
			", description=" + description +
			", characteristic=" + characteristic +
			", remark=" + remark +
			", keyword=" + keyword +
			", contacts=" + contacts +
			", mobileNo=" + mobileNo +
			", auditState=" + auditState +
			", puslishState=" + puslishState +
			", publishTime=" + publishTime +
			", isRecommend=" + isRecommend +
			", recommendTime=" + recommendTime +
			", isDeleted=" + isDeleted +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
