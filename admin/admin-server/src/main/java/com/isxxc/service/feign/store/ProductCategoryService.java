package com.isxxc.service.feign.store;

import com.isxxc.client.ProductCategoryClient;
import com.isxxc.domain.entity.ProductCategoryDO;

import org.springframework.cloud.netflix.feign.FeignClient;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface ProductCategoryService extends ProductCategoryClient {

}
