package cc.likq.constant;

import com.isxxc.constant.CommonRedisKeyConstant;

/**
 * Redis服务Key
 *
 * @author 泥水佬
 */
public class RedisKeyConstant extends CommonRedisKeyConstant{
    /***
     * 管理员Key
     */
    private static String ADMIN_TOKEY = "adminToke:";

    public static String getAdminTokenKey(Integer adminId) {
        return ADMIN_TOKEY + adminId;
    }
}
