package com.isxxc.service.feign.store;

import com.isxxc.client.OrderItemCommentClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 订单商品评论 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@FeignClient("${feign-client.store-server}")
public interface OrderItemCommentService extends OrderItemCommentClient {

}
