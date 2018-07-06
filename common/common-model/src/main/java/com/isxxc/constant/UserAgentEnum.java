package com.isxxc.constant;

/***
 * 终端类型
 * @author likq
 */
public enum UserAgentEnum {
    /***
     * 所有类型
     */
    ALL("*"),
    /***
     * WEB类型
     */
    WEB("WEB"),
    /***
     * 微信类型
     */
    WEIXIN("WEIXIN");
    public String code;

    UserAgentEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static boolean isInclude(String code) {
        if (WEB.code.equals(code) || WEIXIN.code.equals(code)) {
            return true;
        } else {
            return false;
        }
    }
    
}
