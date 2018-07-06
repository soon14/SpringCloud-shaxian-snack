package cc.likq.util;

import com.alibaba.fastjson.JSON;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressUtils {
    private final static String url = "http://poll.kuaidi100.com/poll/query.do";
    private static String customer = "169A98CC22844B740A2DA89D62D25DBA";
    private static String key = "NMBlYdfP2325";

    public static ExpressResultInfo findByNo(String expressCode, String expressNo) {
        Map<String, String> params = new HashMap<>();
        params.put("com", expressCode);
        params.put("num", expressNo);

        String paramsJson = JSON.toJSONString(params);
        String sign = DigestUtils.md5Hex(paramsJson + key + customer).toUpperCase();

        ExpressResultInfo resultInfo = new ExpressResultInfo();
        HttpUtilResponse httpUtilResponse = HttpUtils.post(url).addParam("customer", customer).addParam("sign", sign).addParam("param", paramsJson).execute();
        if (httpUtilResponse.getCode() == 200) {
            resultInfo = JSON.parseObject(httpUtilResponse.getContent(), ExpressResultInfo.class);
        } else {
            resultInfo.setReturnCode(500);
            resultInfo.setMessage("服务异常,请稍候再试");
        }
        return resultInfo;
    }

    public static class ExpressResultInfo {

        /*快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态，其中4-7需要另外开通才有效，详见章1.3 */
        private String state;
        /*是否签收标记，请忽略，明细状态请参考state字段，详见章1.3 */
        private String ischeck;
        /*快递公司编码,一律用小写字母，见章三《快递公司编码》*/
        private String com;
        /*单号*/
        private String nu;
        /*查询结果：false表示查询失败*/
        private boolean result;
        /*
         * 失败的代号
         * 400: 提交的数据不完整，或者贵公司没授权
         * 500: 表示查询失败，或没有POST提交
         *  501: 服务器错误，快递100服务器压力过大或需要升级，暂停服务
         *  502: 服务器繁忙，详细说明见章二《查询接口并发协议》
         *  503: 验证签名失败。
         * */
        private Integer returnCode;
        /*失败内容表述*/
        private String message;
        /*快递路线结果*/
        private List<ExpressDataBean> data = new ArrayList<>();

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public List<ExpressDataBean> getData() {
            return data;
        }

        public void setData(List<ExpressDataBean> data) {
            this.data = data;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public Integer getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(Integer returnCode) {
            this.returnCode = returnCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ResultInfo{" +
                    "state='" + state + '\'' +
                    ", ischeck='" + ischeck + '\'' +
                    ", com='" + com + '\'' +
                    ", nu='" + nu + '\'' +
                    ", result=" + result +
                    ", returnCode=" + returnCode +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static class ExpressDataBean {
        /*内容*/
        private String context;
        /*时间，原始格式*/
        private String time;
        /*格式化后时间*/
        private String ftime;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "context='" + context + '\'' +
                    ", time='" + time + '\'' +
                    ", ftime='" + ftime + '\'' +
                    '}';
        }
    }
}
