package com.isxxc.service.feign.store;

import com.isxxc.client.PaymentClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 支付 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface PaymentService extends PaymentClient {

}
