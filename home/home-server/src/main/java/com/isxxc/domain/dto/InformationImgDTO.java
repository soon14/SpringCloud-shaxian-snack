package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.InformationImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/5
 */
public class InformationImgDTO extends InformationImgDO {
    /***
     * 访问路径
     */
    private String url;

    public InformationImgDTO() {
    }

    public InformationImgDTO(InformationImgDO informationImgDO, String folderName) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(informationImgDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(informationImgDO.getName())) {
            this.url = CommonFolderConstant.getInformationCoverImgWebPath(folderName, informationImgDO.getName());
        }
    }

    public InformationImgDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "InformationImgDTO{" +
                "url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        InformationImgDO that = (InformationImgDO) o;
        return Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
}
