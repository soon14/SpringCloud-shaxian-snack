package com.isxxc.client;


import com.isxxc.domain.dto.Pager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.likq.result.Result;

/**
 * <p>
 * 订单信息 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/orderStatisticsClient")
public interface OrderStatisticsClient {
    /***
     * 查询订单统计信息列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);
}
