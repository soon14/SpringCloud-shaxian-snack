package com.isxxc.client;


import com.isxxc.service.ProductSkuStockService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 商品库存 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class ProductStockClientImpl implements ProductStockClient {

    @Resource
    private ProductSkuStockService productSkuStockService;


    @Override
    public Result upadteNum(Integer stockId, Integer num, Integer storeId, String remark) {
        return productSkuStockService.upadteNum(stockId, num, storeId, remark);
    }
}
