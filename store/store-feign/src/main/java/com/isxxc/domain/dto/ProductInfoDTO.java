package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ProductSpuDO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品SPU信息
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */

public class ProductInfoDTO extends ProductSpuDO {

    /***
     * 分类名称
     */
    private String categoryName;

    /***
     * 品牌名称
     */
    private String brandName;

    /***
     * 产品详情
     */
    private String content;

    /***
     * 产品详情链接
     */
    private String contentUrl;
    /***
     * 运费
     */
    private Long freightPrice;
    /***
     * 销量
     */
    private Integer salesVolume;
    /***
     * 商店号码
     */

    /***
     * 商品图片
     */
    private List<ProductSpuImgDTO> imgList;

    /***
     * 商品SKU
     */
    private List<ProductSkuInfoDTO> skuInfoList;


    public ProductInfoDTO() {
        super();
    }

    public ProductInfoDTO(Integer id, Integer storeId, Integer categoryId, String name, Integer brandId, String title, String subtitle, Long showPrice, Integer freightTemplateId, Integer isShelves, Integer auditState, Integer isDeleted, Date gmtModified, Date gmtCreate, String categoryName, String brandName) {
        super(id, storeId, categoryId, name, brandId, title, subtitle, showPrice, freightTemplateId, isShelves, auditState, isDeleted, gmtModified, gmtCreate);
        this.categoryName = categoryName;
        this.brandName = brandName;

    }

    public List<ProductSpuImgDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ProductSpuImgDTO> imgList) {
        this.imgList = imgList;
    }

    public List<ProductSkuInfoDTO> getSkuInfoList() {
        return skuInfoList;
    }

    public void setSkuInfoList(List<ProductSkuInfoDTO> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Long getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Long freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

}
