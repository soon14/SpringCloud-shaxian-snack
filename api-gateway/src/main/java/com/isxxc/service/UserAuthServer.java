package com.isxxc.service;

import com.isxxc.client.UserAuthClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */

@FeignClient("${feignClient.authServer}")
public interface UserAuthServer extends UserAuthClient {
}
