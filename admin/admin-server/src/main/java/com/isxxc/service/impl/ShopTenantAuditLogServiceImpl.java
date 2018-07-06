package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ShopTenantAuditLogDAO;
import com.isxxc.dao.ShopTenantDAO;
import com.isxxc.domain.entity.ShopTenantAuditLogDO;
import com.isxxc.domain.entity.ShopTenantDO;
import com.isxxc.service.ShopTenantAuditLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTenantAuditLogServiceImpl extends ServiceImpl<ShopTenantAuditLogDAO, ShopTenantAuditLogDO> implements ShopTenantAuditLogService {

    @Resource
    private ShopTenantAuditLogDAO shopTenantAuditLogDAO;

    @Resource
    private ShopTenantDAO shopTenantDAO;

    @Override
    public Result getAuditLogByTenantId(Integer tenantId) {
        List<ShopTenantAuditLogDO> shopTenantAuditLogDOList = shopTenantAuditLogDAO.selectByTenantId(tenantId);
        return ResponseResult.success(shopTenantAuditLogDOList);
    }

    @Override
    public Result audit(ShopTenantAuditLogDO shopTenantAuditLogDO) {
        ShopTenantDO shopTenantDO = shopTenantDAO.selectById(shopTenantAuditLogDO.getShopTenantId());
        if (CommonStateEnum.AuditStateLog.PASSED.code == shopTenantAuditLogDO.getAuditState()) {
            shopTenantDO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
            shopTenantDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        } else {
            shopTenantDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
            shopTenantDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        shopTenantDAO.updateById(shopTenantDO);
        shopTenantAuditLogDAO.insert(shopTenantAuditLogDO);
        return ResponseResult.success();
    }
}
