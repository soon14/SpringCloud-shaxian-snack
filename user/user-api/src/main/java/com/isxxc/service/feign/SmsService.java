package com.isxxc.service.feign;

import com.isxxc.client.SmsClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2018/1/8
 */
@FeignClient("${feign-client.common-server}")
public interface SmsService extends SmsClient {
}
