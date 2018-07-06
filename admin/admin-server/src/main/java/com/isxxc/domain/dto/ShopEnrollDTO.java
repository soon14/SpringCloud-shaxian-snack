package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ShopEnrollDO;

import org.apache.commons.lang3.StringUtils;

import com.isxxc.constant.CommonFolderConstant;
import cc.likq.util.MyBeanUtils;

public class ShopEnrollDTO extends ShopEnrollDO {

    private String shopFacadeWebImg;

    private String shopHallWebImg;

    private String hotMenuWebImg;

    private String bankCardWebImg;

    private String identityCardWebImg;

    private String businessLicenseWebImg;
    private String identityCardBackWebImg;
    /**
     * 银行卡反面照片
     */
    private String bankCardBackWebImg;

    /**
     * 收银台照片
     */
    private String cashierDeskWebImg;

    /**
     * 餐饮服务许可证照片
     */
    private String cateringServiceLicenseWebImg;

    public ShopEnrollDTO(ShopEnrollDO ShopEnrollDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(ShopEnrollDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getBankCardImg())) {
            this.bankCardWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getBankCardImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getBusinessLicenseImg())) {
            this.businessLicenseWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getBusinessLicenseImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getHotMenuImg())) {
            this.hotMenuWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getHotMenuImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getIdentityCardImg())) {
            this.identityCardWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getIdentityCardImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getShopFacadeImg())) {
            this.shopFacadeWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getShopFacadeImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getShopHallImg())) {
            this.shopHallWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getShopHallImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getIdentityCardBackImg())) {
            this.identityCardBackWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getIdentityCardBackImg());
        }

        if (StringUtils.isNotBlank(ShopEnrollDO.getBankCardBackImg())) {
            this.bankCardBackWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getBankCardBackImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getCashierDeskImg())) {
            this.cashierDeskWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getCashierDeskImg());
        }
        if (StringUtils.isNotBlank(ShopEnrollDO.getCateringServiceLicenseImg())) {
            this.cateringServiceLicenseWebImg = CommonFolderConstant.getShopEnrollWebPath(ShopEnrollDO.getCateringServiceLicenseImg());
        }

    }

    public String getShopFacadeWebImg() {
        return shopFacadeWebImg;
    }

    public void setShopFacadeWebImg(String shopFacadeWebImg) {
        this.shopFacadeWebImg = shopFacadeWebImg;
    }

    public String getShopHallWebImg() {
        return shopHallWebImg;
    }

    public void setShopHallWebImg(String shopHallWebImg) {
        this.shopHallWebImg = shopHallWebImg;
    }

    public String getHotMenuWebImg() {
        return hotMenuWebImg;
    }

    public void setHotMenuWebImg(String hotMenuWebImg) {
        this.hotMenuWebImg = hotMenuWebImg;
    }

    public String getBankCardWebImg() {
        return bankCardWebImg;
    }

    public void setBankCardWebImg(String bankCardWebImg) {
        this.bankCardWebImg = bankCardWebImg;
    }

    public String getIdentityCardWebImg() {
        return identityCardWebImg;
    }

    public void setIdentityCardWebImg(String identityCardWebImg) {
        this.identityCardWebImg = identityCardWebImg;
    }

    public String getBusinessLicenseWebImg() {
        return businessLicenseWebImg;
    }

    public void setBusinessLicenseWebImg(String businessLicenseWebImg) {
        this.businessLicenseWebImg = businessLicenseWebImg;
    }

    public String getIdentityCardBackWebImg() {
        return identityCardBackWebImg;
    }

    public void setIdentityCardBackWebImg(String identityCardBackWebImg) {
        this.identityCardBackWebImg = identityCardBackWebImg;
    }

    public String getBankCardBackWebImg() {
        return bankCardBackWebImg;
    }

    public void setBankCardBackWebImg(String bankCardBackWebImg) {
        this.bankCardBackWebImg = bankCardBackWebImg;
    }

    public String getCashierDeskWebImg() {
        return cashierDeskWebImg;
    }

    public void setCashierDeskWebImg(String cashierDeskWebImg) {
        this.cashierDeskWebImg = cashierDeskWebImg;
    }

    public String getCateringServiceLicenseWebImg() {
        return cateringServiceLicenseWebImg;
    }

    public void setCateringServiceLicenseWebImg(String cateringServiceLicenseWebImg) {
        this.cateringServiceLicenseWebImg = cateringServiceLicenseWebImg;
    }
}
