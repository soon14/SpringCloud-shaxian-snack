package com.isxxc.service.feign.store;


import com.isxxc.client.ProductFreightTemplateClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 运费模版 服务
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface ProductFreightTemplateServer extends ProductFreightTemplateClient {

}
