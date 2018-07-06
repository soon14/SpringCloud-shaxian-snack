package cc.likq.util;

import com.isxxc.constant.WeixinConstant;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 微信支付工具类
 * @author 泥水佬
 */
public class WeiXinPayCommonUtils {

    /***
     * 统一下单预支付生成(二维码)
     * @param orderNo 订单号
     * @param description 订单支付描述
     * @param clientIp 支付终端ip
     * @param amountPayable 支付金额（分）
     * @return 预支付密钥生成
     */
    public static Map<String, String> generateQRcodePayment(String orderNo, String description, String clientIp, long amountPayable, String notifyUrl, String attach) {
        String nonceStr = RandomUtils.generateString(15);
        SortedMap<String, String> params = new TreeMap<>();
        // 应用ID
        params.put("appid", WeixinConstant.APP_ID);
        // 商户ID
        params.put("mch_id", WeixinConstant.MCH_ID);
        // 随机串
        params.put("nonce_str", nonceStr);
        // 商品描述
        params.put("body", description);
        // 订单号
        params.put("out_trade_no", orderNo);
        // 金额
        params.put("total_fee", String.valueOf(amountPayable));
        // 终端IP
        params.put("spbill_create_ip", StringUtils.isBlank(clientIp) ? "127.0.0.1" : clientIp);
        // 回调URL
        params.put("notify_url", notifyUrl);
        // 交易类型
        params.put("trade_type", "NATIVE");
        //签名类型
        params.put("sign_type", "MD5");
        //附加参数
        if (StringUtils.isNotBlank(attach)) {
            params.put("attach", attach);
        }
        // 加密串
        params.put("sign", PayCommonUtil.createSign(params, WeixinConstant.API_KEY));
        // 生成请求XML
        String paramsXML = PayCommonUtil.getRequestXml(params);
        // 请求结果集
        HttpUtilResponse httpUtilResponse = HttpUtils.post(WeixinConstant.UNIFIED_ORDER_URL).setBody(paramsXML).execute();
        Map<String, String> resultMap = new HashMap<>(3);
        if (httpUtilResponse.getCode() == 200) {
            Map<String, String> tempMap = PayCommonUtil.doXMLParse(httpUtilResponse.getContent());
            resultMap.put("returnCode", tempMap.get("return_code"));
            resultMap.put("returnMsg", tempMap.get("return_msg"));
            if ("SUCCESS".equals(tempMap.get("return_code"))) {
                resultMap.put("codeUrl", tempMap.get("code_url"));
            }
        } else {
            System.out.println("***********************微信预支付请求失败: " + orderNo + "**********************");
            resultMap.put("returnCode", "FAIL");
            resultMap.put("returnMsg", "服务异常,请稍候再试");
        }
        return resultMap;
    }

    /***
     * 校验签名
     * @param paramsMap
     * @return
     */
    public static boolean verifySign(SortedMap<String, String> paramsMap) {
        if (paramsMap != null && paramsMap.containsKey("result_code") && "SUCCESS".equals(paramsMap.get("result_code"))
                && paramsMap.containsKey("sign") && paramsMap.get("sign").equalsIgnoreCase(PayCommonUtil.createSign(paramsMap, WeixinConstant.API_KEY))) {
            return true;
        } else {
            return false;
        }
    }
}
