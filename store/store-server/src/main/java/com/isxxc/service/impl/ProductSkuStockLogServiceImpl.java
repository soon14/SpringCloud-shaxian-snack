package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductSkuStockLogDO;
import com.isxxc.dao.ProductSkuStockLogDAO;
import com.isxxc.service.ProductSkuStockLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * SKU商品库存记录 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuStockLogServiceImpl extends ServiceImpl<ProductSkuStockLogDAO, ProductSkuStockLogDO> implements ProductSkuStockLogService {
	
}
