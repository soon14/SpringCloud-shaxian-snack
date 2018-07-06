package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ProductSkuStockDO;

import cc.likq.result.Result;

/**
 * <p>
 * SKU商品库存 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-17
 */
public interface ProductSkuStockService extends IService<ProductSkuStockDO> {

    /***
     * 更新库存
     * @param stockId
     * @param num
     * @param storeId
     * @return
     */
    Result upadteNum(Integer stockId, Integer num, Integer storeId, String remark);

    /***
     * 根据SKUID查询库存
     * @param skuId
     * @return
     */
    ProductSkuStockDO selectBySkuId(Integer skuId);
}
