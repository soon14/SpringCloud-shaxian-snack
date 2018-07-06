package com.isxxc.domain.dto;



/***
 * <p>
 *  商品数量信息统计 实体
 * </p>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */

public class ProductNumStatisticsDTO {

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
     * 商品数量(库存)
     */
    private Integer productCount;

    /***
     * 库存变更前数量
     */
    private Integer beforeNum;


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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getBeforeNum() {
        return beforeNum;
    }

    public void setBeforeNum(Integer beforeNum) {
        this.beforeNum = beforeNum;
    }
}
