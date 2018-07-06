package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.OrderItemCommentImgDTO;
import com.isxxc.domain.entity.OrderItemCommentImgDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单商品评价图片 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-23
 */
@Repository
public interface OrderItemCommentImgDAO extends BaseMapper<OrderItemCommentImgDO> {

    /***
     * 查询图片列表
     * @param commentId
     * @return
     */
    List<OrderItemCommentImgDTO> selectDTOByCommentId(Long commentId);
}