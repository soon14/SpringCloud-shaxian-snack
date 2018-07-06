package com.isxxc.client;

import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.StoreExtractAmountLogDTO;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额日志 服务治理
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@RequestMapping("/storeExtractAmountLogClient")
public interface StoreExtractAmountLogClient {

    /***
     * 添加提取日志
     * @param storeExtractAmountLogDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(StoreExtractAmountLogDO storeExtractAmountLogDO);

    /***
     * 查询商店申请列表
     * @return
     */
    @RequestMapping(value = "listPageByStoreId", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPageByStoreId(Pager pager);

    /***
     * 获取金额详情
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getAmountInfo", method = RequestMethod.GET)
    Result getAmountInfo(@RequestParam("storeId") Integer storeId);

    /***
     * 查询申请列表
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 查询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result getInfoById(@RequestParam("id") Integer id);

    /***
     * 结算
     * @return
     */
    @RequestMapping(value = "settlementAmount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result settlementAmount(StoreExtractAmountLogDTO storeExtractAmountLogDTO);
}
