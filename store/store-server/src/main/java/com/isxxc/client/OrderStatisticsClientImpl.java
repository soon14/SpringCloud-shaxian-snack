package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.OrderStatisticsService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 订单统计 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class OrderStatisticsClientImpl implements OrderStatisticsClient {

    @Resource
    private OrderStatisticsService orderStatisticsService;

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return orderStatisticsService.listPage(pager);
    }
}
