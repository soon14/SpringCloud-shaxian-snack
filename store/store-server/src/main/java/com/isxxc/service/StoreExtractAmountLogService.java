package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;

import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额日志 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
public interface StoreExtractAmountLogService extends IService<StoreExtractAmountLogDO> {

    /***
     * 添加提取日志
     * @param storeExtractAmountLogDO
     * @return
     */
    Result save(StoreExtractAmountLogDO storeExtractAmountLogDO);

    /***
     * 查询日志列表
     * @return
     */
    Result listPageByStoreId(Pager pager);

    /***
     * 查询金额详情
     * @param storeId
     * @return
     */
    Result getAmountInfo(Integer storeId);

    /***
     * 查询申请列表
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);

    /***
     * 结算
     * @param settlementAmount
     * @param poundage
     * @param remark
     * @return
     */
    Result settlementAmount(Integer extractAmountLogId, Long settlementAmount, Long poundage, String remark);
}
