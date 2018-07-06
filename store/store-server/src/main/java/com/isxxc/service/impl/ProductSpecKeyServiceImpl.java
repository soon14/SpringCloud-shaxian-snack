package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductSpecKeyDO;
import com.isxxc.dao.ProductSpecKeyDAO;
import com.isxxc.service.ProductSpecKeyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品规格名称 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpecKeyServiceImpl extends ServiceImpl<ProductSpecKeyDAO, ProductSpecKeyDO> implements ProductSpecKeyService {
	
}
