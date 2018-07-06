package cc.likq.base.service;

import com.baomidou.mybatisplus.service.IService;

import cc.likq.base.domain.entity.SystemRoleDO;
import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;

import java.util.List;

/**
 * <p>
 *  系统角色表 服务类
 * </p>
 *
 * @author likq
 * @since  2017-11-22
 * @update 2018-05-30
 */
public interface SystemRoleService extends IService<SystemRoleDO> {

    /***
     *添加角色
     */
    Result save(SystemRoleDO systemRoleDO);

    /**
     * 更新信息
     */
    Result updateInfo(SystemRoleDO systemRoleDO);

    /***
     *删除角色
     */
    Result remove(Integer id);

    /***
     *分页查询系统角色
     */
    Result listPage(Pager pager);

    /***
     * 查询系统角色
     */
    Result query ();

}
