package cc.likq.util;

/***
 * 订单号生成
 */
public class OrderNoUtils {
    public enum OrderType {
        /***
         * 购物订单
         */
        SHOP_ORDER("01"),
        /***
         * 退货订单
         */
        RETURN_ORDER("02");
        public String type;

        OrderType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /***
     * 订单号生成
     * @param orderType
     * @return
     */
    public static String generate(OrderType orderType) {
        String currTime = TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED);
        return orderType.type + currTime + RandomUtils.generateNumberString(8);
    }

}
