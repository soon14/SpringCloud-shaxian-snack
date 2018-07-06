package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ShopTransferAuditLogDAO;
import com.isxxc.dao.ShopTransferDAO;
import com.isxxc.domain.entity.ShopTransferAuditLogDO;
import com.isxxc.domain.entity.ShopTransferDO;
import com.isxxc.service.ShopTransferAuditLogService;

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
public class ShopTransferAuditLogServiceImpl extends ServiceImpl<ShopTransferAuditLogDAO, ShopTransferAuditLogDO> implements ShopTransferAuditLogService {

    @Resource
    private ShopTransferAuditLogDAO shopTransferAuditLogDAO;

    @Resource
    private ShopTransferDAO shopTransferDAO;

    @Override
    public Result getAuditLogByTransferId(Integer transferId) {
        List<ShopTransferAuditLogDO> shopTransferAuditLogDOList = shopTransferAuditLogDAO.selectByTransferId(transferId);
        return ResponseResult.success(shopTransferAuditLogDOList);
    }

    @Override
    public Result audit(ShopTransferAuditLogDO shopTransferAuditLogDO) {
        ShopTransferDO shopTransferDO = shopTransferDAO.selectById(shopTransferAuditLogDO.getShopTransferId());
        if (CommonStateEnum.AuditStateLog.PASSED.code == shopTransferAuditLogDO.getAuditState()) {
            shopTransferDO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
            shopTransferDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        } else {
            shopTransferDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
            shopTransferDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        shopTransferDAO.updateById(shopTransferDO);
        shopTransferAuditLogDAO.insert(shopTransferAuditLogDO);
        return ResponseResult.success();
    }
}
