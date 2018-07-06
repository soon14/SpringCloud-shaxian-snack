package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.LoansInfoAuditLogDAO;
import com.isxxc.domain.entity.LoansInfoAuditLogDO;
import com.isxxc.service.LoansInfoAuditLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Result getAuditLogByLoansInfoId(Integer loansInfoId) {
        EntityWrapper<LoansInfoAuditLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("loans_info_id", loansInfoId);
        return ResponseResult.success(selectList(entityWrapper));
    }
}
