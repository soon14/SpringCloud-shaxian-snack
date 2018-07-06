package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.FinancingInfoAuditLogDAO;
import com.isxxc.domain.entity.FinancingInfoAuditLogDO;
import com.isxxc.service.FinancingInfoAuditLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class FinancingInfoAuditLogServiceImpl extends ServiceImpl<FinancingInfoAuditLogDAO, FinancingInfoAuditLogDO> implements FinancingInfoAuditLogService {

    @Override
    public Result getAuditLogByFinancingInfoId(Integer financingInfoId) {
        EntityWrapper<FinancingInfoAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("financing_info_id", financingInfoId);
        List<FinancingInfoAuditLogDO> financingInfoAuditLogDOList = selectList(entityWrapper);
        return ResponseResult.success(financingInfoAuditLogDOList);
    }
}
