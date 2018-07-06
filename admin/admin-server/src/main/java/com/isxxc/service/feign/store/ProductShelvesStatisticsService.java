package com.isxxc.service.feign.store;

import com.isxxc.client.ProductShelvesStatisticsClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/***
 * <p>
 *  商品上下架信息统计 服务治理
 * </p>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@FeignClient("${feign-client.store-server}")
public interface ProductShelvesStatisticsService extends ProductShelvesStatisticsClient {
}
