package com.isxxc.domain.dto;

import com.isxxc.domain.entity.FinancingInfoDO;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 */
public class FinancingInfoDTO extends FinancingInfoDO {

    /***
     * 最小金额
     */
    private BigDecimal amountMin;
    /***
     * 最大金额
     */
    private BigDecimal amountMax;

    private List<FinancingInfoImgDTO> imgList;

    public FinancingInfoDTO() {
    }

    public FinancingInfoDTO(FinancingInfoDO financingInfoDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(financingInfoDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(BigDecimal amountMin) {
        this.amountMin = amountMin;
    }

    public BigDecimal getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(BigDecimal amountMax) {
        this.amountMax = amountMax;
    }

    public List<FinancingInfoImgDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<FinancingInfoImgDTO> imgList) {
        this.imgList = imgList;
    }
}
