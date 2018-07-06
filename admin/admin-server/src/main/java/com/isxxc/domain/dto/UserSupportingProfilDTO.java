package com.isxxc.domain.dto;

import com.isxxc.domain.entity.UserSupportingProfilDO;

import org.apache.commons.lang3.StringUtils;

import com.isxxc.constant.CommonFolderConstant;
import cc.likq.util.MyBeanUtils;

public class UserSupportingProfilDTO extends UserSupportingProfilDO {

    /**
     * 企业/个体工商户营业执照,WEB路径
     */
    private String businessLicenseWebImg;
    /**
     * 个人身份证照(正面),WEB路径
     */
    private String identityCardFrontWebImg;
    /**
     * 个人身份证照(背面),WEB路径
     */
    private String identityCardBackWebImg;

    public UserSupportingProfilDTO() {
    }

    public UserSupportingProfilDTO(UserSupportingProfilDO userSupportingProfilDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userSupportingProfilDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(userSupportingProfilDO.getBusinessLicenseImg())) {
            this.businessLicenseWebImg = CommonFolderConstant.getUserProfilWebPath(userSupportingProfilDO.getBusinessLicenseImg());
        }
        if (StringUtils.isNotBlank(userSupportingProfilDO.getIdentityCardFrontImg())) {
            this.identityCardFrontWebImg = CommonFolderConstant.getUserProfilWebPath(userSupportingProfilDO.getIdentityCardFrontImg());
        }
        if (StringUtils.isNotBlank(userSupportingProfilDO.getIdentityCardBackImg())) {
            this.identityCardBackWebImg = CommonFolderConstant.getUserProfilWebPath(userSupportingProfilDO.getIdentityCardBackImg());
        }
    }

    public String getBusinessLicenseWebImg() {
        return businessLicenseWebImg;
    }

    public void setBusinessLicenseWebImg(String businessLicenseWebImg) {
        this.businessLicenseWebImg = businessLicenseWebImg;
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
