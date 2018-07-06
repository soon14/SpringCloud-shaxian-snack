package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ProductSalesVolumeDO;
import com.isxxc.domain.entity.ProductSkuStockDO;

/**
 * <p>
 * 商品销量 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-31
 */
public interface ProductSalesVolumeService extends IService<ProductSalesVolumeDO> {

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
