package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.StoreAmountDO;
import com.isxxc.service.StoreAmountService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商店金额 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-1-05
 */
@RestController
public class StoreAmountClientImpl implements StoreAmountClient {

    @Resource
    private StoreAmountService storeAmountService;

    @Override
    public Result save(@RequestBody StoreAmountDO storeAmountDO) {
        return storeAmountService.insert(storeAmountDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result getInfoByStore(Integer storeId) {
        return storeAmountService.getInfoByStore(storeId);
    }

    @Override
    public Result getAmountLogByStore(@RequestBody Pager pager) {
        return storeAmountService.getAmountLogByStore(pager);
    }
}
