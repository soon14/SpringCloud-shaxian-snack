package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.OrderInfoDTO;
import com.isxxc.domain.entity.OrderInfoDO;

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
public interface OrderInfoDAO extends BaseMapper<OrderInfoDO> {

    /***
     * 查询订单列表
     * @param orderNoList
     * @return
     */
    List<OrderInfoDO> selectByOrderNoList(@Param("orderNoList") List<String> orderNoList);

    /***
     * 查询订单列表
     * @param page
     * @param paramMap
     * @return
     */
    List<OrderInfoDTO> selectListPage(Page page, @Param("params") Map<String, Object> paramMap);

    /***
     * 查询订单详情
     * @param params
     * @return
     */
    OrderInfoDTO selectDTOById(@Param("params") Map<String, Object> params);

    /***
     * 根据商店ID及订单号查询订单
     * @param orderNo
     * @param storeId
     * @return
     */
    OrderInfoDO selectByOrderNoAndStoreId(@Param("orderNo") String orderNo, @Param("storeId") Integer storeId);

    /***
     * 根据支付单号，查询同一笔支付订单
     * @param paymentNo
     * @return
     */
    List<OrderInfoDO> selectListBypaymentNo(@Param("paymentNo") String paymentNo);
}