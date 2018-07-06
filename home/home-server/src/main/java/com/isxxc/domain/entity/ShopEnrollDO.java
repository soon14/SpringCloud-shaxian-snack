package com.isxxc.domain.entity;

import java.io.Serializable;

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
 * @since 2017-11-27
 */
@TableName("shop_enroll")
public class ShopEnrollDO extends Model<ShopEnrollDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺报名ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 姓名
     */
	private String name;
    /**
     * 联系方式
     */
	@TableField("mobile_no")
	private String mobileNo;
    /**
     * 门店名称
     */
	@TableField("shop_name")
	private String shopName;
    /**
     * 门店地址
     */
	@TableField("shop_address")
	private String shopAddress;
    /**
     * 门店店面照片
     */
	@TableField("shop_facade_img")
	private String shopFacadeImg;
    /**
     * 门店厅堂照片
     */
	@TableField("shop_hall_img")
	private String shopHallImg;
    /**
     * 热销菜单照片
     */
	@TableField("hot_menu_img")
	private String hotMenuImg;
    /**
     * 门店职工人数
     */
	@TableField("shop_staff")
	private Integer shopStaff;
    /**
     * 餐厅面积
     */
	@TableField("shop_area")
	private Integer shopArea;
    /**
     * 年营业额
     */
	@TableField("annual_sales_volume")
	private Integer annualSalesVolume;
    /**
     * 餐厅桌台数
     */
	@TableField("dining_table_number")
	private Integer diningTableNumber;
    /**
     * 是否有POS机(0:无,1:有)
     */
	@TableField("pos_status")
	private Integer posStatus;
    /**
     * POS品牌
     */
	@TableField("pos_brand")
	private String posBrand;
    /**
     * 喜欢POS还是扫码点餐(0:POS,1:扫码)
     */
	@TableField("order_dishes_type")
	private Integer orderDishesType;
    /**
     * 收银方式(0:现金,1:微信,2:支付宝,3:银行卡)
     */
	@TableField("gathering_types")
	private String gatheringTypes;
    /**
     * 银行卡号
     */
	@TableField("bank_no")
	private String bankNo;
    /**
     * 开户行名称
     */
	@TableField("bank_name")
	private String bankName;
    /**
     * 银行卡照片
     */
	@TableField("bank_card_img")
	private String bankCardImg;
    /**
     * 身份证背面照片
     */
	@TableField("identity_card_back_img")
	private String identityCardBackImg;
    /**
     * 身份证照片
     */
	@TableField("identity_card_img")
	private String identityCardImg;
    /**
     * 留言
     */
	@TableField("leave_message")
	private String leaveMessage;
    /**
     * 营业执照照片
     */
	@TableField("business_license_img")
	private String businessLicenseImg;
    /**
     * 删除(0:未删除,1:已删除)
     */
	@TableField("is_deleted")
	private Integer isDeleted;
    /**
     * 银行卡反面照片
     */
	@TableField("bank_card_back_img")
	private String bankCardBackImg;
    /**
     * 收银台照片
     */
	@TableField("cashier_desk_img")
	private String cashierDeskImg;
    /**
     * 餐饮服务许可证照片
     */
	@TableField("catering_service_license_img")
	private String cateringServiceLicenseImg;
    /**
     * 更新时间
     */
	@TableField("gmt_modified")
	private Date gmtModified;
    /**
     * 创建时间
     */
	@TableField("gmt_create")
	private Date gmtCreate;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopFacadeImg() {
		return shopFacadeImg;
	}

	public void setShopFacadeImg(String shopFacadeImg) {
		this.shopFacadeImg = shopFacadeImg;
	}

	public String getShopHallImg() {
		return shopHallImg;
	}

	public void setShopHallImg(String shopHallImg) {
		this.shopHallImg = shopHallImg;
	}

	public String getHotMenuImg() {
		return hotMenuImg;
	}

	public void setHotMenuImg(String hotMenuImg) {
		this.hotMenuImg = hotMenuImg;
	}

	public Integer getShopStaff() {
		return shopStaff;
	}

	public void setShopStaff(Integer shopStaff) {
		this.shopStaff = shopStaff;
	}

	public Integer getShopArea() {
		return shopArea;
	}

	public void setShopArea(Integer shopArea) {
		this.shopArea = shopArea;
	}

	public Integer getAnnualSalesVolume() {
		return annualSalesVolume;
	}

	public void setAnnualSalesVolume(Integer annualSalesVolume) {
		this.annualSalesVolume = annualSalesVolume;
	}

	public Integer getDiningTableNumber() {
		return diningTableNumber;
	}

	public void setDiningTableNumber(Integer diningTableNumber) {
		this.diningTableNumber = diningTableNumber;
	}

	public Integer getPosStatus() {
		return posStatus;
	}

	public void setPosStatus(Integer posStatus) {
		this.posStatus = posStatus;
	}

	public String getPosBrand() {
		return posBrand;
	}

	public void setPosBrand(String posBrand) {
		this.posBrand = posBrand;
	}

	public Integer getOrderDishesType() {
		return orderDishesType;
	}

	public void setOrderDishesType(Integer orderDishesType) {
		this.orderDishesType = orderDishesType;
	}

	public String getGatheringTypes() {
		return gatheringTypes;
	}

	public void setGatheringTypes(String gatheringTypes) {
		this.gatheringTypes = gatheringTypes;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardImg() {
		return bankCardImg;
	}

	public void setBankCardImg(String bankCardImg) {
		this.bankCardImg = bankCardImg;
	}

	public String getIdentityCardBackImg() {
		return identityCardBackImg;
	}

	public void setIdentityCardBackImg(String identityCardBackImg) {
		this.identityCardBackImg = identityCardBackImg;
	}

	public String getIdentityCardImg() {
		return identityCardImg;
	}

	public void setIdentityCardImg(String identityCardImg) {
		this.identityCardImg = identityCardImg;
	}

	public String getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getBankCardBackImg() {
		return bankCardBackImg;
	}

	public void setBankCardBackImg(String bankCardBackImg) {
		this.bankCardBackImg = bankCardBackImg;
	}

	public String getCashierDeskImg() {
		return cashierDeskImg;
	}

	public void setCashierDeskImg(String cashierDeskImg) {
		this.cashierDeskImg = cashierDeskImg;
	}

	public String getCateringServiceLicenseImg() {
		return cateringServiceLicenseImg;
	}

	public void setCateringServiceLicenseImg(String cateringServiceLicenseImg) {
		this.cateringServiceLicenseImg = cateringServiceLicenseImg;
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
		return "ShopEnroll{" +
			", id=" + id +
			", name=" + name +
			", mobileNo=" + mobileNo +
			", shopName=" + shopName +
			", shopAddress=" + shopAddress +
			", shopFacadeImg=" + shopFacadeImg +
			", shopHallImg=" + shopHallImg +
			", hotMenuImg=" + hotMenuImg +
			", shopStaff=" + shopStaff +
			", shopArea=" + shopArea +
			", annualSalesVolume=" + annualSalesVolume +
			", diningTableNumber=" + diningTableNumber +
			", posStatus=" + posStatus +
			", posBrand=" + posBrand +
			", orderDishesType=" + orderDishesType +
			", gatheringTypes=" + gatheringTypes +
			", bankNo=" + bankNo +
			", bankName=" + bankName +
			", bankCardImg=" + bankCardImg +
			", identityCardBackImg=" + identityCardBackImg +
			", identityCardImg=" + identityCardImg +
			", leaveMessage=" + leaveMessage +
			", businessLicenseImg=" + businessLicenseImg +
			", isDeleted=" + isDeleted +
			", bankCardBackImg=" + bankCardBackImg +
			", cashierDeskImg=" + cashierDeskImg +
			", cateringServiceLicenseImg=" + cateringServiceLicenseImg +
			", gmtModified=" + gmtModified +
			", gmtCreate=" + gmtCreate +
			"}";
	}
}
