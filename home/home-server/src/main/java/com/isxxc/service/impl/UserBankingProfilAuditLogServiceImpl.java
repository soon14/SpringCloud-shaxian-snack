package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserBankingProfilAuditLogDAO;
import com.isxxc.domain.entity.UserBankingProfilAuditLogDO;
import com.isxxc.service.UserBankingProfilAuditLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBankingProfilAuditLogServiceImpl extends ServiceImpl<UserBankingProfilAuditLogDAO, UserBankingProfilAuditLogDO> implements UserBankingProfilAuditLogService {

    @Override
    public Result auditLogByBankingProfilId(Integer bankingProfilId) {
        EntityWrapper<UserBankingProfilAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("banking_profil_id", bankingProfilId);
        return ResponseResult.success(selectList(entityWrapper));
    }
}
