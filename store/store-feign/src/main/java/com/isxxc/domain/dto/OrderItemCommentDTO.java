package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.domain.entity.OrderItemCommentDO;

import java.util.Date;
import java.util.List;

/**
 * 订单商品评论
 *
 * @author 泥水佬
 * @date 2018/3/23
 */
public class OrderItemCommentDTO extends OrderItemCommentDO {
    /***
     * 会员昵称
     */
    private String nickname;
    /***
     * 会员头像
     */
    private String avaterPath;
    /***
     * 商品属性
     */
    private List<OrderItemAttrDO> attrList;

    public OrderItemCommentDTO() {
    }

    public OrderItemCommentDTO(String nickname, String avaterPath, List<OrderItemAttrDO> attrList, List<OrderItemCommentImgDTO> commentImgList) {
        this.nickname = nickname;
        this.avaterPath = avaterPath;
        this.attrList = attrList;
        this.commentImgList = commentImgList;
    }

    public OrderItemCommentDTO(Long id, Integer orderItemId, Integer storeId, Integer userId, Integer spuId, Integer skuId, Integer describeScore, Integer serviceScore, String comment, Date gmtModified, Date gmtCreated, String reply, String storeName, String productName, String price, String nickName, String nickname, String avaterPath, List<OrderItemAttrDO> attrList, List<OrderItemCommentImgDTO> commentImgList) {
        super(id, orderItemId, storeId, userId, spuId, skuId, describeScore, serviceScore, comment, gmtModified, gmtCreated, reply, storeName, productName, price, nickName);
        this.nickname = nickname;
        this.avaterPath = avaterPath;
        this.attrList = attrList;
        this.commentImgList = commentImgList;
    }

    /***
     * 图片集
     */
    private List<OrderItemCommentImgDTO> commentImgList;

    public List<OrderItemCommentImgDTO> getCommentImgList() {
        return commentImgList;
    }

    public void setCommentImgList(List<OrderItemCommentImgDTO> commentImgList) {
        this.commentImgList = commentImgList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvaterPath() {
        return avaterPath;
    }

    public void setAvaterPath(String avaterPath) {
        this.avaterPath = avaterPath;
    }

    public List<OrderItemAttrDO> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<OrderItemAttrDO> attrList) {
        this.attrList = attrList;
    }
}
