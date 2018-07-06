package com.isxxc.client;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 商品库存 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/productStockClient")
public interface ProductStockClient {

    /***
     * 更新库存
     * @param stockId
     * @param num
     * @param storeId
     * @return
     */
    @RequestMapping(value = "upadteNum", method = RequestMethod.POST)
    Result upadteNum(@RequestParam("stockId") Integer stockId, @RequestParam("num") Integer num, @RequestParam("storeId") Integer storeId, @RequestParam("remark") String remark);
}
