package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.StoreAmountDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 商店金额 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/storeAmountClient")
public interface StoreAmountClient {

    /***
     * 初始化商店总金额
     * @param storeAmountDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(StoreAmountDO storeAmountDO);

    /***
     * 查询商店详情
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getInfoByStore", method = RequestMethod.GET)
    Result getInfoByStore(@RequestParam("storeId") Integer storeId);

    /***
     * 查询商店金额日志
     * @param pager
     * @return
     */
    @RequestMapping(value = "getAmountLogByStore", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result getAmountLogByStore(Pager pager);

}
