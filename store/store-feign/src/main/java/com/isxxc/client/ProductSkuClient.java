package com.isxxc.client;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 产品SKU 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/productSkuClient")
public interface ProductSkuClient {
    /***
     * 商品SKU上架
     * @param skuId
     * @param storeId
     * @return
     */
    @RequestMapping(value = "onShelvesBySkuId", method = RequestMethod.POST)
    Result onShelvesBySkuId(@RequestParam("skuId") Integer skuId, @RequestParam("storeId") Integer storeId);

    /***
     * 商品SKU下架
     * @param skuId
     * @param storeId
     * @return
     */
    @RequestMapping(value = "offShelvesBySkuId", method = RequestMethod.POST)
    Result offShelvesBySkuId(@RequestParam("skuId") Integer skuId, @RequestParam("storeId") Integer storeId);
}
