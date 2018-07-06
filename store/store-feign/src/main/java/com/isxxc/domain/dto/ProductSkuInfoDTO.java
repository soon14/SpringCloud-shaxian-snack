package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ProductSkuInfoDO;
import com.isxxc.domain.entity.ProductSkuPriceMultiDO;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 商品SKU信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public class ProductSkuInfoDTO extends ProductSkuInfoDO {

    /***
     * 商品标题
     */
    private String title;

    /***
     * SKU属性
     */
    private List<ProductAttrInfoDTO> attrInfoList;

    /***
     * 库存ID
     */
    private Integer stockId;

    /***
     * 库存数量
     */
    private Integer stockNum;

    /***
     * 多价格定义
     */
    private List<ProductSkuPriceMultiDO> skuPriceMultiList;

    public ProductSkuInfoDTO() {
        super();
    }

    public ProductSkuInfoDTO(Integer id, Integer spuId, String img, Integer priceType, Long marketPrice, Long salePrice, Integer isShelves, Integer isDeleted, Date gmtModified, Date gmtCreate) {
        super(id, spuId, img, priceType, marketPrice, salePrice, isShelves, isDeleted, gmtModified, gmtCreate);
    }

    public List<ProductAttrInfoDTO> getAttrInfoList() {
        return attrInfoList;
    }

    public void setAttrInfoList(List<ProductAttrInfoDTO> attrInfoList) {
        this.attrInfoList = attrInfoList;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public List<ProductSkuPriceMultiDO> getSkuPriceMultiList() {
        return skuPriceMultiList;
    }

    public void setSkuPriceMultiList(List<ProductSkuPriceMultiDO> skuPriceMultiList) {
        this.skuPriceMultiList = skuPriceMultiList;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductSkuInfoDTO that = (ProductSkuInfoDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
