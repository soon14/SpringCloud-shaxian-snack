package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.OrderItemCommentImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 订单商品评价
 *
 * @author 泥水佬
 * @date 2018/3/23
 */
public class OrderItemCommentImgDTO extends OrderItemCommentImgDO {
    /***
     * 图片URL
     */
    private String url;

    public OrderItemCommentImgDTO() {
    }

    public OrderItemCommentImgDTO(Long id, Long orderItemCommentId, String name, Integer sort, Date gmtModified, Date gmtCreate) {
        super(id, orderItemCommentId, name, sort, gmtModified, gmtCreate);
        if (StringUtils.isNotBlank(name)) {
            this.url = CommonFolderConstant.getProductCommentWebPath(name);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
