package com.isxxc.service.feign.user;

import com.isxxc.client.UserAddressClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 会员地址 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface UserAddressService extends UserAddressClient {

}
