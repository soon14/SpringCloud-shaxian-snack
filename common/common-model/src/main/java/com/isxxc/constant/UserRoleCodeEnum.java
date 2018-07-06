package com.isxxc.constant;

/**
 * @author 泥水佬
 * @date 2017/12/20
 */
public enum UserRoleCodeEnum {
    /***
     * 商店
     */
    SHOP("shop"),
    /***
     * 商城
     */
    STORE("store"),
    /***
     * 服务支撑
     */
    SUPPORTING("supporting"),
    /***
     * 金融服务
     */
    BANKING("banking");
    public String code;

    UserRoleCodeEnum(String code) {
        this.code = code;
    }
}
