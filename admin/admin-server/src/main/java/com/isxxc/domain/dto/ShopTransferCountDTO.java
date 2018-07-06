package com.isxxc.domain.dto;


import com.isxxc.domain.entity.ShopTransferDO;

/**
 * @author 吴坚
 * @since 2018-05-22
 */
public class ShopTransferCountDTO extends ShopTransferDO {
    /***
     * 盘店寻租-旺铺转让  统计
     */
    private Integer countAttornShop;
    /***
     * 盘店寻租-求租旺铺  统计
     */
    private Integer countRentingShop;


    public ShopTransferCountDTO(Integer countAttornShop, Integer countRentingShop) {
        this.countAttornShop = countAttornShop;
        this.countRentingShop = countRentingShop;
    }

    public ShopTransferCountDTO() {
    }


    public Integer getCountAttornShop() {
        return countAttornShop;
    }

    public void setCountAttornShop(Integer countAttornShop) {
        this.countAttornShop = countAttornShop;
    }

    public Integer getCountRentingShop() {
        return countRentingShop;
    }

    public void setCountRentingShop(Integer countRentingShop) {
        this.countRentingShop = countRentingShop;
    }

}
