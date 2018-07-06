package com.isxxc.constant;

import java.util.List;

/**
 * 功能URL
 *
 * @author 泥水佬
 * @date 2017/12/25
 */
public class FunctionUrlConstant {
    public static List<String> ignoreUrl;

    public static List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public static void setIgnoreUrl(List<String> ignoreUrl) {
        FunctionUrlConstant.ignoreUrl = ignoreUrl;
    }
}
