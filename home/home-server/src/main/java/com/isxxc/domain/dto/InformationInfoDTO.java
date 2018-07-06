package com.isxxc.domain.dto;

import com.isxxc.domain.entity.InformationInfoDO;

import java.util.List;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/4
 */
public class InformationInfoDTO extends InformationInfoDO {
    /***
     * 页面内容
     */
    private String content;

    /***
     * 内容链接
     */
    private String pageUrl;

    /***
     * 封面图集
     */
    private List<InformationImgDTO> coverImgList;

    /***
     * 类型名称
     */
    private String typeName;

    public List<InformationImgDTO> getCoverImgList() {
        return coverImgList;
    }

    public InformationInfoDTO() {
    }

    public InformationInfoDTO(InformationInfoDO informationInfoDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(informationInfoDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCoverImgList(List<InformationImgDTO> coverImgList) {
        this.coverImgList = coverImgList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "InformationInfoDTO{" +
                "content='" + content + '\'' +
                ", coverImgList=" + coverImgList +
                '}';
    }
}
