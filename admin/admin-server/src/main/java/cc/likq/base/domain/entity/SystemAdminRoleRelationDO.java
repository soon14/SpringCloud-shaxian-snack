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
@TableName("system_admin_role_relation")
public class SystemAdminRoleRelationDO extends Model<SystemAdminRoleRelationDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 帐户与角色关联ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 帐户ID
     */
    @TableField("admin_id")
    private Integer adminId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
        return "SystemAdminRoleRelation{" +
                ", id=" + id +
                ", adminId=" + adminId +
                ", roleId=" + roleId +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
