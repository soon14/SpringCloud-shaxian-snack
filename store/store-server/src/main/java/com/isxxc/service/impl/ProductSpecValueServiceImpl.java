package com.isxxc.service.impl;

import com.isxxc.domain.entity.ProductSpecValueDO;
import com.isxxc.dao.ProductSpecValueDAO;
import com.isxxc.service.ProductSpecValueService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 规格值 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpecValueServiceImpl extends ServiceImpl<ProductSpecValueDAO, ProductSpecValueDO> implements ProductSpecValueService {
	
}
