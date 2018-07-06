package com.isxxc.service.impl;

import com.isxxc.domain.entity.ShopTransferAuditLogDO;
import com.isxxc.dao.ShopTransferAuditLogDAO;
import com.isxxc.service.ShopTransferAuditLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTransferAuditLogServiceImpl extends ServiceImpl<ShopTransferAuditLogDAO, ShopTransferAuditLogDO> implements ShopTransferAuditLogService {
	
}
