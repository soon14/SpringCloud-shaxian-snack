package com.isxxc.service.impl;

import com.isxxc.domain.entity.ShopTenantAuditLogDO;
import com.isxxc.dao.ShopTenantAuditLogDAO;
import com.isxxc.service.ShopTenantAuditLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTenantAuditLogServiceImpl extends ServiceImpl<ShopTenantAuditLogDAO, ShopTenantAuditLogDO> implements ShopTenantAuditLogService {
	
}
