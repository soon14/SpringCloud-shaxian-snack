package com.isxxc.service.feign.store;

import com.isxxc.client.StoreAmountClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 商店金额收支 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface StoreAmountService extends StoreAmountClient {

}
