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
 * 原料供应商档案
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@TableName("user_store_profil")
public class UserStoreProfilDO extends Model<UserStoreProfilDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员原料供应商商户档案ID
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
	@TableField("individual_business_license_img")
	private String individualBusinessLicenseImg;
    /**
     * 法人身份证照片(正面)
     */
	@TableField("identity_card_front_img")
	private String identityCardFrontImg;
    /**
     * 法人身份证照片(背面)
     */
	@TableField("identity_card_back_img")
	private String identityCardBackImg;
    /**
     * 银行卡正面照
     */
	@TableField("bank_img")
	private String bankImg;
    /**
     * 食品经营许可证（食品类需要）
     */
	@TableField("business_license_img")
	private String businessLicenseImg;
    /**
     * 开户名
     */
	@TableField("bank_user_name")
	private String bankUserName;
    /**
     * 开户银行
     */
	@TableField("bank_name")
	private String bankName;
    /**
     * 对公银行账号/银行卡号
     */
	@TableField("bank_no")
	private String bankNo;
    /**
     * Logo图片
     */
	private String logo;
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

	public String getIndividualBusinessLicenseImg() {
		return individualBusinessLicenseImg;
	}

	public void setIndividualBusinessLicenseImg(String individualBusinessLicenseImg) {
		this.individualBusinessLicenseImg = individualBusinessLicenseImg;
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

	public String getBankImg() {
		return bankImg;
	}

	public void setBankImg(String bankImg) {
		this.bankImg = bankImg;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getAuditState() {
		return auditState;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserStoreProfil{" +
			", id=" + id +
			", userId=" + userId +
			", companyName=" + companyName +
			", address=" + address +
			", contact=" + contact +
			", mobileNo=" + mobileNo +
			", individualBusinessLicenseImg=" + individualBusinessLicenseImg +
			", identityCardFrontImg=" + identityCardFrontImg +
			", identityCardBackImg=" + identityCardBackImg +
			", bankImg=" + bankImg +
			", businessLicenseImg=" + businessLicenseImg +
			", bankUserName=" + bankUserName +
			", bankName=" + bankName +
			", bankNo=" + bankNo +
			", logo=" + logo +
			", auditState=" + auditState +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
