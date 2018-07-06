package com.isxxc.domain.dto;

import com.isxxc.domain.entity.UserRoleDO;

/**
 * @author 吴坚
 */
public class UserRoleDTO extends UserRoleDO{
    /***
     * 门店商户统计数
     */
    private Integer countShop;
    /***
     * 原料供应商统计数
     */
    private Integer countStore;
    /***
     * 配套服务/文化服务统计数
     */
    private Integer countSupporting;
    /***
     * 金融服务统计数
     */
    private Integer countBanking;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Integer countShop, Integer countStore, Integer countSupporting, Integer countBanking) {
        this.countShop = countShop;
        this.countStore = countStore;
        this.countSupporting = countSupporting;
        this.countBanking = countBanking;
    }

    public Integer getCountShop() {
        return countShop;
    }

    public void setCountShop(Integer countShop) {
        this.countShop = countShop;
    }

    public Integer getCountStore() {
        return countStore;
    }

    public void setCountStore(Integer countStore) {
        this.countStore = countStore;
    }

    public Integer getCountSupporting() {
        return countSupporting;
    }

    public void setCountSupporting(Integer countSupporting) {
        this.countSupporting = countSupporting;
    }

    public Integer getCountBanking() {
        return countBanking;
    }

    public void setCountBanking(Integer countBanking) {
        this.countBanking = countBanking;
    }



}
