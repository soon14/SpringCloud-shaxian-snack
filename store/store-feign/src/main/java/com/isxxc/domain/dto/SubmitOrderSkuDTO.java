package com.isxxc.domain.dto;

/**
 * 提交订单SKU信息
 *
 * @author 泥水佬
 * @date 2018/2/3
 */
public class SubmitOrderSkuDTO {
    /***
     *SKUID
     */
    private Integer skuId;
    /**
     * 单价
     */
    private Long salePrice;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 总价
     */
    private Long totalPrice;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
