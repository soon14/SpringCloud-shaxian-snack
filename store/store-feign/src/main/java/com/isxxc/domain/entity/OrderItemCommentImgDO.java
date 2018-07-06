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
 * 订单商品评价图片
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-23
 */
@TableName("order_item_comment_img")
public class OrderItemCommentImgDO extends Model<OrderItemCommentImgDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单商品评价ID
     */
    @TableField("order_item_comment_id")
    private Long orderItemCommentId;
    /**
     * 图片名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
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

    public OrderItemCommentImgDO() {
    }

    public OrderItemCommentImgDO(Long id, Long orderItemCommentId, String name, Integer sort, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.orderItemCommentId = orderItemCommentId;
        this.name = name;
        this.sort = sort;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderItemCommentId() {
        return orderItemCommentId;
    }

    public void setOrderItemCommentId(Long orderItemCommentId) {
        this.orderItemCommentId = orderItemCommentId;
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

    @Override
    public String toString() {
        return "OrderItemCommentImg{" +
                ", id=" + id +
                ", orderItemCommentId=" + orderItemCommentId +
                ", name=" + name +
                ", sort=" + sort +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
