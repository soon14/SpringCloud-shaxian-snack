package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.OrderItemDTO;
import com.isxxc.domain.entity.OrderItemDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单子项 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@Repository
public interface OrderItemDAO extends BaseMapper<OrderItemDO> {

    /***
     * 根据订单ID查询列表
     * @param orderId
     * @return
     */
    List<OrderItemDTO> selectDTOByOrderId(Integer orderId);

    /***
     * 更新评论状态
     * @param orderId
     * @param commentState
     * @return
     */
    int updateCommentByOrderId(@Param("orderId") Integer orderId, @Param("commentState") int commentState);
}