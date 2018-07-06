package cc.likq.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cc.likq.base.domain.dto.SystemAdminDTO;
import cc.likq.base.domain.entity.SystemAdminDO;
import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
public interface SystemAdminService extends IService<SystemAdminDO> {

    /***
     *保存管理员信息
     */
    Result save(SystemAdminDO systemAdminDO);

    /***
     * 禁用管理员
     */
    Result disable(Integer id);

    /***
     * 取消禁用管理员
     */
    Result cancelDisable(Integer id);

    /***
     * 更新用户信息
     */
    Result updateInfo(SystemAdminDO systemAdminDO);

    /***
     *修改密码
     */
    Result updatePassword(Integer id, String password, String newPassword);

    /***
     * 登录
     */
    Result login(String account, String password, String ip);

    /***
     * 注销登录
     */
    Result logout(Integer id);

    /***
     * 图片列表
     */
    Result listPage(Page page, SystemAdminDTO systemAdminDTO);
}
