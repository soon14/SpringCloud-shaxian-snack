package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductSkuSpecRelationDO;
import com.isxxc.dao.ProductSkuSpecRelationDAO;
import com.isxxc.service.ProductSkuSpecRelationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品SKU与规格关联 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuSpecRelationServiceImpl extends ServiceImpl<ProductSkuSpecRelationDAO, ProductSkuSpecRelationDO> implements ProductSkuSpecRelationService {
	
}
