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
 * 商店提取金额日志
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@TableName("store_extract_amount_log")
public class StoreExtractAmountLogDO extends Model<StoreExtractAmountLogDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商店ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;
    /**
     * 开户名
     */
    @TableField("bank_user_name")
    private String bankUserName;
    /**
     * 银行卡号
     */
    @TableField("bank_no")
    private String bankNo;
    /**
     * 提现金额
     */
    @TableField("extract_amount")
    private Long extractAmount;
    /**
     * 手续费
     */
    private Long poundage;
    /**
     * 结算金额
     */
    @TableField("settlement_amount")
    private Long settlementAmount;
    /**
     * 客服备注
     */
    @TableField("remark_service")
    private String remarkService;
    /**
     * 管理员ID
     */
    @TableField("admin_id")
    private Integer adminId;
    /**
     * 审核状态(0:待审核,1:已通过,2:已驳回)
     */
    @TableField("audit_state")
    private Integer auditState;
    /**
     * 结算状态(0:未结算,1:已结算)
     */
    @TableField("settlement_state")
    private Integer settlementState;
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

    public StoreExtractAmountLogDO() {
    }

    public StoreExtractAmountLogDO(Integer id, Integer storeId, String bankName, String bankUserName, String bankNo, Long extractAmount, Long poundage, Long settlementAmount, String remarkService, Integer adminId, Integer auditState, Integer settlementState, Date gmtModified, Date gmtCreate) {
        this.id = id;
        this.storeId = storeId;
        this.bankName = bankName;
        this.bankUserName = bankUserName;
        this.bankNo = bankNo;
        this.extractAmount = extractAmount;
        this.poundage = poundage;
        this.settlementAmount = settlementAmount;
        this.remarkService = remarkService;
        this.adminId = adminId;
        this.auditState = auditState;
        this.settlementState = settlementState;
        this.gmtModified = gmtModified;
        this.gmtCreate = gmtCreate;
    }

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public Long getExtractAmount() {
        return extractAmount;
    }

    public void setExtractAmount(Long extractAmount) {
        this.extractAmount = extractAmount;
    }

    public Long getPoundage() {
        return poundage;
    }

    public void setPoundage(Long poundage) {
        this.poundage = poundage;
    }

    public Long getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Long settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getRemarkService() {
        return remarkService;
    }

    public void setRemarkService(String remarkService) {
        this.remarkService = remarkService;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Integer getSettlementState() {
        return settlementState;
    }

    public void setSettlementState(Integer settlementState) {
        this.settlementState = settlementState;
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
        return "StoreExtractAmountLog{" +
                ", id=" + id +
                ", storeId=" + storeId +
                ", bankName=" + bankName +
                ", bankUserName=" + bankUserName +
                ", bankNo=" + bankNo +
                ", extractAmount=" + extractAmount +
                ", poundage=" + poundage +
                ", settlementAmount=" + settlementAmount +
                ", remarkService=" + remarkService +
                ", adminId=" + adminId +
                ", auditState=" + auditState +
                ", settlementState=" + settlementState +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
