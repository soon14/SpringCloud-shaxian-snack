package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserAccountDO;
import com.isxxc.domain.entity.UserFunctionDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
public interface UserFunctionService extends IService<UserFunctionDO> {

    /***
     * 添加功能菜单
     * @param userFunctionDO
     * @return
     */
    Result save(UserFunctionDO userFunctionDO);

    /***
     * 查询功能菜单，构建树
     * @return
     */
    Result functionTree();

    /***
     * 更新菜单信息
     * @param userFunctionDO
     * @return
     */
    Result updateInfo(UserFunctionDO userFunctionDO);
}
