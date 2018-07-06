package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductSpecGroupDO;
import com.isxxc.dao.ProductSpecGroupDAO;
import com.isxxc.service.ProductSpecGroupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品规格组 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpecGroupServiceImpl extends ServiceImpl<ProductSpecGroupDAO, ProductSpecGroupDO> implements ProductSpecGroupService {
	
}
