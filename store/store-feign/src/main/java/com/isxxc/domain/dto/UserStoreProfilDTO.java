package com.isxxc.domain.dto;

import com.isxxc.domain.entity.UserStoreProfilDO;

import java.util.Date;

/**
 * 商店信息
 *
 * @author 泥水佬
 * @date 2018/1/31
 */
public class UserStoreProfilDTO extends UserStoreProfilDO {
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 区名称
     */
    private String areaName;

    public UserStoreProfilDTO() {
    }

    public UserStoreProfilDTO(Integer id, Integer userId, String companyName, Integer provinceCode, Integer cityCode, Integer areaCode, String address, String contact, String mobileNo, String individualBusinessLicenseImg, String identityCardFrontImg, String identityCardBackImg, String bankImg, String businessLicenseImg, String bankUserName, String bankName, String bankNo, String logo, String description, Integer auditState, String banner, Date gmtModified, Date gmtCreate, String provinceName, String cityName, String areaName) {
        super(id, userId, companyName, provinceCode, cityCode, areaCode, address, contact, mobileNo, individualBusinessLicenseImg, identityCardFrontImg, identityCardBackImg, bankImg, businessLicenseImg, bankUserName, bankName, bankNo, logo, description, auditState, banner, gmtModified, gmtCreate);
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.areaName = areaName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
