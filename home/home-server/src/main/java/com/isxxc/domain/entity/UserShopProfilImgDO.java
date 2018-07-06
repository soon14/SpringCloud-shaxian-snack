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
 *
 * </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@TableName("user_shop_profil_img")
public class UserShopProfilImgDO extends Model<UserShopProfilImgDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员门店档案照片ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 门店档案照片ID
     */
    @TableField("shop_profil_id")
    private Integer shopProfilId;
    /**
     * 照片类型(0:门店招牌/logo照片,1:门店环境,2:产品信息)
     */
    private Integer type;
    /**
     * 路径
     */
    private String name;
    /**
     * 顺序
     */
    private Integer sort;
    /***
     * 描述
     */
    private String description;
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

    public Integer getShopProfilId() {
        return shopProfilId;
    }

    public void setShopProfilId(Integer shopProfilId) {
        this.shopProfilId = shopProfilId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        UserShopProfilImgDO that = (UserShopProfilImgDO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UserShopProfilImg{" +
                ", id=" + id +
                ", shopProfilId=" + shopProfilId +
                ", type=" + type +
                ", name=" + name +
                ", sort=" + sort +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
