package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ShopTenantDO;

/**
 * @author 泥水佬
 * @date 2017/12/8
 */
public class ShopTenantDTO extends ShopTenantDO {
    /***
     * 排序类型(0:面积,1:月租金,2:最新)
     */
    private Integer sortType;
    /***
     *排序顺序(0:顺序,1:反序)
     */
    private Integer sortAsc = 0;

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Integer getSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(Integer sortAsc) {
        this.sortAsc = sortAsc;
    }
}
