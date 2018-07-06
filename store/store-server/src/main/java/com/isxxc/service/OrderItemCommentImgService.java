package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.OrderItemCommentImgDTO;
import com.isxxc.domain.entity.OrderItemCommentImgDO;

import java.util.List;

/**
 * <p>
 * 订单商品评价图片 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-23
 */
public interface OrderItemCommentImgService extends IService<OrderItemCommentImgDO> {

    /***
     * 查询评价图片
     * @param commentId 评价ID
     * @return
     */
    List<OrderItemCommentImgDTO> selectDTOByCommentId(Long commentId);
}
