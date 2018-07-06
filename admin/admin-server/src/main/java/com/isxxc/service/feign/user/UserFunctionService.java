package com.isxxc.service.feign.user;

import com.isxxc.client.UserFunctionClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-21
 */
@FeignClient("${feign-client.user-server}")
public interface UserFunctionService extends UserFunctionClient {

}
