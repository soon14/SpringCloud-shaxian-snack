package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ExpressCompanyDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ExpressCompanyDO;
import com.isxxc.service.ExpressCompanyService;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpressCompanyServiceImpl extends ServiceImpl<ExpressCompanyDAO, ExpressCompanyDO> implements ExpressCompanyService {

    @Override
    public Result listPage(Pager pager) {
        EntityWrapper<ExpressCompanyDO> expressCompanyDOEntityWrapper = new EntityWrapper<>();
        if (MapUtils.isNotEmpty(pager.getParamMap()) && pager.getParamMap().containsKey("name")) {
            expressCompanyDOEntityWrapper.like("name", "%" + pager.getParamMap().get("name") + "%");
        }
        return ResponseResult.success(selectPage(pager, expressCompanyDOEntityWrapper));
    }
}
