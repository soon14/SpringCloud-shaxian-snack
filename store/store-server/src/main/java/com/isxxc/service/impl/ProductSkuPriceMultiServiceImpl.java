package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductSkuPriceMultiDO;
import com.isxxc.dao.ProductSkuPriceMultiDAO;
import com.isxxc.service.ProductSkuPriceMultiService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * SKU批量价 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuPriceMultiServiceImpl extends ServiceImpl<ProductSkuPriceMultiDAO, ProductSkuPriceMultiDO> implements ProductSkuPriceMultiService {
	
}
