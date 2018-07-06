package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductSkuAttrRelationDAO;
import com.isxxc.domain.dto.ProductAttrInfoDTO;
import com.isxxc.domain.entity.ProductSkuAttrRelationDO;
import com.isxxc.service.ProductSkuAttrRelationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * SKU与属性关联 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuAttrRelationServiceImpl extends ServiceImpl<ProductSkuAttrRelationDAO, ProductSkuAttrRelationDO> implements ProductSkuAttrRelationService {

    @Resource
    private ProductSkuAttrRelationDAO productSkuAttrRelationDAO;

    @Override
    public List<ProductAttrInfoDTO> selectDTOBySkuId(Integer skuId, Integer isDeleted) {
        return productSkuAttrRelationDAO.selectDTOBySkuId(skuId, isDeleted);
    }

    @Override
    public ProductAttrInfoDTO selectDTOById(Integer id) {
        return productSkuAttrRelationDAO.selectDTOById(id);
    }
}
