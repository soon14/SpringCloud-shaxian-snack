package com.isxxc.service.impl;

import com.isxxc.domain.entity.OrderPayMergerDO;
import com.isxxc.dao.OrderPayMergerDAO;
import com.isxxc.service.OrderPayMergerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单合并支付 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderPayMergerServiceImpl extends ServiceImpl<OrderPayMergerDAO, OrderPayMergerDO> implements OrderPayMergerService {
	
}
