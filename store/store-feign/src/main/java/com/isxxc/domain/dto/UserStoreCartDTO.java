package com.isxxc.domain.dto;

import java.util.List;

/**
 * 会员商店购物车信息
 *
 * @author 泥水佬
 * @date 2018/2/4
 */
public class UserStoreCartDTO {
    /***
     * 商店ID
     */
    private Integer storeId;
    /***
     * 商店名称
     */
    private String storeName;
    /***
     * 会员购物车商品信息
     */
    List<UserCartDTO> cartList;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<UserCartDTO> getCartList() {
        return cartList;
    }

    public void setCartList(List<UserCartDTO> cartList) {
        this.cartList = cartList;
    }
}
