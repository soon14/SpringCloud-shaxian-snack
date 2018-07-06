package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.StoreExtractAmountAuditLogDAO;
import com.isxxc.domain.entity.StoreExtractAmountAuditLogDO;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;
import com.isxxc.service.StoreExtractAmountAuditLogService;
import com.isxxc.service.StoreExtractAmountLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreExtractAmountAuditLogServiceImpl extends ServiceImpl<StoreExtractAmountAuditLogDAO, StoreExtractAmountAuditLogDO> implements StoreExtractAmountAuditLogService {

    @Resource
    private StoreExtractAmountLogService storeExtractAmountLogService;

    @Override
    public Result audit(StoreExtractAmountAuditLogDO auditLogDO) {
        StoreExtractAmountLogDO storeExtractAmountLogDO = storeExtractAmountLogService.selectById(auditLogDO.getExtractAmountLogId());
        if (storeExtractAmountLogDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "提取金额日志ID错误");
        }
        if (storeExtractAmountLogDO.getAuditState() == CommonStateEnum.AuditState.PASSED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经审核通过,不要重复提交");
        }
        if (auditLogDO.getAuditState() == CommonStateEnum.AuditStateLog.PASSED.code) {
            storeExtractAmountLogDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        } else {
            storeExtractAmountLogDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        storeExtractAmountLogService.updateById(storeExtractAmountLogDO);
        return insert(auditLogDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
