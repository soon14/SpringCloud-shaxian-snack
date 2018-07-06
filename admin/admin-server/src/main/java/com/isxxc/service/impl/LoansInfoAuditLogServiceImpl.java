package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.LoansInfoAuditLogDAO;
import com.isxxc.dao.LoansInfoDAO;
import com.isxxc.domain.entity.LoansInfoAuditLogDO;
import com.isxxc.domain.entity.LoansInfoDO;
import com.isxxc.service.LoansInfoAuditLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoansInfoAuditLogServiceImpl extends ServiceImpl<LoansInfoAuditLogDAO, LoansInfoAuditLogDO> implements LoansInfoAuditLogService {

    @Resource
    private LoansInfoDAO loansInfoDAO;

    @Override
    public Result getAuditLogByLoansInfoId(Integer loansInfoId) {
        EntityWrapper<LoansInfoAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("loans_info_id", loansInfoId);
        return ResponseResult.success(selectList(entityWrapper));
    }

    @Override
    public Result audit(LoansInfoAuditLogDO loansInfoAuditLogDO) {
        LoansInfoDO financingInfoDO = loansInfoDAO.selectById(loansInfoAuditLogDO.getLoansInfoId());
        if (CommonStateEnum.AuditStateLog.PASSED.code == loansInfoAuditLogDO.getAuditState()) {
            financingInfoDO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
            financingInfoDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        } else {
            financingInfoDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        loansInfoDAO.updateById(financingInfoDO);
        insert(loansInfoAuditLogDO);
        return ResponseResult.success();
    }
}
