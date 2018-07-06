package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 会员购物车
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@TableName("user_cart")
public class UserCartDO extends Model<UserCartDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 会员ID
     */
    @TableField("user_id")
    private Integer userId;
    /***
     * 商店ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 价格类型(0:零售价,1:批量价)
     */
    @TableField("price_type")
    private Integer priceType;
    /**
     * SPUID
     */
    @TableField("spu_id")
    private Integer spuId;
    /**
     * SKUID
     */
    @TableField("sku_id")
    private Integer skuId;
    /**
     * 销售价
     */
    @TableField("sale_price")
    private Long salePrice;
    /**
     * 数量
     */
    private Integer num;
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

    public UserCartDO() {
    }

    public UserCartDO(Long id, Integer userId, Integer storeId, Integer priceType, Integer spuId, Integer skuId, Long salePrice, Integer num, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.userId = userId;
        this.storeId = storeId;
        this.priceType = priceType;
        this.spuId = spuId;
        this.skuId = skuId;
        this.salePrice = salePrice;
        this.num = num;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCartDO that = (UserCartDO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(skuId, that.skuId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, skuId);
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserCart{" +
                ", id=" + id +
                ", userId=" + userId +
                ", skuId=" + skuId +
                ", salePrice=" + salePrice +
                ", num=" + num +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
