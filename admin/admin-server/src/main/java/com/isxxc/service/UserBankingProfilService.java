package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserBankingProfilDTO;
import com.isxxc.domain.entity.UserBankingProfilAuditLogDO;
import com.isxxc.domain.entity.UserBankingProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
public interface UserBankingProfilService extends IService<UserBankingProfilDO> {

    /***
     * 查询列表
     * @param page
     * @param userBankingProfilDO
     * @return
     */
    Result list(Page<UserBankingProfilDTO> page, UserBankingProfilDO userBankingProfilDO);

    /***
     * 档案审核
     * @param userBankingProfilAuditLogDO
     * @return
     */
    Result audit(UserBankingProfilAuditLogDO userBankingProfilAuditLogDO);

    /***
     * 查询内容详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);
}
