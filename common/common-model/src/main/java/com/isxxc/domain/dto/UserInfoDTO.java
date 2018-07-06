package com.isxxc.domain.dto;

import java.util.List;

/**
 * 会员登录信息通用实体
 *
 * @author 泥水佬
 * @date 2017/12/26
 */
public class UserInfoDTO {
    /***
     * 会员ID
     */
    private Integer id;
    /***
     * 当有原料供应商权限时，才存在商店ID
     */
    private Integer storeId;
    /***
     * 昵称
     */
    private String nickname;
    /***
     * 角色权限列表
     */
    private List<String> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
