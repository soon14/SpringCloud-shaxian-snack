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
 * @since 2017-12-20
 */
@TableName("user_role_function_relation")
public class UserRoleFunctionRelationDO extends Model<UserRoleFunctionRelationDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 功能与角色关系ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 功能ID
     */
    @TableField("function_id")
    private Integer functionId;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    public UserRoleFunctionRelationDO() {
    }

    public UserRoleFunctionRelationDO(Integer functionId, Integer roleId) {
        this.functionId = functionId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
    public String toString() {
        return "UserRoleFunctionRelation{" +
                ", id=" + id +
                ", functionId=" + functionId +
                ", roleId=" + roleId +
                ", gmtCreate=" + gmtCreate +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRoleFunctionRelationDO that = (UserRoleFunctionRelationDO) o;
        return Objects.equals(functionId, that.functionId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(functionId, roleId);
    }
}
