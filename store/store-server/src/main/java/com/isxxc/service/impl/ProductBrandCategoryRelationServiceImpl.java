package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductBrandCategoryRelationDO;
import com.isxxc.dao.ProductBrandCategoryRelationDAO;
import com.isxxc.service.ProductBrandCategoryRelationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品与品牌关联 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductBrandCategoryRelationServiceImpl extends ServiceImpl<ProductBrandCategoryRelationDAO, ProductBrandCategoryRelationDO> implements ProductBrandCategoryRelationService {
	
}
