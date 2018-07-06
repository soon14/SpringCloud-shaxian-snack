package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.UserStoreProfilAuditLogDO;
import com.isxxc.domain.entity.UserStoreProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserStoreProfilService extends IService<UserStoreProfilDO> {

    /**
     * 查询档案列表
     */
    Result list(Page<UserStoreProfilDTO> page, UserStoreProfilDO userStoreProfilDO);

    /***
     *审核档案
     * @param userStoreProfilAuditLogDO
     * @return
     */
    Result audit(UserStoreProfilAuditLogDO userStoreProfilAuditLogDO);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);
}
