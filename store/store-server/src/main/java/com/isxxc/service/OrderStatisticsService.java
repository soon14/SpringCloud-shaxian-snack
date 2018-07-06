package com.isxxc.service;

import com.isxxc.domain.dto.Pager;

import cc.likq.result.Result;

/**
 * <p>
 * 订单统计 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
public interface OrderStatisticsService {


    /***
     * 查询订单统计信息
     * @param pager
     * @return
     */
    Result listPage(Pager pager);
}
