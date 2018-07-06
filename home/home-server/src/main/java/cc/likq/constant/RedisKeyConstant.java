package cc.likq.constant;

/**
 * RedisKey集
 *
 * @author 泥水佬
 */
public class RedisKeyConstant {

    /***
     * 资讯阅读数
     */
    private final static String PAGEVIEW = "pageview:";
    
    public static String getPageviewKey(String sourceName) {
        return PAGEVIEW + sourceName;
    }
}
