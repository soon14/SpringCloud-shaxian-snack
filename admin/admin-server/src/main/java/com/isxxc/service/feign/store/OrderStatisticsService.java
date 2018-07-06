package com.isxxc.service.feign.store;

import com.isxxc.client.OrderStatisticsClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 订单统计 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface OrderStatisticsService extends OrderStatisticsClient {

}
