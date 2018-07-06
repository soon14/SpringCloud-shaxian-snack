package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ProductCategoryDO;

import java.util.List;

/**
 * 产品分类
 *
 * @author 泥水佬
 * @date 2017/12/29
 */
public class ProductCategoryDTO extends ProductCategoryDO {
    private List<ProductCategoryDTO> childList;

    public List<ProductCategoryDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<ProductCategoryDTO> childList) {
        this.childList = childList;
    }
}
