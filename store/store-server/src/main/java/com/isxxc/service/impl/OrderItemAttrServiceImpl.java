package com.isxxc.service.impl;

import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.dao.OrderItemAttrDAO;
import com.isxxc.service.OrderItemAttrService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单子项属性 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemAttrServiceImpl extends ServiceImpl<OrderItemAttrDAO, OrderItemAttrDO> implements OrderItemAttrService {
	
}
