package com.isxxc.client;


import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.service.OrderItemCommentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 订单商品评价 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@RestController
public class OrderItemCommentClientImpl implements OrderItemCommentClient {

    @Resource
    private OrderItemCommentService orderItemCommentService;
    Logger logger = LogManager.getLogger(OrderItemCommentClientImpl.class);
    @Override
    public Result add(@RequestBody OrderItemCommentDTO orderItemCommentDTO) {
        return orderItemCommentService.add(orderItemCommentDTO);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return orderItemCommentService.listPage(pager);
    }

    @Override
    public Result getInfoByOrderItemId(Integer orderItemId) {
        return orderItemCommentService.getInfoByOrderItemId(orderItemId);
    }

    @Override
    public Result getInfoByUserId(Pager pager,Integer userId) {
        return orderItemCommentService.getInfoByUserId(pager,userId);
    }

    @Override
    public Result getInfoByStoreId( Pager pager,Integer storeId) {
        return orderItemCommentService.getInfoByStoreId(pager,storeId);
    }

    @Override
    public Result replyComments(@RequestBody OrderItemCommentDTO orderItemCommentDTO) {
        logger.info("OrderItemCommentClientImpl 商家回复ClientImpl入参："+orderItemCommentDTO);
        return orderItemCommentService.replyComments(orderItemCommentDTO);
    }

}
