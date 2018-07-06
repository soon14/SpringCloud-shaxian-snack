package cc.likq.base.service;

import com.baomidou.mybatisplus.service.IService;

import cc.likq.base.domain.entity.SystemFunctionDO;
import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
public interface SystemFunctionService extends IService<SystemFunctionDO> {

    /***
     *添加功能
     */
    Result save(SystemFunctionDO systemFunctionDO);

    /***
     *删除菜单
     */
    Result remove(Integer id);

    /***
     * 更新菜单信息
     */
    Result updateInfo(SystemFunctionDO systemFunctionDO);

    /***
     * 查询所有菜单，构建树
     */
    Result listTree();

    /**
     * 根据用户adminId查询用户权限
     */
    Result getFunction(String adminId, String token);
}
