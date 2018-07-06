package com.isxxc.web;


import com.isxxc.service.feign.store.ProductStockService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商品库存 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/productStock")
public class ProductStockController {

    @Resource
    private ProductStockService productStockService;

    /***
     * 更新库存
     * @param stockId
     * @param num
     * @param storeId
     * @return
     */
    @RequestMapping(value = "upadteNum", method = RequestMethod.POST)
    public Result upadteNum(Integer stockId, Integer num, Integer storeId, String remark) {
        if (stockId == null) {
            return ResponseResult.paramNotNull("库存ID不能为空");
        }
        if (num == null) {
            return ResponseResult.paramNotNull("数量不能为空");
        }
        return productStockService.upadteNum(stockId, num, storeId, remark);
    }

}
