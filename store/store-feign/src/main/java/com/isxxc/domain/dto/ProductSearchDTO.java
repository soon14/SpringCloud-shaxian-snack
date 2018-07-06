package com.isxxc.domain.dto;

import java.util.List;

/**
 * 商品检索
 *
 * @author 泥水佬
 * @date 2018/1/31
 */
public class ProductSearchDTO {
    /***
     * 分页实体
     */
    private Pager pager;
    /***
     * 关键字
     */
    private String keyword;
    /***
     * 分类ID
     */
    private Integer categoryId;
    /***
     * 最小价格
     */
    private Long minPrice;
    /***
     * 最大价格
     */
    private Long maxPrice;
    /**
     * 省名称
     */
    private Integer provinceCode;
    /**
     * 市名称
     */
    private Integer cityCode;
    /**
     * 区名称
     */
    private Integer areaCode;

    /***
     * 分类ID集
     */
    private List<Integer> categoryIdList;
    /***
     * 商店ID
     */
    private Integer storeId;
    /***
     * 商店ID集
     */
    private List<Integer> storeIdList;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public List<Integer> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Integer> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public List<Integer> getStoreIdList() {
        return storeIdList;
    }

    public void setStoreIdList(List<Integer> storeIdList) {
        this.storeIdList = storeIdList;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
