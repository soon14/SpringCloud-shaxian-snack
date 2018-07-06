package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.ProductSpuImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 商品图片
 *
 * @author 泥水佬
 * @date 2018/1/12
 */
public class ProductSpuImgDTO extends ProductSpuImgDO {

    /***
     * 图片路径
     */
    private String url;

    public ProductSpuImgDTO() {
        super();
    }

    public ProductSpuImgDTO(Integer id, Integer spuId, String name, Integer sort, Integer isDeleted, Date gmtModified, Date gmtCreate) {
        super(id, spuId, name, sort, isDeleted, gmtModified, gmtCreate);
        if (StringUtils.isNotBlank(name)) {
            this.url = CommonFolderConstant.getProductSpuImgWebPath(spuId, name);
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductSpuImgDTO that = (ProductSpuImgDTO) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
