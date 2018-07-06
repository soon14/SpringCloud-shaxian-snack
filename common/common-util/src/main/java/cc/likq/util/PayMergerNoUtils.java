package cc.likq.util;

/***
 * 订单号生成
 */
public class PayMergerNoUtils {
    /***
     * 订单号生成
     */
    public static String generate() {
        String currTime = TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED);
        return currTime + RandomUtils.generateSafeString(8);
    }

}
