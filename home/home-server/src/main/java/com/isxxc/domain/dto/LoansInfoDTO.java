package com.isxxc.domain.dto;

import com.isxxc.domain.entity.LoansInfoDO;

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

    public LoansInfoDTO() {
    }

    public LoansInfoDTO(LoansInfoDO loansInfoDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(loansInfoDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAdImgWebPath() {
        return adImgWebPath;
    }

    public void setAdImgWebPath(String adImgWebPath) {
        this.adImgWebPath = adImgWebPath;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
