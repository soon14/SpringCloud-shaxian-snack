package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.ProductSalesVolumeDO;
import com.isxxc.domain.entity.ProductSkuStockDO;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品销量 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-31
 */
@Repository
public interface ProductSalesVolumeDAO extends BaseMapper<ProductSalesVolumeDO> {

    /***
     * 根据SPUID查询总销量
     * @param spuId
     * @return
     */
    Integer selectTotalSalesVolumeBySpuId(Integer spuId);

    /***
     * 根据SKUID查询销量
     * @param skuId
     * @return
     */
    ProductSalesVolumeDO selectBySkuId(Integer skuId);
}