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
 * 商品SKU信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@TableName("product_sku_info")
public class ProductSkuInfoDO extends Model<ProductSkuInfoDO> {

    private static final long serialVersionUID = 1L;

    /**
     * SKUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品ID
     */
    @TableField("spu_id")
    private Integer spuId;
    /**
     * 图片,预留
     */
    private String img;
    /**
     * 价格类型(0:零售价,1:批量价)
     */
    @TableField("price_type")
    private Integer priceType;
    /**
     * 市场价格
     */
    @TableField("market_price")
    private Long marketPrice;
    /**
     * 销售价格
     */
    @TableField("sale_price")
    private Long salePrice;
    /**
     * 上下架状态(0:未上架,1:已上架)
     */
    @TableField("is_shelves")
    private Integer isShelves;
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

    public ProductSkuInfoDO() {
    }

    public ProductSkuInfoDO(Integer id, Integer spuId, String img, Integer priceType, Long marketPrice, Long salePrice, Integer isShelves, Integer isDeleted, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.spuId = spuId;
        this.img = img;
        this.priceType = priceType;
        this.marketPrice = marketPrice;
        this.salePrice = salePrice;
        this.isShelves = isShelves;
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

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(Integer isShelves) {
        this.isShelves = isShelves;
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
        return "ProductSkuInfo{" +
                ", id=" + id +
                ", spuId=" + spuId +
                ", img=" + img +
                ", priceType=" + priceType +
                ", marketPrice=" + marketPrice +
                ", salePrice=" + salePrice +
                ", isShelves=" + isShelves +
                ", isDeleted=" + isDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
