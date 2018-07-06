package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.ShopTransferImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/7
 */
public class ShopTransferImgDTO extends ShopTransferImgDO {
    /***
     * Web访问路径
     */
    private String url;

    public ShopTransferImgDTO() {
    }

    public ShopTransferImgDTO(ShopTransferImgDO shopTransferImgDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(shopTransferImgDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(shopTransferImgDO.getName())) {
            this.url = CommonFolderConstant.getShopTransferWebPath(shopTransferImgDO.getName());
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof ShopTransferImgDO)) {
            return false;
        }
        ShopTransferImgDO that = (ShopTransferImgDO) o;
        return Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
}
