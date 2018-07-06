package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-14
 */
@TableName("user_role_relation")
public class UserRoleRelationDO extends Model<UserRoleRelationDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员与角色关联ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 会员角色code
     */
    @TableField("role_code")
    private String roleCode;
    /**
     * 是否禁用(0:未禁用,1:已禁用)
     */
    @TableField("is_disable")
    private Integer isDisable;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    public UserRoleRelationDO() {
    }

    public UserRoleRelationDO(Integer userId, String roleCode, Integer isDisable) {
        this.userId = userId;
        this.roleCode = roleCode;
        this.isDisable = isDisable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRoleRelationDO that = (UserRoleRelationDO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleCode, that.roleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleCode);
    }

    @Override
    public String toString() {
        return "UserRoleRelation{" +
                ", id=" + id +
                ", userId=" + userId +
                ", roleCode=" + roleCode +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                "}";
    }

}
