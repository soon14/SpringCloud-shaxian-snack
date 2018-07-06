package com.isxxc.service.feign.store;

import com.isxxc.client.OrderInfoClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 商品订单 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface OrderInfoService extends OrderInfoClient {

}
