package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserSupportingProfilDTO;
import com.isxxc.domain.entity.UserSupportingAuditLogDO;
import com.isxxc.domain.entity.UserSupportingProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserSupportingProfilService extends IService<UserSupportingProfilDO> {

    /***
     *查询列表
     * @param page
     * @param userSupportingProfilDO
     * @return
     */
    Result list(Page<UserSupportingProfilDTO> page, UserSupportingProfilDO userSupportingProfilDO);

    /***
     * 内容审核
     * @param userSupportingAuditLogDO
     * @return
     */
    Result audit(UserSupportingAuditLogDO userSupportingAuditLogDO);

    /***
     * 查询内容详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);
}
