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
 * @author likq
 * @since 2017-11-30
 */
@TableName("user_shop_profil")
public class UserShopProfilDO extends Model<UserShopProfilDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员门店商户档案ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 会员ID
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 企业/个体工商户名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 招牌名称
     */
	@TableField("signboard_name")
	private String signboardName;
	/**
	 * 省Code
	 */
	@TableField("province_code")
	private Integer provinceCode;
	/**
	 * 市Code
	 */
	@TableField("city_code")
	private Integer cityCode;
	/**
	 * 区Code
	 */
	@TableField("area_code")
	private Integer areaCode;
    /**
     * 地址
     */
	private String address;
    /**
     * 联系人
     */
	private String contact;
    /**
     * 联系方式
     */
	@TableField("mobile_no")
	private String mobileNo;
    /**
     * 企业/个体工商户营业执照
     */
	@TableField("business_license_img")
	private String businessLicenseImg;
    /**
     * 个人身份证照(正面)
     */
	@TableField("identity_card_front_img")
	private String identityCardFrontImg;
    /**
     * 个人身份证照(背面)
     */
	@TableField("identity_card_back_img")
	private String identityCardBackImg;
    /**
     * 营业时间
     */
	@TableField("business_hours")
	private String businessHours;
	/***
	 * 是否完善(0:未完善,1:已完善)
	 */
	@TableField("is_complete")
	private Integer isComplete;
    /**
     * 审核状态(0:未审核,1:已通过,2:已驳回)
     */
	@TableField("audit_state")
	private Integer auditState;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSignboardName() {
		return signboardName;
	}

	public void setSignboardName(String signboardName) {
		this.signboardName = signboardName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public String getIdentityCardFrontImg() {
		return identityCardFrontImg;
	}

	public void setIdentityCardFrontImg(String identityCardFrontImg) {
		this.identityCardFrontImg = identityCardFrontImg;
	}

	public String getIdentityCardBackImg() {
		return identityCardBackImg;
	}

	public void setIdentityCardBackImg(String identityCardBackImg) {
		this.identityCardBackImg = identityCardBackImg;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public Integer getAuditState() {
		return auditState;
	}

	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
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

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserShopProfil{" +
			", id=" + id +
			", userId=" + userId +
			", companyName=" + companyName +
			", signboardName=" + signboardName +
			", address=" + address +
			", contact=" + contact +
			", mobileNo=" + mobileNo +
			", businessLicenseImg=" + businessLicenseImg +
			", identityCardFrontImg=" + identityCardFrontImg +
			", identityCardBackImg=" + identityCardBackImg +
			", businessHours=" + businessHours +
			", auditState=" + auditState +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
