package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.domain.entity.OrderItemDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 订单详情项
 *
 * @author 泥水佬
 * @date 2018/2/4
 */
public class OrderItemDTO extends OrderItemDO {
    /***
     * 主图URL
     */
    private String imgUrl;
    /***
     * 属性信息
     */
    private List<OrderItemAttrDO> itemAttrList;

    public List<OrderItemAttrDO> getItemAttrList() {
        return itemAttrList;
    }

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer id, Integer orderInfoId, Integer skuId, String spuTitle, Long salePrice, Integer num, Long totalPrice, Integer commentState, String img, Date gmtModified, Date gmtCreate) {
        super(id, orderInfoId, skuId, spuTitle, salePrice, num, totalPrice, commentState, img, gmtModified, gmtCreate);
        if (StringUtils.isNotBlank(img)) {
            this.imgUrl = CommonFolderConstant.getOrderInfoImgWebPath(orderInfoId, img);
        }
    }

    public void setItemAttrList(List<OrderItemAttrDO> itemAttrList) {
        this.itemAttrList = itemAttrList;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
