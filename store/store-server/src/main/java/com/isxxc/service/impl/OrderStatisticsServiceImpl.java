package com.isxxc.service.impl;

import com.isxxc.dao.OrderStatisticsDAO;
import com.isxxc.domain.dto.OrderStatisticsDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.service.OrderStatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 订单统计 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderStatisticsServiceImpl implements OrderStatisticsService {

    @Resource
    private OrderStatisticsDAO orderStatisticsDAO;

    @Override
    public Result listPage(Pager pager) {
        List<OrderStatisticsDTO> orderStatisticsDTOList = orderStatisticsDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(orderStatisticsDTOList);
        return ResponseResult.success(pager);
    }
}
