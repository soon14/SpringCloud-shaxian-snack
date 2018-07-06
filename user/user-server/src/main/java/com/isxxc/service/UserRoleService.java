package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserRoleDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-20
 */
public interface UserRoleService extends IService<UserRoleDO> {

    /***
     *添加角色
     * @param userRoleDO
     * @return
     */
    Result save(UserRoleDO userRoleDO);

    /***
     * 更新角色信息
     * @param userRoleDO
     * @return
     */
    Result updateInfo(UserRoleDO userRoleDO);

    /***
     * 查询列表
     * @return
     */
    Result listPage(Page<UserRoleDO> page);

    /***
     * 删除
     * @param id
     * @return
     */
    Result deleted(Integer id);
}
