package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ProductSkuAttrRelationDO;

import java.util.Date;
import java.util.Objects;

/**
 * SKU属性详情
 *
 * @author 泥水佬
 * @date 2018/1/12
 */
public class ProductAttrInfoDTO extends ProductSkuAttrRelationDO {

    /***
     * 属性名
     */
    private String attrKeyName;

    /***
     * 属性值名称
     */
    private String attrValueName;

    public ProductAttrInfoDTO() {
        super();
    }

    public ProductAttrInfoDTO(Integer id, Integer skuId, Integer attrKeyId, Integer attrValueId, Integer isDeleted, Date gmtModified, Date gmtCreate, String attrKeyName, String attrValueName) {
        super(id, skuId, attrKeyId, attrValueId, isDeleted, gmtModified, gmtCreate);
        this.attrKeyName = attrKeyName;
        this.attrValueName = attrValueName;
    }

    public String getAttrKeyName() {
        return attrKeyName;
    }

    public void setAttrKeyName(String attrKeyName) {
        this.attrKeyName = attrKeyName;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductAttrInfoDTO that = (ProductAttrInfoDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
