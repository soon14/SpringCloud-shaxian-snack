package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserShopProfilDO;

import org.apache.commons.lang3.StringUtils;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 */
public class UserShopProfilDTO extends UserShopProfilDO {

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

    public UserShopProfilDTO() {
    }

    public UserShopProfilDTO(UserShopProfilDO userShopProfilDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userShopProfilDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(userShopProfilDO.getBusinessLicenseImg())) {
            this.businessLicenseWebImg = CommonFolderConstant.getUserProfilWebPath(userShopProfilDO.getBusinessLicenseImg());
        }
        if (StringUtils.isNotBlank(userShopProfilDO.getIdentityCardFrontImg())) {
            this.identityCardFrontWebImg = CommonFolderConstant.getUserProfilWebPath(userShopProfilDO.getIdentityCardFrontImg());
        }
        if (StringUtils.isNotBlank(userShopProfilDO.getIdentityCardBackImg())) {
            this.identityCardBackWebImg = CommonFolderConstant.getUserProfilWebPath(userShopProfilDO.getIdentityCardBackImg());
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
