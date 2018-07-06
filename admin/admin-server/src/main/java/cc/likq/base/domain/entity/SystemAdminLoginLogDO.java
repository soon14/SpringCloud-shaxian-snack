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
@TableName("system_admin_login_log")
public class SystemAdminLoginLogDO extends Model<SystemAdminLoginLogDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员登录日志
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 会员ID
     */
    @TableField("admin_id")
    private Integer adminId;
    /**
     * 登录IP
     */
    private String ip;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    public SystemAdminLoginLogDO(Integer adminId, String ip) {
        this.adminId = adminId;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        return "SystemAdminLoginLog{" +
                ", id=" + id +
                ", adminId=" + adminId +
                ", ip=" + ip +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
