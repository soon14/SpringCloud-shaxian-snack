package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.LoansInfoDO;

import org.apache.commons.lang3.StringUtils;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 */
public class LoansInfoDTO extends LoansInfoDO {
    /***
     * 公司名称
     */
    private String companyName;

    private String adImgWebPath;

    public String getAdImgWebPath() {
        return adImgWebPath;
    }

    public void setAdImgWebPath(String adImgWebPath) {
        if (StringUtils.isNotBlank(adImgWebPath)) {
            this.adImgWebPath = CommonFolderConstant.getLoansInfoWebPath(adImgWebPath);
        }
    }

    public LoansInfoDTO() {
    }

    public LoansInfoDTO(LoansInfoDO loansInfoDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(loansInfoDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
