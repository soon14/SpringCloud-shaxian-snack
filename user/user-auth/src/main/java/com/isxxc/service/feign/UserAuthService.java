package com.isxxc.service.feign;

import com.isxxc.client.UserAuthClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 授权服务
 *
 * @author 泥水佬
 * @date 2018/1/13
 */
@FeignClient("${feign-client.user-server}")
public interface UserAuthService extends UserAuthClient {
}
