package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.OrderStatisticsDTO;
import com.isxxc.domain.dto.Pager;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单统计 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@Repository
public interface OrderStatisticsDAO extends BaseMapper<OrderStatisticsDTO> {

    /***
     * 查询订单统计列表
     * @param pager
     * @param paramMap
     * @return
     */
    List<OrderStatisticsDTO> listPage(Pager pager, @Param("params") Map<String, Object> paramMap);
}