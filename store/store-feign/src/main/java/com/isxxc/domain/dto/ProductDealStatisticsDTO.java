package com.isxxc.domain.dto;


/***
 * <p>
 *  商品成交信息统计 实体
 * </p>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */

public class ProductDealStatisticsDTO {

    /***
     * 下单日期
     */
    private String modifiedDate;

    /***
     * 商品名称
     */
    private String productName;

    /***
     * 商品成交数
     */
    private Integer productDealCount;

    /***
     * 商品成交额
     */
    private String productDealAmount;

    public String getModifiedDate() { return modifiedDate; }

    public void setModifiedDate(String modifiedDate) { this.modifiedDate = modifiedDate; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public Integer getProductDealCount() {
        return productDealCount;
    }

    public void setProductDealCount(Integer productDealCount) {
        this.productDealCount = productDealCount;
    }

    public String getProductDealAmount() {
        return productDealAmount;
    }

    public void setProductDealAmount(String productDealAmount) {
        this.productDealAmount = productDealAmount;
    }
}
