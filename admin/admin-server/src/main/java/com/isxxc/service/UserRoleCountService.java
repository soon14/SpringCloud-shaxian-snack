package com.isxxc.service;

import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserRoleDTO;
import com.isxxc.domain.entity.UserRoleDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-21
 */
public interface UserRoleCountService extends IService<UserRoleDO> {

    /***
     * 用户角色 -- 门店商户  统计
     * @param userRoleDTO
     * @return
     */
    Result countUserShop(UserRoleDTO userRoleDTO);
    /***
     * 用户角色 -- 原料供应商  统计
     * @param userRoleDTO
     * @return
     */
    Result countUserStore(UserRoleDTO userRoleDTO);
    /***
     * 用户角色 -- 配套服务/文化服务  统计
     * @param userRoleDTO
     * @return
     */
    Result countUserSupporting(UserRoleDTO userRoleDTO);
    /***
     * 用户角色 -- 金融服务  统计
     * @param userRoleDTO
     * @return
     */
    Result countUserBanking(UserRoleDTO userRoleDTO);
    /***
     * 用户角色  -- 时间段查询
     * @param pager
     * @return
     */
    Pager listPage(Pager pager);
}
