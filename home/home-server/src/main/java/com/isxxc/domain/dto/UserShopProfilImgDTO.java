package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserShopProfilImgDO;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/29
 */
public class UserShopProfilImgDTO extends UserShopProfilImgDO {
    private String url;

    public UserShopProfilImgDTO() {
    }

    public UserShopProfilImgDTO(UserShopProfilImgDO userShopProfilImgDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userShopProfilImgDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(userShopProfilImgDO.getName())) {
            this.url = CommonFolderConstant.getUserProfilWebPath(userShopProfilImgDO.getName());
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
        if (o == null) {
            return false;
        }
        UserShopProfilImgDO that = (UserShopProfilImgDO) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
