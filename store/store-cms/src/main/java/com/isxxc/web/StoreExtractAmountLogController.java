package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;
import com.isxxc.service.feign.store.StoreExtractAmountLogService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额日志 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-06
 */
@RestController
@RequestMapping("/storeExtractAmountLog")
public class StoreExtractAmountLogController {

    @Resource
    private StoreExtractAmountLogService storeExtractAmountLogService;

    /***
     * 添加申请记录
     * @param storeExtractAmountLogDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(StoreExtractAmountLogDO storeExtractAmountLogDO) {
        if (storeExtractAmountLogDO.getStoreId() == null) {
            return ResponseResult.paramNotNull("商店ID不能为空");
        }
        if (StringUtils.isBlank(storeExtractAmountLogDO.getBankName())) {
            return ResponseResult.paramNotNull("到帐银行不能为空");
        }
        if (StringUtils.isBlank(storeExtractAmountLogDO.getBankNo())) {
            return ResponseResult.paramNotNull("到账卡号不能为空");
        }
        if (StringUtils.isBlank(storeExtractAmountLogDO.getBankUserName())) {
            return ResponseResult.paramNotNull("户主姓名不能为空");
        }
        return storeExtractAmountLogService.save(storeExtractAmountLogDO);
    }

    /***
     * 查询列表
     * @param storeId
     * @param settlementState
     * @return
     */
    @RequestMapping(value = "listPageByStoreId", method = RequestMethod.GET)
    public Result listPageByStoreId(Pager pager, Integer storeId, Integer settlementState) {
        pager.putParam("storeId", storeId);
        if (settlementState != null) {
            pager.putParam("settlementState", settlementState);
        }
        return storeExtractAmountLogService.listPageByStoreId(pager);
    }

    /***
     * 查询金额详情
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getAmountInfo", method = RequestMethod.GET)
    public Result getAmountInfo(Integer storeId) {
        return storeExtractAmountLogService.getAmountInfo(storeId);
    }
}
