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
 * SKU商品库存记录
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-17
 */
@TableName("product_sku_stock_log")
public class ProductSkuStockLogDO extends Model<ProductSkuStockLogDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 库存日志ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /***
     * SKU库存ID
     */
    @TableField("sku_stock_id")
    private Integer skuStockId;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 变更前数量
     */
    @TableField("bofore_num")
    private Integer boforeNum;
    /**
     * 备注
     */
    private String remark;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getBoforeNum() {
        return boforeNum;
    }

    public void setBoforeNum(Integer boforeNum) {
        this.boforeNum = boforeNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getSkuStockId() {
        return skuStockId;
    }

    public void setSkuStockId(Integer skuStockId) {
        this.skuStockId = skuStockId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductSkuStockLog{" +
                ", id=" + id +
                ", num=" + num +
                ", boforeNum=" + boforeNum +
                ", skuStockId=" + skuStockId +
                ", remark=" + remark +
                ", isDeleted=" + isDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
