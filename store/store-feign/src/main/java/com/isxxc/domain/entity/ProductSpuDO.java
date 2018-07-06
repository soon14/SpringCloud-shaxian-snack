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
 * 商品SPU信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@TableName("product_spu")
public class ProductSpuDO extends Model<ProductSpuDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 店铺ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 分类ID
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 品牌ID
     */
    @TableField("brand_id")
    private Integer brandId;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 展示价格
     */
    @TableField("show_price")
    private Long showPrice;
    /**
     * 运费模版ID
     */
    @TableField("freight_template_id")
    private Integer freightTemplateId;
    /**
     * 上下架状态(0:未上架,1:已上架)
     */
    @TableField("is_shelves")
    private Integer isShelves;
    /**
     * 审核状态(0:未审核,1:已通过,2:已驳回)
     */
    @TableField("audit_state")
    private Integer auditState;
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

    public ProductSpuDO() {
    }

    public ProductSpuDO(Integer id, Integer storeId, Integer categoryId, String name, Integer brandId, String title, String subtitle, Long showPrice,
                        Integer freightTemplateId, Integer isShelves, Integer auditState, Integer isDeleted, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.storeId = storeId;
        this.categoryId = categoryId;
        this.name = name;
        this.brandId = brandId;
        this.title = title;
        this.subtitle = subtitle;
        this.showPrice = showPrice;
        this.freightTemplateId = freightTemplateId;
        this.isShelves = isShelves;
        this.auditState = auditState;
        this.isDeleted = isDeleted;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(Integer isShelves) {
        this.isShelves = isShelves;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
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

    public Integer getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Integer freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public Long getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Long showPrice) {
        this.showPrice = showPrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductSpu{" +
                ", id=" + id +
                ", storeId=" + storeId +
                ", categoryId=" + categoryId +
                ", name=" + name +
                ", brandId=" + brandId +
                ", title=" + title +
                ", subtitle=" + subtitle +
                ", isShelves=" + isShelves +
                ", auditState=" + auditState +
                ", isDeleted=" + isDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
