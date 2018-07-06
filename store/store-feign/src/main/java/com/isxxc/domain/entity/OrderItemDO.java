package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单子项
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@TableName("order_item")
public class OrderItemDO extends Model<OrderItemDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单子项ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单ID
     */
    @TableField("order_info_id")
    private Integer orderInfoId;
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
     * 商品SPU标题
     */
    @TableField("spu_title")
    private String spuTitle;
    /**
     * 单价
     */
    @TableField("sale_price")
    private Long salePrice;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 总价
     */
    @TableField("total_price")
    private Long totalPrice;
    /**
     * 评价状态:(0:待评价、1:已评价)
     */
    @TableField("comment_state")
    private Integer commentState;
    /**
     * 图片URL
     */
    private String img;
    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;

    public OrderItemDO() {
    }

    public OrderItemDO(Integer id, Integer orderInfoId, Integer skuId, String spuTitle, Long salePrice, Integer num, Long totalPrice, Integer commentState, String img, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.orderInfoId = orderInfoId;
        this.skuId = skuId;
        this.spuTitle = spuTitle;
        this.salePrice = salePrice;
        this.num = num;
        this.totalPrice = totalPrice;
        this.commentState = commentState;
        this.img = img;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
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

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getSpuTitle() {
        return spuTitle;
    }

    public void setSpuTitle(String spuTitle) {
        this.spuTitle = spuTitle;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                ", id=" + id +
                ", orderInfoId=" + orderInfoId +
                ", skuId=" + skuId +
                ", salePrice=" + salePrice +
                ", num=" + num +
                ", totalPrice=" + totalPrice +
                ", commentState=" + commentState +
                ", img=" + img +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
