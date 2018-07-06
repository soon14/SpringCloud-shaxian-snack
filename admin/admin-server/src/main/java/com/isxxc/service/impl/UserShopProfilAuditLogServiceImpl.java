package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserShopProfilAuditLogDAO;
import com.isxxc.domain.entity.UserShopProfilAuditLogDO;
import com.isxxc.service.UserShopProfilAuditLogService;

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
public class UserShopProfilAuditLogServiceImpl extends ServiceImpl<UserShopProfilAuditLogDAO, UserShopProfilAuditLogDO> implements UserShopProfilAuditLogService {

    @Override
    public Result auditLogByShopProfilId(Integer shopProfilId) {
        EntityWrapper<UserShopProfilAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("shop_profil_id", shopProfilId);
        return ResponseResult.success(selectList(entityWrapper));
    }
}
