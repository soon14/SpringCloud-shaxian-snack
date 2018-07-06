package com.isxxc.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@TableName("area")
public class AreaDO extends Model<AreaDO> {

    private static final Long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId(value = "code", type = IdType.AUTO)
    private Integer code;
    /**
     * 名称
     */
    private String name;
    /**
     * 父编码
     */
    @TableField("parent_code")
    private Integer parentCode;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.code;
    }

    @Override
    public String toString() {
        return "Area{" +
                ", code=" + code +
                ", name=" + name +
                ", parentCode=" + parentCode +
                "}";
    }
}
