package com.isxxc.client;


import com.isxxc.service.ProductSkuInfoService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 产品SKU 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class ProductSkuClientImpl implements ProductSkuClient {

    @Resource
    private ProductSkuInfoService productSkuInfoService;

    @Override
    public Result onShelvesBySkuId(Integer skuId, Integer storeId) {
        return productSkuInfoService.onShelvesBySkuId(skuId, storeId);
    }

    @Override
    public Result offShelvesBySkuId(Integer skuId, Integer storeId) {
        return productSkuInfoService.offShelvesBySkuId(skuId, storeId);
    }
}
