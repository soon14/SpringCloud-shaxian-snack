package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductAttrValueDAO;
import com.isxxc.domain.entity.ProductAttrValueDO;
import com.isxxc.service.ProductAttrValueService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 商品属性值 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDAO, ProductAttrValueDO> implements ProductAttrValueService {

    @Resource
    private ProductAttrValueDAO productAttrValueDAO;

    @Override
    public ProductAttrValueDO selectByName(String name) {
        return productAttrValueDAO.selectByName(name);
    }
}
