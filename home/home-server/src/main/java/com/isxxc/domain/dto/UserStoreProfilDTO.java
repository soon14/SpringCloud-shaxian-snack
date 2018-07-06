package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserStoreProfilDO;

import org.apache.commons.lang3.StringUtils;

import cc.likq.util.MyBeanUtils;

public class UserStoreProfilDTO extends UserStoreProfilDO {

    /**
     * 企业/个体工商户营业执照
     */
    private String individualBusinessLicenseWebImg;
    /**
     * 法人身份证照片(正面)
     */
    private String identityCardFrontWebImg;
    /**
     * 法人身份证照片(背面)
     */
    private String identityCardBackWebImg;
    /**
     * 银行卡正面照
     */
    private String bankWebImg;
    /**
     * 食品经营许可证（食品类需要）
     */
    private String businessLicenseWebImg;

    /**
     * 商店头部图片
     */
    private String bannerWebImg;

    /***
     * 商店Logo
     */
    private String logoWebImg;

    public UserStoreProfilDTO() {
    }

    public UserStoreProfilDTO(UserStoreProfilDO userStoreProfilDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userStoreProfilDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getBusinessLicenseImg())) {
            this.businessLicenseWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getBusinessLicenseImg());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getIdentityCardFrontImg())) {
            this.identityCardFrontWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getIdentityCardFrontImg());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getIdentityCardBackImg())) {
            this.identityCardBackWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getIdentityCardBackImg());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getBankImg())) {
            this.bankWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getBankImg());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getIndividualBusinessLicenseImg())) {
            this.individualBusinessLicenseWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getIndividualBusinessLicenseImg());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getBanner())) {
            this.bannerWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getBanner());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getLogo())) {
            this.logoWebImg = CommonFolderConstant.getUserProfilWebPath(userStoreProfilDO.getLogo());
        }
    }

    public String getIndividualBusinessLicenseWebImg() {
        return individualBusinessLicenseWebImg;
    }

    public void setIndividualBusinessLicenseWebImg(String individualBusinessLicenseWebImg) {
        this.individualBusinessLicenseWebImg = individualBusinessLicenseWebImg;
    }

    public String getIdentityCardFrontWebImg() {
        return identityCardFrontWebImg;
    }

    public void setIdentityCardFrontWebImg(String identityCardFrontWebImg) {
        this.identityCardFrontWebImg = identityCardFrontWebImg;
    }

    public String getIdentityCardBackWebImg() {
        return identityCardBackWebImg;
    }

    public void setIdentityCardBackWebImg(String identityCardBackWebImg) {
        this.identityCardBackWebImg = identityCardBackWebImg;
    }

    public String getBankWebImg() {
        return bankWebImg;
    }

    public void setBankWebImg(String bankWebImg) {
        this.bankWebImg = bankWebImg;
    }

    public String getBusinessLicenseWebImg() {
        return businessLicenseWebImg;
    }

    public void setBusinessLicenseWebImg(String businessLicenseWebImg) {
        this.businessLicenseWebImg = businessLicenseWebImg;
    }

    public String getBannerWebImg() {
        return bannerWebImg;
    }

    public void setBannerWebImg(String bannerWebImg) {
        this.bannerWebImg = bannerWebImg;
    }

    public String getLogoWebImg() {
        return logoWebImg;
    }

    public void setLogoWebImg(String logoWebImg) {
        this.logoWebImg = logoWebImg;
    }
}
