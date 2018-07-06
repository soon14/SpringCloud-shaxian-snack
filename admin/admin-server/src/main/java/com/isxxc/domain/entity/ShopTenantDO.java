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
 * @since 2017-12-08
 */
@TableName("shop_tenant")
public class ShopTenantDO extends Model<ShopTenantDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 旺铺承包
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 最小面积
     */
    @TableField("acreage_min")
    private Integer acreageMin;
    /**
     * 最大面积
     */
    @TableField("acreage_max")
    private Integer acreageMax;
    /**
     * 最低转让费
     */
    @TableField("transfer_amount_min")
    private Integer transferAmountMin;
    /**
     * 最高转让费
     */
    @TableField("transfer_amount_max")
    private Integer transferAmountMax;
    /**
     * 最低租金
     */
    @TableField("rent_amount_min")
    private Integer rentAmountMin;
    /**
     * 最高租金
     */
    @TableField("rent_amount_max")
    private Integer rentAmountMax;
    /**
     * 描述
     */
    private String description;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 手机号
     */
    @TableField("mobile_no")
    private String mobileNo;
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
     * 审核状态(0:未审核,1:已通过,2:已驳回)
     */
    @TableField("audit_state")
    private Integer auditState;
    /**
     * 发布状态(0:未发布,1:已发布,2:已下线)
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getAcreageMin() {
        return acreageMin;
    }

    public void setAcreageMin(Integer acreageMin) {
        this.acreageMin = acreageMin;
    }

    public Integer getAcreageMax() {
        return acreageMax;
    }

    public void setAcreageMax(Integer acreageMax) {
        this.acreageMax = acreageMax;
    }

    public Integer getTransferAmountMin() {
        return transferAmountMin;
    }

    public void setTransferAmountMin(Integer transferAmountMin) {
        this.transferAmountMin = transferAmountMin;
    }

    public Integer getTransferAmountMax() {
        return transferAmountMax;
    }

    public void setTransferAmountMax(Integer transferAmountMax) {
        this.transferAmountMax = transferAmountMax;
    }

    public Integer getRentAmountMin() {
        return rentAmountMin;
    }

    public void setRentAmountMin(Integer rentAmountMin) {
        this.rentAmountMin = rentAmountMin;
    }

    public Integer getRentAmountMax() {
        return rentAmountMax;
    }

    public void setRentAmountMax(Integer rentAmountMax) {
        this.rentAmountMax = rentAmountMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "ShopTenant{" +
                ", id=" + id +
                ", userId=" + userId +
                ", title=" + title +
                ", acreageMin=" + acreageMin +
                ", acreageMax=" + acreageMax +
                ", transferAmountMin=" + transferAmountMin +
                ", transferAmountMax=" + transferAmountMax +
                ", rentAmountMin=" + rentAmountMin +
                ", rentAmountMax=" + rentAmountMax +
                ", description=" + description +
                ", contacts=" + contacts +
                ", mobileNo=" + mobileNo +
                ", provinceCode=" + provinceCode +
                ", cityCode=" + cityCode +
                ", areaCode=" + areaCode +
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
