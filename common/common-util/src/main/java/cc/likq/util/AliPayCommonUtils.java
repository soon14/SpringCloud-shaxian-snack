package cc.likq.util;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.isxxc.constant.AlipayConfigConstant;
import com.isxxc.domain.dto.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NanZheng
 * @projectName shaxian
 * @since 2018/5/21
 * 支付宝支付跳转页面工具类
 */
public class AliPayCommonUtils {

    private final static Logger logger = LoggerFactory.getLogger(AliPayCommonUtils.class);

    /**
     * 支付宝支付跳转页面公共方法
     */
    public static String skipAlipayPayment(String mergeNo, Long amountPrice) throws Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigConstant.gatewayUrl, AlipayConfigConstant.app_id, AlipayConfigConstant.merchant_private_key, "json", AlipayConfigConstant.charset, AlipayConfigConstant.alipay_public_key, AlipayConfigConstant.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfigConstant.return_url);
        alipayRequest.setNotifyUrl(AlipayConfigConstant.notify_url);

        //根据订单code查询订单信息
        //TODO 通过订单号查询出订单的详情


        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = mergeNo;
        //付款金额，必填
        String total_amount = new BigDecimal(amountPrice).divide(new BigDecimal(100), 2,BigDecimal.ROUND_HALF_UP).toString();
        logger.info("***********订单金额：{}************", total_amount);
        //订单名称，必填
        String subject = mergeNo;
        //商品描述，可空
        String body = "";


        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    /**
     * 支付宝异步通知支付结果
     * @param pager
     * @return
     * @throws Exception
     */
    public static Result notifyAlipayPayment(Pager pager) throws Exception{
        logger.info("**********获取支付宝POST过来反馈信息********");
        Map<String, Object> map = pager.getParamMap();
        Map<String, String> verifiedMap = new HashMap<>();
        for (String s : map.keySet()) {
            verifiedMap.put(s, map.get(s).toString());
        }
        //验证
        boolean signVerified = AlipaySignature.rsaCheckV1(verifiedMap, AlipayConfigConstant.alipay_public_key, AlipayConfigConstant.charset, AlipayConfigConstant.sign_type); //调用SDK验证签名

        /* 实际验证过程建议商户务必添加以下校验：
    	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
    	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
    	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
    	4、验证app_id是 否为该商户本身。
    	*/
        if (signVerified) {
            //商户订单号
            String out_trade_no = verifiedMap.get("out_trade_no");
            logger.info("out_trade_no=" + out_trade_no);
            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            String trade_no = new String(verifiedMap.get("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            logger.info("trade_no=" + trade_no);
            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            String trade_status = new String(verifiedMap.get("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            logger.info("trade_status=" + trade_status);
            //付款金额
//            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            String total_amount = new String(verifiedMap.get("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            logger.info("total_amount=" + total_amount);
            // 商户UID
//            String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"),"UTF-8");
            String seller_id = new String(verifiedMap.get("seller_id").getBytes("ISO-8859-1"), "UTF-8");
            logger.info("seller_id=" + seller_id);
            // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
//            String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"),"UTF-8");
            String app_id = new String(verifiedMap.get("app_id").getBytes("ISO-8859-1"), "UTF-8");
            logger.info("app_id=" + app_id);
            /**
             * 验证上方注释所说的四个条件
             */
            if ("TRADE_FINISHED".equals(trade_status) || "TRADE_SUCCESS".equals(trade_status)) {
                BigDecimal paymoney = null;
                //获取支付的总金额
                if (total_amount != null) {
                    paymoney = new BigDecimal(total_amount);
                }

                //验证商户UID
                if (!AlipayConfigConstant.seller_id.equals(seller_id)) {
                    logger.info("进入到商户UID不匹配中");
                    logger.info("商户UID不对应！seller_id:" + seller_id);
                    logger.error("支付宝支付验证失败");
                    return ResponseResult.other(201, "验证商户sell_id不匹配");
                }


                //验证应用ID，收款账号既是您的APPID对应支付宝账号
                if (!AlipayConfigConstant.app_id.equals(app_id)) {
                    logger.info("进入到商户APPID不匹配中");
                    logger.info("APPID不对应！app_id:" + app_id);
                    logger.error("支付宝支付验证失败");
                    return ResponseResult.other(202, "验证商户app_id不匹配");
                }

                logger.info("验证成功");
                return ResponseResult.success();

            } else {
                return ResponseResult.other(204, "交易状态不正确");
            }
        } else {
            return ResponseResult.other(203, "签名验证不通过");
        }
    }
}
