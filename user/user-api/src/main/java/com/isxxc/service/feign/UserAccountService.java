package com.isxxc.service.feign;

import com.isxxc.client.UserAccountClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2018/1/13
 */
@FeignClient("${feign-client.user-server}")
public interface UserAccountService extends UserAccountClient {
}
