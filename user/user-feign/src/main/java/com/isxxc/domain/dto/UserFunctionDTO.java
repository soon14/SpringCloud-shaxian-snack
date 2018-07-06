package com.isxxc.domain.dto;

import com.isxxc.domain.entity.UserFunctionDO;

import java.util.List;

import cc.likq.util.MyBeanUtils;

/**
 * 功能菜单
 *
 * @author 泥水佬
 * @date 2017/12/21
 */
public class UserFunctionDTO extends UserFunctionDO {
    private List<UserFunctionDTO> childList;

    public UserFunctionDTO() {
    }

    public UserFunctionDTO(UserFunctionDO userFunctionDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userFunctionDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserFunctionDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<UserFunctionDTO> childList) {
        this.childList = childList;
    }
}
