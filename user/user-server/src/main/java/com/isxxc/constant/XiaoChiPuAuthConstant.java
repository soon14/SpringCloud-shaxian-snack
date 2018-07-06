package com.isxxc.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 泥水佬
 * @date 2018/1/22
 */
@Component
@ConfigurationProperties(prefix = "XiaoChiPuAuthConstant")
public class XiaoChiPuAuthConstant {

    /***
     * 服务器路径
     */
    private static String serverUrl;

    /***
     * 签名Key
     */
    private static String key;

    public static String getServerUrl() {
        return serverUrl;
    }

    public static void setServerUrl(String serverUrl) {
        XiaoChiPuAuthConstant.serverUrl = serverUrl;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        XiaoChiPuAuthConstant.key = key;
    }
}
