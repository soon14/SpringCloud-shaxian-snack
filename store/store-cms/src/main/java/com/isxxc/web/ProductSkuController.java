package com.isxxc.web;


import com.isxxc.service.feign.store.ProductSkuService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 产品SKU信息 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/productSku")
public class ProductSkuController {

    @Resource
    private ProductSkuService productSkuService;

    /***
     * 商品SKU上架
     * @param skuId
     * @return
     */
    @RequestMapping(value = "onShelvesBySkuId", method = RequestMethod.POST)
    public Result onShelvesBySkuId(Integer skuId, Integer storeId) {
        if (skuId == null) {
            return ResponseResult.paramNotNull("商品SPUID不能为空");
        }
        return productSkuService.onShelvesBySkuId(skuId, storeId);
    }

    /***
     * 商品SKU下架
     * @param skuId
     * @return
     */
    @RequestMapping(value = "offShelvesBySkuId", method = RequestMethod.POST)
    public Result offShelvesById(Integer skuId, Integer storeId) {
        if (skuId == null) {
            return ResponseResult.paramNotNull("商品SPUID不能为空");
        }
        return productSkuService.offShelvesBySkuId(skuId, storeId);
    }

}
