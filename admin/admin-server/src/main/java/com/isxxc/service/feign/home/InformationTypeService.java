package com.isxxc.service.feign.home;

import com.isxxc.client.InformationTypeClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 泥水佬
 * @date 2017/12/30
 */
@FeignClient("${feign-client.home-server}")
public interface InformationTypeService extends InformationTypeClient {
}
