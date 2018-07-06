package com.isxxc.constant;

/**
 * 商店金额枚举
 *
 * @author 泥水佬
 * @date 2018/2/5
 */
public class StoreAmountEnum {

    /***
     * 类型
     */
    public static enum Type {
        /***
         * 进账
         */
        IN_AMOUNT(0),
        /***
         * 退款
         */
        REFUND_AMOUNT(1),
        /***
         * 提现
         */
        WITHDRAW(2);
        public Integer code;

        Type(Integer code) {
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
