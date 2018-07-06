package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.OrderStatisticsService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import javax.annotation.Resource;

import cc.likq.result.Result;
import cc.likq.util.TimeUtils;

/**
 * <p>
 * 订单统计 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@RestController
@RequestMapping("/orderStatistics")
public class OrderStatisticsController {

    @Resource
    private OrderStatisticsService orderStatisticsService;

    /***
     * 查询列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Date startTime, Date endTime) {
        if (startTime != null) {
            pager.putParam("startTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(startTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        if (endTime != null) {
            pager.putParam("endTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(endTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        return orderStatisticsService.listPage(pager);
    }
}
