package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.AdContentImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

import cc.likq.util.MyBeanUtils;

/**
 * 广告内容图片
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
public class AdContentImgDTO extends AdContentImgDO {
    /***
     * 图片URL
     */
    private String url;

    public AdContentImgDTO() {
    }

    public AdContentImgDTO(Integer id, Integer adContentId, String title, String name, Integer sort, Date gmtModified, Date gmtCreate) {
        super(id, adContentId, title, name, sort, gmtModified, gmtCreate);
        if (StringUtils.isNotBlank(name)) {
            this.url = CommonFolderConstant.getAdWebPath(name);
        }
    }

    public AdContentImgDTO(AdContentImgDO adContentImgDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(adContentImgDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(adContentImgDO.getName())) {
            this.url = CommonFolderConstant.getAdWebPath(adContentImgDO.getName());
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdContentImgDTO that = (AdContentImgDTO) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
