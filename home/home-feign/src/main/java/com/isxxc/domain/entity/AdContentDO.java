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
 * 广告内容
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@TableName("ad_content")
public class AdContentDO extends Model<AdContentDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 广告位ID
     */
    @TableField("ad_space_id")
    private Integer adSpaceId;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 内容
     */
    private String content;
    /**
     * URL
     */
    private String url;
    /***
     * 排序
     */
    private Integer sort;
    /**
     * 删除状态(0:未删除,1:已删除)
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /***
     *发布状态(0:未发布,1:已发布,2:已下线)
     */
    @TableField("publish_state")
    private Integer publishState;
    /***
     *发布时间
     */
    @TableField("publish_time")
    private Date publishTime;
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

    public AdContentDO() {
    }

    public AdContentDO(Integer id, Integer adSpaceId, String title, String subtitle, String content, String url, Integer sort, Integer isDeleted, Integer publishState, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.adSpaceId = adSpaceId;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.url = url;
        this.sort = sort;
        this.isDeleted = isDeleted;
        this.publishState = publishState;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdSpaceId() {
        return adSpaceId;
    }

    public void setAdSpaceId(Integer adSpaceId) {
        this.adSpaceId = adSpaceId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPublishState() {
        return publishState;
    }

    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AdContent{" +
                ", id=" + id +
                ", adSpaceId=" + adSpaceId +
                ", title=" + title +
                ", subtitle=" + subtitle +
                ", content=" + content +
                ", url=" + url +
                ", isDeleted=" + isDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
