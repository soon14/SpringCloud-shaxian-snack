package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserStoreProfilAuditLogDAO;
import com.isxxc.domain.entity.UserStoreProfilAuditLogDO;
import com.isxxc.service.UserStoreProfilAuditLogService;

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
public class UserStoreProfilAuditLogServiceImpl extends ServiceImpl<UserStoreProfilAuditLogDAO, UserStoreProfilAuditLogDO> implements UserStoreProfilAuditLogService {

    @Override
    public Result auditLogByStoreProfilId(Integer storeProfilId) {
        EntityWrapper<UserStoreProfilAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_profil_id", storeProfilId);
        return ResponseResult.success(selectList(entityWrapper));
    }
}
