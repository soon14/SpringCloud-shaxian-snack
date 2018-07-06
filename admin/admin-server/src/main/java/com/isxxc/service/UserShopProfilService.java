package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserShopProfilDTO;
import com.isxxc.domain.entity.UserShopProfilAuditLogDO;
import com.isxxc.domain.entity.UserShopProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserShopProfilService extends IService<UserShopProfilDO> {

    /***
     * 查询列表
     * @param page
     * @param userShopProfilDO
     * @return
     */
    Result listPage(Page<UserShopProfilDTO> page, UserShopProfilDO userShopProfilDO);

    /***
     * 档案审核
     * @param userShopProfilAuditLogDO
     * @return
     */
    Result audit(UserShopProfilAuditLogDO userShopProfilAuditLogDO);

    /***
     *根据ID查询详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);
}
