package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ProductFreightTemplateDO;

import java.util.List;

import cc.likq.util.MyBeanUtils;

/**
 * <p>
 * 运费模版
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
public class ProductFreightTemplateDTO extends ProductFreightTemplateDO {

    /***
     * 子项列表
     */
    private List<ProductFreightTemplateItemDTO> freightTemplateItemList;

    public ProductFreightTemplateDTO() {
    }

    public ProductFreightTemplateDTO(ProductFreightTemplateDO productFreightTemplateDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(productFreightTemplateDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductFreightTemplateItemDTO> getFreightTemplateItemList() {
        return freightTemplateItemList;
    }

    public void setFreightTemplateItemList(List<ProductFreightTemplateItemDTO> freightTemplateItemList) {
        this.freightTemplateItemList = freightTemplateItemList;
    }
}
