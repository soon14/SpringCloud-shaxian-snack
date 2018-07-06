package com.isxxc.service.feign.store;

import com.isxxc.client.OrderRefundClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 退款订单 服务类
 * </p>
 *
 * @author 吴坚
 * @since 2018-5-9
 */
@FeignClient("${feign-client.store-server}")
public interface OrderRefundService extends OrderRefundClient {

}
