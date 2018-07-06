package cc.likq.config;

import cc.likq.base.domain.dto.SystemAdminTokenDTO;

/**
 * 当前登录管理员信息
 *
 * @author likq
 */
public class CurrentAdminManager {

    private static ThreadLocal<SystemAdminTokenDTO> threadLocal = new ThreadLocal<>();

    /***
     * 存储当前管理员信息
     * @param systemAdminTokenDTO
     */
    public static void setAdminInfo(SystemAdminTokenDTO systemAdminTokenDTO) {
        threadLocal.set(systemAdminTokenDTO);
    }

    /***
     * 获取当前管理员信息
     * @return
     */
    public static SystemAdminTokenDTO getAdminInfo() {
        return threadLocal.get();
    }

    /***
     * 关闭当前信息
     */
    public static void close() {
        threadLocal.remove();
    }
}
