package com.isxxc.domain.entity;

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
 * 会员信息
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-14
 */
@TableName("user_account")
public class UserAccountDO extends Model<UserAccountDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员信息表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员Code
     */
    private String code;

    /**
     * 手机号
     */
    @TableField("mobile_no")
    private String mobileNo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码(前端进行md5加密,后端再进行sha1加密)
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    @TableField("avater_path")
    private String avaterPath;
    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvaterPath() {
        return avaterPath;
    }

    public void setAvaterPath(String avaterPath) {
        this.avaterPath = avaterPath;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
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
        return "UserAccount{" +
                ", id=" + id +
                ", mobileNo=" + mobileNo +
                ", email=" + email +
                ", password=" + password +
                ", nickname=" + nickname +
                ", avaterPath=" + avaterPath +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
