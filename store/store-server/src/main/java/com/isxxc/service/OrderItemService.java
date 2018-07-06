package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.constant.OrderStateEnum;
import com.isxxc.domain.dto.OrderItemDTO;
import com.isxxc.domain.entity.OrderItemDO;

import java.util.List;

/**
 * <p>
 * 订单子项 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
public interface OrderItemService extends IService<OrderItemDO> {

    /***
     * 根据订单ID查询
     * @param orderId
     * @return
     */
    List<OrderItemDTO> selectDTOByOrderId(Integer orderId);

    /***
     * 根据订单更新评论状态
     * @param orderId
     * @param commentState
     * @return
     */
    int updateCommentByOrderId(Integer orderId, OrderStateEnum.CommentState commentState);
}
