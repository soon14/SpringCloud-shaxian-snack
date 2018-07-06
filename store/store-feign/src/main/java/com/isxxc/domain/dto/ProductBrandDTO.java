package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.ProductBrandDO;

import org.apache.commons.lang3.StringUtils;

import cc.likq.util.MyBeanUtils;

/**
 * 产品品牌
 *
 * @author 泥水佬
 * @date 2017/12/27
 */
public class ProductBrandDTO extends ProductBrandDO {
    /***
     * LogoUrl
     */
    private String logoWebUrl;

    public ProductBrandDTO() {
    }

    public ProductBrandDTO(ProductBrandDO productBrandDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(productBrandDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(productBrandDO.getLogo())) {
            this.logoWebUrl = CommonFolderConstant.getProductBrandWebPath(productBrandDO.getLogo());
        }
    }

    public String getLogoWebUrl() {
        return logoWebUrl;
    }

    public void setLogoWebUrl(String logoWebUrl) {
        this.logoWebUrl = logoWebUrl;
    }
}
