package com.isxxc.constant;

/**
 * RedisKey集
 *
 * @author 泥水佬
 */
public class CommonRedisKeyConstant {
    /***
     * 会员信息Token
     */
    private final static String USER_INFO = "userInfo:";
    /***
     * 注册验证码
     */
    private final static String REGISTER_VERIFYCODE = "registerVerifyCode:";
    /***
     * 获取注册验证码次数
     */
    private final static String REGISTER_VERIFYCODE_NUM = "registerVerifyCodeNum:";
    /***
     * 登录验证码
     */
    private final static String LOGIN_VERIFYCODE = "loginVerifyCode:";
    /***
     * 获取登录验证码次数
     */
    private final static String LOGIN_VERIFYCODE_NUM = "loginVerifyCodeNum:";
    /***
     * 授权绑定验证码
     */
    private final static String USER_BIND_VERIFYCODE = "userBindVerifycode:";
    /***
     * 获取授权绑定验证码
     */
    private final static String USER_BIND_VERIFYCODE_NUM = "userBindVerifycodeNum:";
    /***
     * 会员登录Token
     */
    private final static String LOGIN_TOKEN = "loginToken:";
    /***
     * 根据手机号找回密码
     */
    private final static String RESET_PASSWORD_VERIFYCODE = "resetPasswordVerifyCode:";
    /***
     * 根据手机号找回密码短信获取次数
     */
    private final static String RESET_PASSWORD_VERIFYCODE_NUM = "resetPasswordVerifyCodeNum:";
    /***
     * 资讯阅读数
     */
    private final static String PAGEVIEW = "pageview:";

    /***
     * 第三方会员信息Token
     */
    private final static String OPEN_USER_INFO = "openUserInfo:";

    public static String getRegisterVerifyCodeKey(String mobile) {
        return REGISTER_VERIFYCODE + mobile;
    }

    public static String getRegisterVerifyCodeNumKey(String mobile) {
        return REGISTER_VERIFYCODE_NUM + mobile;
    }

    public static String getLoginVerifyCodeKey(String mobile) {
        return LOGIN_VERIFYCODE + mobile;
    }

    public static String getLoginVerifyCodeNumKey(String mobile) {
        return LOGIN_VERIFYCODE_NUM + mobile;
    }

    public static String getResetPasswordVerifyCodeKey(String mobile) {
        return RESET_PASSWORD_VERIFYCODE + mobile;
    }

    public static String getResetPasswordVerifyCodeNumKey(String mobile) {
        return RESET_PASSWORD_VERIFYCODE_NUM + mobile;
    }

    public static String getPageviewKey(String sourceName) {
        return PAGEVIEW + sourceName;
    }


    public static String getUserBindVerifycodeKey(String mobile) {
        return USER_BIND_VERIFYCODE + mobile;
    }

    public static String getUserBindVerifycodeNumKey(String mobile) {
        return USER_BIND_VERIFYCODE_NUM + mobile;
    }

    public static String getUserInfoKey(String userCode) {
        return USER_INFO + userCode;
    }

    public static String getOpenUserInfoKey(Integer openUserId) {
        return OPEN_USER_INFO + openUserId;
    }
}
