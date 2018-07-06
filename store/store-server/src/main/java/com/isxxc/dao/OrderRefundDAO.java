package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.OrderInfoDTO;
import com.isxxc.domain.dto.OrderRefundDTO;
import com.isxxc.domain.entity.OrderInfoDO;
import com.isxxc.domain.entity.OrderRefundDO;
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
public interface OrderRefundDAO extends BaseMapper<OrderRefundDO> {

    /***
     * 客户查询退款列表信息
     * @param page
     * @param paramMap
     * @return
     */
    List<OrderRefundDO> comsumerRefundListPage(Page page, @Param("params") Map<String, Object> paramMap);

    /***
     * 商家查询订单列表退款信息
     * @param page
     * @param paramMap
     * @return
     */
    List<OrderRefundDO> storeRefundListPage(Page page, @Param("params") Map<String, Object> paramMap);


    /***
     * 后台列表退款信息
     * @param page
     * @param paramMap
     * @return
     */
    List<OrderRefundDO> listRefundPage(Page page, @Param("params") Map<String, Object> paramMap);


    /***
     * 根据退款订单号查询退款信息
     * @param refundNo
     * @return
     */
    OrderRefundDO  selectRefundDOByRefundNo(String refundNo);

}