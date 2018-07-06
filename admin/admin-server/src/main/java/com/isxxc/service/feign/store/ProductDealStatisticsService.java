package com.isxxc.service.feign.store;

import com.isxxc.client.ProductDealStatisticsClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/***
 * <p>
 *  商品成交信息统计 服务治理
 * </p>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@FeignClient("${feign-client.store-server}")
public interface ProductDealStatisticsService extends ProductDealStatisticsClient {
}
