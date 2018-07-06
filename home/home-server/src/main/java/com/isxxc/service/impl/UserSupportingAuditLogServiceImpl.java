package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserSupportingAuditLogDAO;
import com.isxxc.domain.entity.UserSupportingAuditLogDO;
import com.isxxc.service.UserSupportingAuditLogService;

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
public class UserSupportingAuditLogServiceImpl extends ServiceImpl<UserSupportingAuditLogDAO, UserSupportingAuditLogDO> implements UserSupportingAuditLogService {

    @Override
    public Result auditLogBySupportingProfilId(Integer supportingProfilId) {
        EntityWrapper<UserSupportingAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("supporting_profil_id", supportingProfilId);
        return ResponseResult.success(selectList(entityWrapper));
    }
}
