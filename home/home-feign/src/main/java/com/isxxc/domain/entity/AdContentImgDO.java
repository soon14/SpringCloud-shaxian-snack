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
 * 广告内容图片
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@TableName("ad_content_img")
public class AdContentImgDO extends Model<AdContentImgDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 广告内容ID
     */
    @TableField("ad_content_id")
    private Integer adContentId;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片名称
     */
    private String name;
    /***
     * 排序
     */
    private Integer sort;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    public AdContentImgDO() {
    }

    public AdContentImgDO(Integer id, Integer adContentId, String title, String name, Integer sort, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.adContentId = adContentId;
        this.title = title;
        this.name = name;
        this.sort = sort;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdContentId() {
        return adContentId;
    }

    public void setAdContentId(Integer adContentId) {
        this.adContentId = adContentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "AdContentImg{" +
                ", id=" + id +
                ", adContentId=" + adContentId +
                ", title=" + title +
                ", name=" + name +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
