package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.StoreExtractAmountLogDTO;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;
import com.isxxc.service.StoreExtractAmountLogService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额日志 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-1-05
 */
@RestController
public class StoreExtractAmountLogClientImpl implements StoreExtractAmountLogClient {

    @Resource
    private StoreExtractAmountLogService storeExtractAmountLogService;

    @Override
    public Result save(@RequestBody StoreExtractAmountLogDO storeExtractAmountLogDO) {
        return storeExtractAmountLogService.save(storeExtractAmountLogDO);
    }

    @Override
    public Result listPageByStoreId(@RequestBody Pager pager) {
        return storeExtractAmountLogService.listPageByStoreId(pager);
    }

    @Override
    public Result getAmountInfo(Integer storeId) {
        return storeExtractAmountLogService.getAmountInfo(storeId);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return storeExtractAmountLogService.listPage(pager);
    }

    @Override
    public Result getInfoById(Integer id) {
        return storeExtractAmountLogService.getInfoById(id);
    }

    @Override
    public Result settlementAmount(@RequestBody StoreExtractAmountLogDTO storeExtractAmountLogDTO) {
        return storeExtractAmountLogService.settlementAmount(storeExtractAmountLogDTO.getId(), storeExtractAmountLogDTO.getSettlementAmount(), storeExtractAmountLogDTO.getPoundage(), storeExtractAmountLogDTO.getRemarkService());
    }
}
