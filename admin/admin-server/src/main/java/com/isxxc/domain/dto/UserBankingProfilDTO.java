package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserBankingProfilDO;

import org.apache.commons.lang3.StringUtils;

import cc.likq.util.MyBeanUtils;

public class UserBankingProfilDTO extends UserBankingProfilDO {

    /**
     * 经营许可证
     */
    private String businessLicenseWebImg;
    /**
     * Logo图片
     */
    private String logoWeb;
    /**
     * 在职/代理机构证证明
     */
    private String agencyCertificateWebImg;
    /**
     * 法人身份证照片(正面)
     */
    private String identityCardFrontWebImg;
    /**
     * 法人身份证照片(背面)
     */
    private String identityCardBackWebImg;

    public UserBankingProfilDTO() {
    }

    public UserBankingProfilDTO(UserBankingProfilDO userBankingProfilDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userBankingProfilDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getBusinessLicenseImg())) {
            this.businessLicenseWebImg = CommonFolderConstant.getUserProfilWebPath(userBankingProfilDO.getBusinessLicenseImg());
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getIdentityCardFrontImg())) {
            this.identityCardFrontWebImg = CommonFolderConstant.getUserProfilWebPath(userBankingProfilDO.getIdentityCardFrontImg());
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getIdentityCardBackImg())) {
            this.identityCardBackWebImg = CommonFolderConstant.getUserProfilWebPath(userBankingProfilDO.getIdentityCardBackImg());
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getLogo())) {
            this.logoWeb = CommonFolderConstant.getUserProfilWebPath(userBankingProfilDO.getLogo());
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getAgencyCertificateImg())) {
            this.agencyCertificateWebImg = CommonFolderConstant.getUserProfilWebPath(userBankingProfilDO.getAgencyCertificateImg());
        }
    }

    public String getBusinessLicenseWebImg() {
        return businessLicenseWebImg;
    }

    public void setBusinessLicenseWebImg(String businessLicenseWebImg) {
        this.businessLicenseWebImg = businessLicenseWebImg;
    }

    public String getLogoWeb() {
        return logoWeb;
    }

    public void setLogoWeb(String logoWeb) {
        this.logoWeb = logoWeb;
    }

    public String getAgencyCertificateWebImg() {
        return agencyCertificateWebImg;
    }

    public void setAgencyCertificateWebImg(String agencyCertificateWebImg) {
        this.agencyCertificateWebImg = agencyCertificateWebImg;
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
}
