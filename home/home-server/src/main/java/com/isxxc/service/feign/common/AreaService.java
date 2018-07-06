package com.isxxc.service.feign.common;

import com.isxxc.client.AreaClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2018/1/8
 */
@FeignClient("${feign-client.common-server}")
public interface AreaService extends AreaClient {
}
