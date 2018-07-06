package com.isxxc.service.feign.store;

import com.isxxc.client.ProductInformationClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 商品详细信息 服务类
 * </p>
 *
 * @author yk
 * @since 2018-05-17
 */
@FeignClient("${feign-client.store-server}")
public interface ProductInformationService extends ProductInformationClient {

}
