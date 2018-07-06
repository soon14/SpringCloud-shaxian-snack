package com.isxxc.service.feign.store;

import com.isxxc.client.OrderInfoClient;
import com.isxxc.client.OrderRefundClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 商品订单退款 服务类
 * </p>
 *
 * @author mc
 * @since 2018-05-10
 *
 */
@FeignClient("${feign-client.store-server}")
public interface OrderRefundService extends OrderRefundClient {

}
