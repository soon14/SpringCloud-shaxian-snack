package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductSalesVolumeDAO;
import com.isxxc.domain.entity.ProductSalesVolumeDO;
import com.isxxc.domain.entity.ProductSkuStockDO;
import com.isxxc.service.ProductSalesVolumeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 商品销量 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSalesVolumeServiceImpl extends ServiceImpl<ProductSalesVolumeDAO, ProductSalesVolumeDO> implements ProductSalesVolumeService {

    @Resource
    private ProductSalesVolumeDAO productSalesVolumeDAO;

    @Override
    public Integer selectTotalSalesVolumeBySpuId(Integer spuId) {
        return productSalesVolumeDAO.selectTotalSalesVolumeBySpuId(spuId);
    }

    @Override
    public ProductSalesVolumeDO selectBySkuId(Integer skuId) {
        return productSalesVolumeDAO.selectBySkuId(skuId);
    }
}
