package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.OrderInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.TimeUtils;

/**
 * <p>
 * 订单信息 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    /***
     * 查询订单列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer state, String orderNo, String consigneeName, String consigneeMobile, Date startTime, Date endTime) {
        if (state != null) {
            pager.putParam("state", state);
        }
        if (StringUtils.isNotBlank(orderNo)) {
            pager.putParam("orderNo", orderNo);
        }
        if (StringUtils.isNotBlank(consigneeName)) {
            pager.putParam("consigneeName", consigneeName);
        }
        if (StringUtils.isNotBlank(consigneeMobile)) {
            pager.putParam("consigneeMobile", consigneeMobile);
        }
        if (startTime != null) {
            pager.putParam("startTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(startTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        if (endTime != null) {
            pager.putParam("endTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(endTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        return orderInfoService.listPage(pager);
    }

    /***
     * 根据ID查询订单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("订单ID不能为空");
        }
        return orderInfoService.getInfoById(id);
    }
}
