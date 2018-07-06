package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.FinancingInfoImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 */
public class FinancingInfoImgDTO extends FinancingInfoImgDO {
    private String url;

    public FinancingInfoImgDTO() {
    }

    public FinancingInfoImgDTO(FinancingInfoImgDO financingInfoImgDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(financingInfoImgDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(financingInfoImgDO.getName())) {
            this.url = CommonFolderConstant.getFinancingWebPath(financingInfoImgDO.getName());
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof FinancingInfoImgDO)) {
            return false;
        }
        FinancingInfoImgDO that = (FinancingInfoImgDO) o;
        return Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
}
