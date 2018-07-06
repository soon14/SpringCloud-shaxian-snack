package com.isxxc.domain.dto;


/***
 * <p>
 *  商品上下架信息统计 实体
 * </p>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */

public class ProductShelvesStatisticsDTO {

    /***
     * 创建日期
     */
    private String createDate;

    /***
     * 更改日期
     */
    private String modifiedDate;

    /***
     * 商品名称
     */
    private String productName;

    /***
     * 商品上架数
     */
    private Integer onShelvesCount;

    /***
     * 商品下架数
     */
    private Integer offShelvesCount;

    public String getCreateDate() { return createDate; }

    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public String getModifiedDate() { return modifiedDate; }

    public void setModifiedDate(String modifiedDate) { this.modifiedDate = modifiedDate; }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOnShelvesCount() {
        return onShelvesCount;
    }

    public void setOnShelvesCount(Integer onShelvesCount) {
        this.onShelvesCount = onShelvesCount;
    }

    public Integer getOffShelvesCount() {
        return offShelvesCount;
    }

    public void setOffShelvesCount(Integer offShelvesCount) {
        this.offShelvesCount = offShelvesCount;
    }
}
