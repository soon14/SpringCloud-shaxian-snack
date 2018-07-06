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
 * @since 2017-11-29
 */
@TableName("user_supporting_profil")
public class UserSupportingProfilDO extends Model<UserSupportingProfilDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员配套服务商/文化服务商档案ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 服务介绍
     */
    @TableField("service_introduce")
    private String serviceIntroduce;
    /**
     * 公司介绍
     */
    @TableField("company_introduce")
    private String companyIntroduce;
    /**
     * Logo
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

    public String getServiceIntroduce() {
        return serviceIntroduce;
    }

    public void setServiceIntroduce(String serviceIntroduce) {
        this.serviceIntroduce = serviceIntroduce;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserSupportingProfil{" +
                ", id=" + id +
                ", userId=" + userId +
                ", companyName=" + companyName +
                ", address=" + address +
                ", contact=" + contact +
                ", mobileNo=" + mobileNo +
                ", identityCardFrontImg=" + identityCardFrontImg +
                ", identityCardBackImg=" + identityCardBackImg +
                ", serviceIntroduce=" + serviceIntroduce +
                ", companyIntroduce=" + companyIntroduce +
                ", logo=" + logo +
                ", auditState=" + auditState +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
