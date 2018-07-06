package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
@TableName("shop_transfer_img")
public class ShopTransferImgDO extends Model<ShopTransferImgDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺转让/求租图片ID
     */
    private Integer id;
    /**
     * 店铺转让/求租ID
     */
    @TableField("transfer_id")
    private Integer transferId;
    /**
     * 路径
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
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

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShopTransferImg{" +
                ", id=" + id +
                ", transferId=" + transferId +
                ", name=" + name +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
