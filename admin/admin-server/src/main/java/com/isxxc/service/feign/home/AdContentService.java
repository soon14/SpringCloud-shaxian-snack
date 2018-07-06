package com.isxxc.service.feign.home;

import com.isxxc.client.AdContentClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2017/12/30
 */
@FeignClient("${feign-client.home-server}")
public interface AdContentService extends AdContentClient {
}
