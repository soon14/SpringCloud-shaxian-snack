package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.StoreAmountLogDAO;
import com.isxxc.domain.entity.StoreAmountLogDO;
import com.isxxc.service.StoreAmountLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 商店金额 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreAmountLogServiceImpl extends ServiceImpl<StoreAmountLogDAO, StoreAmountLogDO> implements StoreAmountLogService {

}
