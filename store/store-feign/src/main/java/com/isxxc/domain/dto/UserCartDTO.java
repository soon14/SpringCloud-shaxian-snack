package com.isxxc.domain.dto;

import com.isxxc.domain.entity.UserCartDO;

import java.util.Date;

/**
 * 会员购物车
 *
 * @author 泥水佬
 * @date 2018/1/29
 */
public class UserCartDTO extends UserCartDO {

    private ProductSkuInfoDTO skuInfoDTO;

    /***
     * 主图
     */
    private String mainImgUrl;

    public UserCartDTO() {
    }

    public UserCartDTO(Long id, Integer userId, Integer storeId, Integer priceType, Integer spuId, Integer skuId, Long salePrice, Integer num, Date gmtModified, Date gmtCreate) {
        super(id, userId, storeId, priceType, spuId, skuId, salePrice, num, gmtModified, gmtCreate);
    }

    public ProductSkuInfoDTO getSkuInfoDTO() {
        return skuInfoDTO;
    }

    public void setSkuInfoDTO(ProductSkuInfoDTO skuInfoDTO) {
        this.skuInfoDTO = skuInfoDTO;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }
}
