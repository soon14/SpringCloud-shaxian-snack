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
 * 
 * </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@TableName("user_supporting_audit_log")
public class UserSupportingAuditLogDO extends Model<UserSupportingAuditLogDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员门店商户档案审核ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 会员档案审核ID
     */
	@TableField("supporting_profil_id")
	private Integer supportingProfilId;
    /**
     * 状态(0:通过,1:驳回)
     */
	@TableField("audit_state")
	private Integer auditState;
    /**
     * 内容
     */
	private String content;
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

	public Integer getSupportingProfilId() {
		return supportingProfilId;
	}

	public void setSupportingProfilId(Integer supportingProfilId) {
		this.supportingProfilId = supportingProfilId;
	}

	public Integer getAuditState() {
		return auditState;
	}

	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "UserSupportingAuditLog{" +
			", id=" + id +
			", supportingProfilId=" + supportingProfilId +
			", auditState=" + auditState +
			", content=" + content +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
