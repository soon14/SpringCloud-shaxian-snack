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
 * 商店提取金额审核日志
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@TableName("store_extract_amount_audit_log")
public class StoreExtractAmountAuditLogDO extends Model<StoreExtractAmountAuditLogDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 提取金额日志ID
     */
    @TableField("extract_amount_log_id")
    private Integer extractAmountLogId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核状态(0:驳回,1:通过)
     */
    @TableField("audit_state")
    private Integer auditState;
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

    public Integer getExtractAmountLogId() {
        return extractAmountLogId;
    }

    public void setExtractAmountLogId(Integer extractAmountLogId) {
        this.extractAmountLogId = extractAmountLogId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
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
        return "StoreExtractAmountAuditLog{" +
                ", id=" + id +
                ", extractAmountLogId=" + extractAmountLogId +
                ", remark=" + remark +
                ", auditState=" + auditState +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
