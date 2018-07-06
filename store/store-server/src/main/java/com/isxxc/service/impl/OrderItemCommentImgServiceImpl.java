package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.OrderItemCommentImgDAO;
import com.isxxc.domain.dto.OrderItemCommentImgDTO;
import com.isxxc.domain.entity.OrderItemCommentImgDO;
import com.isxxc.service.OrderItemCommentImgService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 订单商品评价图片 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemCommentImgServiceImpl extends ServiceImpl<OrderItemCommentImgDAO, OrderItemCommentImgDO> implements OrderItemCommentImgService {

    @Resource
    private OrderItemCommentImgDAO orderItemCommentImgDAO;

    @Override
    public List<OrderItemCommentImgDTO> selectDTOByCommentId(Long commentId) {
        return orderItemCommentImgDAO.selectDTOByCommentId(commentId);
    }
}
