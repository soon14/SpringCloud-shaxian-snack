package cc.likq.base.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@TableName("system_function")
public class SystemFunctionDO extends Model<SystemFunctionDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 类型(0:层级菜单,1:功能按钮)
     */
	private Integer type;
    /**
     * 父功能ID,"0"表示最终父功能
     */
	@TableField("parent_id")
	private Integer parentId;
    /**
     * 菜单名称
     */
	private String name;
    /**
     * 功能唯一标识,命名规则为父节点Code+本节点含义code
     */
	private String code;
    /**
     * 层级
     */
	private Integer level;
    /**
     * 功能的URL
     */
	@TableField("function_url")
	private String functionUrl;
    /**
     * 前端页面路由
     */
	@TableField("route_url")
	private String routeUrl;
    /**
     * 图标
     */
	private String icon;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public String getRouteUrl() {
		return routeUrl;
	}

	public void setRouteUrl(String routeUrl) {
		this.routeUrl = routeUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
		return "SystemFunction{" +
			", id=" + id +
			", type=" + type +
			", parentId=" + parentId +
			", name=" + name +
			", code=" + code +
			", level=" + level +
			", functionUrl=" + functionUrl +
			", routeUrl=" + routeUrl +
			", icon=" + icon +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
