package com.isxxc.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * 微信信息配置
 * @author 泥水佬
 */
@Component
@ConfigurationProperties(prefix = "weixinConfig")
public class WeixinConstant {
    /***
     * 应用AppId
     */
    public static String APP_ID;
    /***
     * 应用AppSecret
     */
    public static String API_KEY;
    /***
     * 支付回调
     */
    public static String NOTIFY_URL;
    /***
     * 商户号ID
     */
    public static String MCH_ID;
    /***
     * 微信证书地址
     */
    public static String cert_address;

    /***
     * 微信退款回调地址
     */
    public static String wxrefundnotify_url;
    /***
     * 微信支付统一接口(POST)
     */
    public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /***
     * 微信支付查询订单
     */
    public final static String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    /***
     * 微信订单退款
     */
    public final static String ORDER_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    public static String getAppId() {
        return APP_ID;
    }

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getNotifyUrl() {
        return NOTIFY_URL;
    }

    public static void setNotifyUrl(String notifyUrl) {
        NOTIFY_URL = notifyUrl;
    }

    public static String getMchId() {
        return MCH_ID;
    }

    public static void setMchId(String mchId) {
        MCH_ID = mchId;
    }

    public static String getCert_address() {
        return cert_address;
    }

    public static void setCert_address(String cert_address) {
        WeixinConstant.cert_address = cert_address;
    }

    public static String getWxrefundnotify_url() {
        return wxrefundnotify_url;
    }

    public static void setWxrefundnotify_url(String wxrefundnotify_url) {
        WeixinConstant.wxrefundnotify_url = wxrefundnotify_url;
    }

    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }

    public static String getOrderQuery() {
        return ORDER_QUERY;
    }
    public static String getOrderRefund() {
        return ORDER_REFUND;
    }
}
