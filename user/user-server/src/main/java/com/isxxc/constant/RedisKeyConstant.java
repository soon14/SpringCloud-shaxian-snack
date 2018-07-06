package com.isxxc.constant;

/**
 * RedisKey集
 *
 * @author 泥水佬
 */
public class RedisKeyConstant extends CommonRedisKeyConstant {

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
     * 会员基本信息
     */

    public static String getRegisterVerifyCodeKey(String mobile) {
        return REGISTER_VERIFYCODE + mobile;
    }

    public static String getRegisterVerifyCodeNumKey(String mobile) {
        return REGISTER_VERIFYCODE_NUM + mobile;
    }

    public static String getLoginTokenKey(Integer userId, UserAgentEnum userAgent) {
        return LOGIN_TOKEN + userId + "_" + userAgent.code;
    }

    public static String getLoginTokenKey(Integer userId, String userAgent) {
        return LOGIN_TOKEN + userId + "_" + userAgent;
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

}
