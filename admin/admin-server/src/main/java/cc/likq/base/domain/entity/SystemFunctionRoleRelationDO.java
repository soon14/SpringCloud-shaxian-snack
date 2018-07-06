package cc.likq.base.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@TableName("system_function_role_relation")
public class SystemFunctionRoleRelationDO extends Model<SystemFunctionRoleRelationDO> {

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

    public SystemFunctionRoleRelationDO() {
    }

    public SystemFunctionRoleRelationDO(Integer functionId, Integer roleId) {
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
        return "SystemFunctionRoleRelation{" +
                ", id=" + id +
                ", functionId=" + functionId +
                ", roleId=" + roleId +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
