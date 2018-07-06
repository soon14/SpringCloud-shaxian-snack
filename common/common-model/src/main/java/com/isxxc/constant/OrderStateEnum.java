package com.isxxc.constant;

/***
 * 订单状态
 */
public class OrderStateEnum {

    /***
     * 状态总线
     */
    public enum MasterState {
        /***
         * 支付状态
         */
        PAYMENT_STATE(0),
        /***
         * 配送状态
         */
        DELIVER_STATE(1),
        /***
         * 交易完成
         */
        COMPLETE_TRANSACTION(2),
        /***
         * 已取消
         */
        CANCEL(3),
        /***
         * 交易关闭
         */
        TRADING_CLOSE(4);

        public int code;

        MasterState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 支付状态
     */
    public enum PaymentState {
        /***
         * 待支付
         */
        UNPAY(0),
        /***
         * 已支付
         */
        HAVE_PAY(1),
        /***
         * 退款中
         */
        RETURN_PAY(2),
        /***
         * 已退款
         */
        ALREADY_REFUND(3),
        /***
         * 已作废
         */
        OBSOLETE(4);

        public int code;

        PaymentState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     *物流状态
     */
    public enum ExpressState {
        /***
         * 待揽收
         */
        WAITING_TAKING(0),
        /***
         * 已揽件
         */
        SHIPPED(1),
        /***
         * 在途
         */
        TRANSIT(2),
        /***
         * 派件
         */
        DELIVERY(3),
        /***
         * 已签收
         */
        ACKNOWLEDGE_RECEIPT(4),
        /***
         * 退签
         */
        REJECTION(5),
        /***
         * 退回
         */
        SALES_RETURN(6),
        /***
         * 疑难件
         */
        PROBLEMATIC(7);

        public int code;

        ExpressState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 评论状态
     */
    public enum CommentState {
        /***
         * 待评价
         */
        WAIT_COMMENT(0),
        /***
         * 已评价
         */
        ALREADY_COMMENT(1);
        public int code;

        CommentState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 配送状态
     */
    public enum DeliverState {
        /***
         * 待发货
         */
        WAIT_DELIVERY(0),
        /***
         * 已发货
         */
        ALREADY_DELIVERY(1),
        /***
         * 已收货
         */
        COMPLETE_DELIVER(2);
        public int code;

        DeliverState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 售后状态
     */
    public enum AfterServiceState {
        /***
         * 申请售后
         */
        AFTER_SERVICE(0),
        /***
         * 审核中
         */
        AUDIT(1),
        /***
         * 审核通过
         */
        APPROVE(2),
        /***
         * 审核不通过
         */
        CONTRADICT(3);
        private int code;

        AfterServiceState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 取消状态
     */
    public enum CancelState {
        /***
         * 审核中
         */
        Under_Review(0),
        /***
         * 已通过
         */
        ALREADY_PASS(1),
        /***
         * 不通过
         */
        NO_PASS(2);
        private int code;

        CancelState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 合并支付
     */
    public enum MergerPayState {
        /***
         * 待支付
         */
        UNPAY(0),
        /***
         * 已支付
         */
        HAVE_PAY(1);
        public int code;

        MergerPayState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
