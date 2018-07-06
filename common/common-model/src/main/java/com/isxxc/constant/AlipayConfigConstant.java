package com.isxxc.constant;


/**
 * @author NanZheng
 * @projectName shaxian
 * @since 2018/5/17
 * 支付宝支付的常量设置
 */
public class AlipayConfigConstant {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091400508331";//正式

    // 商户UID
    public static String seller_id = "2088102175573131";//正式

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCT8lG/QzRJa9xPeNESnDAswTMzxkK9t3BKGOu9Lpkw0kVARbnoQR+dKvmUInuK9B6r9d0OXM3888c1pN89iENAlvbqVF3yLhcMeLH0jh7CTVpWkXH0mOl+UvhTU7KtU/oBDZYXNTwJw6vOJBaCPVlC9lonY0oV4+uJoW1tORnTViFncZPqLAduF38rkpVHxLmIAmEYk3lZ3hzClj/cIDrM11A61WkYbGvMwzIt9zJGNKS1BBzuJfkaAknfjDUwr4OIui05yjcqFdmUnaGHA0s/Y+qP8NO0KuLKAn2BE6N169aU5pgtPLcaFIsjS9EfVl7z4DNH1jRTpjyEe4lDvJD3AgMBAAECggEBAJBMa8Eh2jC2fCYjT8BYCIDOW5NnD5edAG+1CND3T0EYBaVfnKjUQ/J2hQEf9OGdowDUCCzHBb5vQuCmk3qfmgC4fdImQY9BtwdZ/S/rIkMfUTpYGA6ayjqE0GHto7t8yb4XM0oliQ55A+xGtJGJJrOyNlJufEcHi6cshN9gLzk5L9uyo1HQXsSKVdjOMO6msIPmH5mgKLg+uN0+cT2tbg0jTWvUo/Vw2COrk37x8nZ4fVDMi0lt5hptDasfCUSYN8IM7PHoqRnp4qzjk2g43FNs+pXvdaSMWsJBrp2YsteWFAMjOWoJu+mfLLLUI26K0E1cA9OMdJlOEaF21lQJXCECgYEAytcIZ9ciMXxJHo9gl3ui2y/YD39yRLASH8ODxtCYYKKTxEqpFE3c7LPGEpbKp8gQ2WDz8WuzzyawGSoJJJ2Zo7GcEQYDe6IasPinhFYt6pmp95LzfdVAo0XAgJsdSHElEnqvLtwm8rLbI4Npi/YZUZtIQIWqkVq17qhjEXUTsrECgYEAurhew2QvDzELNwWxf3Iw16RK79/hy0eR30F8IyeQBfwjM1H8VCyWGSpta93Y8rtS0z2xLixGXBM2OChTrkI4PUhWsSil7IueHQks2TEOhJMXE3oIIWTaNQNiimNIpLNR+LHIWBYfp9Wj4w6QrSijArdce2zHqR3xEsbU2nYn2CcCgYAHI+is1xriCRD93Wg2DOkftCBz8zWPi28n5RcHcaJbjH2NJF+tUkxbnLx1PQ+nNli5fjs4HWV3/lGmoR8vRcj5xS/MPZzzxjIQVsB23R3Cq2QbTfX6UP50gOkU3eD7AtiePOlbye4pDvi92Etn3OLpR4EUnF/zu/C5lP3fyHJIEQKBgCwcwK6SrZd+M7MVEkEUvjtD4jxXfSWkS+8drnEzuI/haIOIW/+a5P8fCejQ6Z826DNlwMUyypCYLVgJFvP1SwoXW/vQqZp/xaQAder97vsdMPd1/hO9mtupaA9fs1rNzpDdl4JP/7yL+YL7/uwZzKEopp22Llli7IBOHx1ZisJDAoGAecmibkKye2f0UR41LLt6OjrD/Z7Xp2dIpE21MuRdaKHafg7n3r7NvVNldXrBRjCPIt/8XX7Zbjl+DVDRkaHAZ7CKI7mzL1ddUtwULRGRUTwVgKDPdtrecY6j+tlO7eulvJLVzE0Cs/y2gvucpVQQ5ZK24fHL0FXIJHnMWFWHEgs=";//正式

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3Bwwrl3yNXljh4+yPZ9OX4wmzMBluYP4fgAodwXYmmMqZ09GGaSfy3Kp8fAUv1PsbD8Ji0aPQ4HcNyTsWao0btm1CefWh72FmgoLurhR3F22mo49DhJnyiPdpxHvfnrMqVutQBsfX00h+CDUCbEeY0xUAffH7IfqHZDYIdoKr2vz2g/Hy7JxHzgOXeHJjss5IVcCSvwg4e0A/ekDoiKp+1t+uTsggoBbzNy4SzRxgE7+5MIzQ+xQ91p5ahgddXivGeo41u/KkHyILiT0hv7YT+T9jOM3QxEHfIC8kmwgwknOT11sd4hd3DPNOevIcebdxLBG7DCgg5Ff8AB0KdTZewIDAQAB";//正式

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://c199a50b.ngrok.io/api/payment/notify_url.pay";//正式

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "https://www.baidu.com/";//正式

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//正式

    // 支付宝网关
    public static String log_path = "C://";
}
