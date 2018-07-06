package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.StoreAmountService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商店金额 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-05
 */
@RestController
@RequestMapping("/storeAmount")
public class StoreAmountController {

    @Resource
    private StoreAmountService storeAmountService;

    /***
     * 查询商家金额
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getInfoByStore", method = RequestMethod.GET)
    public Result getInfoByStore(Integer storeId) {
        return storeAmountService.getInfoByStore(storeId);
    }

    /***
     * 查询商家金额收支日志
     * @param pager
     * @param storeId
     * @param storeAmountId
     * @return
     */
    @RequestMapping(value = "getAmountLogByStore", method = RequestMethod.GET)
    public Result getAmountLogByStore(Pager pager, Integer storeId, Integer storeAmountId) {
        if (storeAmountId == null) {
            return ResponseResult.paramNotNull("商家金额ID不能为空");
        }
        pager.putParam("storeId", storeId);
        pager.putParam("storeAmountId", storeAmountId);
        return storeAmountService.getAmountLogByStore(pager);
    }
}
