package com.isxxc.service.feign;

import com.isxxc.client.OpenUserClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2018/1/13
 */
@FeignClient("${feign-client.user-server}")
public interface OpenUserService extends OpenUserClient {
}
