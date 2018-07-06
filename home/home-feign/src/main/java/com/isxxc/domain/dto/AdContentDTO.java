package com.isxxc.domain.dto;

import com.isxxc.domain.entity.AdContentDO;

import java.util.Date;
import java.util.List;

import cc.likq.util.MyBeanUtils;

/**
 * 广告内容
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
public class AdContentDTO extends AdContentDO {
    private List<AdContentImgDTO> imgList;

    public AdContentDTO() {
    }

    public AdContentDTO(Integer id, Integer adSpaceId, String title, String subtitle, String content, String url, Integer sort, Integer isDeleted, Integer puslishState, Date gmtModified, Date gmtCreate) {
        super(id, adSpaceId, title, subtitle, content, url, sort, isDeleted, puslishState, gmtModified, gmtCreate);
    }

    public AdContentDTO(AdContentDO adContentDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(adContentDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AdContentImgDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<AdContentImgDTO> imgList) {
        this.imgList = imgList;
    }
}
