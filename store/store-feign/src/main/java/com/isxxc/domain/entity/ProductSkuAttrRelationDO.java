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
 * SKU与属性关联
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@TableName("product_sku_attr_relation")
public class ProductSkuAttrRelationDO extends Model<ProductSkuAttrRelationDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * SKUID
     */
    @TableField("sku_id")
    private Integer skuId;
    /**
     * 属性名ID
     */
    @TableField("attr_key_id")
    private Integer attrKeyId;
    /**
     * 属性值ID
     */
    @TableField("attr_value_id")
    private Integer attrValueId;
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

    public ProductSkuAttrRelationDO() {
    }

    public ProductSkuAttrRelationDO(Integer id, Integer skuId, Integer attrKeyId, Integer attrValueId, Integer isDeleted, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.skuId = skuId;
        this.attrKeyId = attrKeyId;
        this.attrValueId = attrValueId;
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

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getAttrKeyId() {
        return attrKeyId;
    }

    public void setAttrKeyId(Integer attrKeyId) {
        this.attrKeyId = attrKeyId;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
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
        return "ProductSkuAttrRelation{" +
                ", id=" + id +
                ", skuId=" + skuId +
                ", attrKeyId=" + attrKeyId +
                ", attrValueId=" + attrValueId +
                ", isDeleted=" + isDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
