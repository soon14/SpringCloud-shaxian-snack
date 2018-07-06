package cc.likq.util;

import com.alibaba.fastjson.JSONObject;
import com.isxxc.constant.WeixinConstant;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 微信支付工具类
 * @author 泥水佬
 */
public class WeiXinRefundCommonUtils {

    /***
     * 微信退款提交
     * @param,appid 公众账号ID
     * @param，mch_id 商户号
     * @param，nonce_str 随机字符串
     * @param，sign 签名
     * @param，sign_type 签名类型
     * @param，transaction_id 微信订单号  out_trade_no 商户订单号 两个参数选择一个
     * @param，out_refund_no 商户退款单号
     * @param，total_fee 订单金额
     * @param，refund_fee 退款金额
     * @param，refund_fee_type 退款货币种类
     * @param，refund_desc 退款原因 若商户传入，会在下发给用户的退款消息中体现退款原因 商品已售完
     * @param，refund_account 退款资金来源
     * @param，notify_url 退款结果通知url
     * <xml>
     *<appid>wx2421b1c4370ec43b</appid>
     *<mch_id>10000100</mch_id>
     *<nonce_str>6cefdb308e1e2e8aabd48cf79e546a02</nonce_str>
     *<out_refund_no>1415701182</out_refund_no>
     *<out_trade_no>1415757673</out_trade_no>
     *<refund_fee>1</refund_fee>
     *<total_fee>1</total_fee>
     *<transaction_id></transaction_id>
     *<sign>FE56DD4AA85C0EECA82C35595A69E153</sign>
     *</xml>
     * @return
     * return_code 返回状态码
     * return_msg 返回信息 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
     * 以下字段在return_code为SUCCESS的时候有返回
     *	result_code 业务结果 SUCCESS/FAIL SUCCESS退款申请接收成功，结果通过退款查询接口查询 FAIL 提交业务失败
     *  err_code 错误代码
     *  err_code_des 错误代码描述
     *  appid  公众账号ID
     *  mch_id    商户号
     *  nonce_str 随机字符串
     *  sign      签名
     *  transaction_id  微信订单号
     *  out_trade_no   商户订单号
     *  out_refund_no  商户退款单号
     *  refund_id   微信退款单号
     *  refund_fee  退款金额
     *  settlement_refund_fee  应结退款金额
     *  total_fee            标价金额
     *  settlement_total_fee  应结订单金额
     *  fee_type            标价币种
     *  cash_fee            现金支付金额
     *  cash_fee_type       现金支付币种
     *  cash_refund_fee     现金退款金额
     *  coupon_type_$n      代金券类型
     *  coupon_refund_fee    代金券退款总金额
     *  coupon_refund_fee_$n 单个代金券退款金额
     *  coupon_refund_count  退款代金券使用数量
     *  coupon_refund_id_$n  退款代金券ID
     */
    public static JSONObject wxRefundPayment(String payOrderNo, String refundNo, long amountPayable, long refund_fee, String notifyUrl) {
        String mingreqxml = "payOrderNo:"+payOrderNo+",refundNo:"+refundNo+",amountPayable:"+amountPayable+",refund_fee:"+refund_fee+",notifyUrl:"+notifyUrl;
        try{
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(WeixinConstant.cert_address));
            try {
                keyStore.load(instream, WeixinConstant.MCH_ID.toCharArray());
            }finally {
                instream.close();
            }
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WeixinConstant.MCH_ID.toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf).build();

            //设置微信访问入口
            HttpPost httppost = new HttpPost(WeixinConstant.ORDER_REFUND);

            String nonceStr = RandomUtils.generateString(15);
            SortedMap<String, String> params = new TreeMap<>();
            // 应用ID
            params.put("appid", WeixinConstant.APP_ID);
            // 商户ID
            params.put("mch_id", WeixinConstant.MCH_ID);
            // 随机串
            params.put("nonce_str", nonceStr);
            // 支付时候的订单号
            params.put("transaction_id", payOrderNo);

            //标价币种
            params.put("fee_type", "CNY");
            // 商户退款单号
            params.put("out_refund_no", refundNo);
            // 订单支付时候金额
            params.put("total_fee", String.valueOf(amountPayable));
            // 退款金额
            params.put("refund_fee", String.valueOf(refund_fee));

            // 回调URL
            params.put("notify_url", notifyUrl);

            // 加密串
            params.put("sign", PayCommonUtil.createSign(params, WeixinConstant.API_KEY));
            // 生成请求XML
            String xml = PayCommonUtil.getRequestXml(params);


            try {
                StringEntity se = new StringEntity(xml);
                httppost.setEntity(se);
                System.out.println("executing request" + httppost.getRequestLine());
                CloseableHttpResponse responseEntry = httpclient.execute(httppost);
                System.out.println("responseEntry" + responseEntry.toString());
                try {
                    HttpEntity entity = responseEntry.getEntity();
                    System.out.println(responseEntry.getStatusLine());
                    if (entity != null) {
                        System.out.println("Response content length: "
                                + entity.getContentLength());
                        SAXReader saxReader = new SAXReader();
                        Document document = saxReader.read(entity.getContent());
                        Element rootElt = document.getRootElement();
                        System.out.println("根节点：" + rootElt.getName());
                        System.out.println("==="+rootElt.elementText("result_code"));
                        System.out.println("==="+rootElt.elementText("return_msg"));
                        String resultCode = rootElt.elementText("result_code");
                        JSONObject result = new JSONObject();

                        Document documentXml = DocumentHelper.parseText(xml);
                        Element rootEltXml = documentXml.getRootElement();
                        if(resultCode.equals("SUCCESS")){
                            System.out.println("*********退款请求成功**********");
//                            System.out.println("=================prepay_id===================="+ rootElt.elementText("prepay_id"));
//                            System.out.println("=================sign===================="+ rootEltXml.elementText("sign"));
//                            result.put("weixinPayUrl", rootElt.elementText("code_url"));
//                            result.put("prepayId", rootElt.elementText("prepay_id"));
                            result.put("status","success");
                            result.put("msg","success");
                            result.put("mireqxml",xml);
                            result.put("mingreqxml",mingreqxml);
                            result.put("mirspentity",entity);
                            result.put("mingrspentity",rootElt);

                        }else{
                            System.out.println("***********************微信退款请求失败 ==>支付时单据号：" + payOrderNo + " ==>退款时退款单号："+refundNo+"**********************");
                            result.put("status","false");
                            result.put("msg",rootElt.elementText("err_code_des"));
                            result.put("mireqxml",xml);
                            result.put("mingreqxml",mingreqxml);
                            result.put("mirspentity",entity);
                            result.put("mingrspentity",rootElt);
                        }
                        return result;
                    }
                    EntityUtils.consume(entity);
                }
                finally {
                    responseEntry.close();
                }
            }
            finally {
                httpclient.close();
            }
            System.out.print("==================返回了result1==================");
            JSONObject result1 = new JSONObject();
            result1.put("status","error");
            result1.put("msg","返回了result1");
            result1.put("mireqxml",xml);
            result1.put("mingreqxml",mingreqxml);
            result1.put("mirspentity","responseEntry 为null");
            result1.put("mingrspentity","responseEntry 为null");
            return result1;
        }catch(Exception e){
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("status","error");
            result.put("msg",e.getMessage());
            result.put("mireqxml","证书读取部分异常");
            result.put("mingreqxml",mingreqxml);
            result.put("mirspentity","证书读取部分异常");
            result.put("mingrspentity","证书读取部分异常");
            return result;
        }
    }


//        String nonceStr = RandomUtils.generateString(15);
//        SortedMap<String, String> params = new TreeMap<>();
//        // 应用ID
//        params.put("appid", WeixinConstant.APP_ID);
//        // 商户ID
//        params.put("mch_id", WeixinConstant.MCH_ID);
//        // 随机串
//        params.put("nonce_str", nonceStr);
//        // 支付时候的订单号
//        params.put("out_trade_no", payOrderNo);
//        // 商户退款单号
//        params.put("out_refund_no", refundNo);
//        // 订单支付时候金额
//        params.put("total_fee", String.valueOf(amountPayable));
//        // 退款金额
//        params.put("refund_fee", String.valueOf(refund_fee));
//
//        // 回调URL
//        params.put("notify_url", notifyUrl);
//
//        // 加密串
//        params.put("sign", PayCommonUtil.createSign(params, WeixinConstant.API_KEY));
//        // 生成请求XML
//        String paramsXML = PayCommonUtil.getRequestXml(params);
//        // 请求结果集
//        HttpUtilResponse httpUtilResponse = HttpUtils.post(WeixinConstant.ORDER_REFUND).setBody(paramsXML).execute();
//        Map<String, String> resultMap = new HashMap<>(3);
//
//        if (httpUtilResponse.getCode() == 200) {
//            Map<String, String> tempMap = PayCommonUtil.doXMLParse(httpUtilResponse.getContent());
//            resultMap.put("returnCode", tempMap.get("return_code"));
//            resultMap.put("returnMsg", tempMap.get("return_msg"));
//            if ("SUCCESS".equals(tempMap.get("return_code"))) {
////                resultMap.put("codeUrl", tempMap.get("code_url"));
//                System.out.println("*********退款请求成功**********");
//                System.out.println("退款请求返回参数：tempMap===》"+tempMap.toString());
//
//            }
//        } else {
//            System.out.println("***********************微信退款请求失败 ==>支付时单据号：" + payOrderNo + " ==>退款时退款单号："+refundNo+"**********************");
//            resultMap.put("returnCode", "FAIL");
//            resultMap.put("returnMsg", "服务异常,请稍候再试");
//        }
//        return resultMap;
//    }

}
