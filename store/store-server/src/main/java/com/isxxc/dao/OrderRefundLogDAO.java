package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.OrderRefundDO;
import com.isxxc.domain.entity.OrderRefundLogDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单信息 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@Repository
public interface OrderRefundLogDAO extends BaseMapper<OrderRefundLogDO> {


 }