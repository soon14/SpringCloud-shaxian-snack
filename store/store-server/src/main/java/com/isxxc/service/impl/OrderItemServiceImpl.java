package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.OrderStateEnum;
import com.isxxc.dao.OrderItemDAO;
import com.isxxc.domain.dto.OrderItemDTO;
import com.isxxc.domain.entity.OrderItemDO;
import com.isxxc.service.OrderItemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 订单子项 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDAO, OrderItemDO> implements OrderItemService {

    @Resource
    private OrderItemDAO orderItemDAO;

    @Override
    public List<OrderItemDTO> selectDTOByOrderId(Integer orderId) {
        return orderItemDAO.selectDTOByOrderId(orderId);
    }

    @Override
    public int updateCommentByOrderId(Integer orderId, OrderStateEnum.CommentState commentState) {
        return orderItemDAO.updateCommentByOrderId(orderId, commentState.code);
    }
}
