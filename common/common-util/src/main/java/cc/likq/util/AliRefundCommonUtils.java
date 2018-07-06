package cc.likq.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.isxxc.constant.AlipayConfigConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author NanZheng
 * @projectName shaxian
 * @since 2018/5/21
 */
public class AliRefundCommonUtils {

    private final static Logger logger = LoggerFactory.getLogger(AliRefundCommonUtils.class);


    public static boolean skipAlipayReturn(String mergeNo, String orderReturnCode, Long amountPrice) throws Exception {

        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigConstant.gatewayUrl, AlipayConfigConstant.app_id,
                AlipayConfigConstant.merchant_private_key, "json", AlipayConfigConstant.charset, AlipayConfigConstant.alipay_public_key,
                AlipayConfigConstant.sign_type);

        // 设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        /**
         * 商户订单号和支付宝交易号二者选其一
         */
        // 商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = mergeNo;
        logger.info("out_trade_no=" + out_trade_no);

        // 支付宝交易号
        String trade_no = "";
        logger.info("trade_no=" + trade_no);

        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = String.valueOf(amountPrice);
        logger.info("refund_amount=" + refund_amount);

        //退款的原因说明
        String refund_reason = "";
        logger.info("refund_reason=" + refund_reason);

        // 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = orderReturnCode;
        logger.info("orderReturnCode=" + orderReturnCode);

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\"" + refund_reason + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}");

        // 请求
        AlipayTradeRefundResponse resp = alipayClient.execute(alipayRequest);

        if(resp == null){
            return false;
        }
        return resp.isSuccess();
    }

}
