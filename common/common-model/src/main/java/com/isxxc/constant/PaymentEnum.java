package com.isxxc.constant;

/**
 * 支付类枚举
 *
 * @author 泥水佬
 * @date 2018/2/5
 */
public class PaymentEnum {

    /***
     * 支付类型
     */
    public enum PayType {
        /***
         * 微信支付
         */
        WEI_XIN(0),
        /***
         * 支付宝
         */
        ALI_PAY(1);
        public Integer code;

        PayType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
}
