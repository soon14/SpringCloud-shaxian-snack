package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ConsignmentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.SubmitOrderDTO;
import com.isxxc.domain.entity.OrderInfoDO;

import java.util.List;

import cc.likq.result.Result;

/**
 * <p>
 * 订单信息 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
public interface OrderInfoService extends IService<OrderInfoDO> {

    /***
     * 订单提交
     * @param submitOrderDTO
     * @return
     */
    Result submitOrderDTO(SubmitOrderDTO submitOrderDTO);

    /***
     * 计算运费
     * @param storeId
     * @param spuIds
     * @return
     */
    Result getFreightPriceByOrder(Integer storeId, String spuIds);

    /***
     * 根据订单列表查询详情
     * @param orderNoList
     * @return
     */
    List<OrderInfoDO> selectByOrderNoList(List<String> orderNoList);

    /***
     * 根据会员查询订单
     * @param pager
     * @return
     */
    Result listPageByUser(Pager pager);

    /***
     * 根据商店查询订单
     * @param pager
     * @return
     */
    Result listPageByStore(Pager pager);

    /***
     * 查询会员订单详情
     * @param userId
     * @param id
     * @return
     */
    Result getInfoByIdAndUser(Integer userId, Integer id);

    /***
     * 查询商店订单详情
     * @param storeId
     * @param id
     * @return
     */
    Result getInfoByIdAndStore(Integer storeId, Integer id);

    /***
     * 发货
     * @param consignmentDTO
     * @return
     */
    Result consignment(ConsignmentDTO consignmentDTO);

    /****
     * 查询订单支付状态
     * @param userId
     * @param orderNo
     * @return
     */
    Result getOrderPayState(Integer userId, String orderNo);

    /***
     * 查询合并订单支付状态
     * @param payMergerNo
     * @return
     */
    Result getMergerPayState(String payMergerNo);

    /***
     * 确认收货
     * @param userId
     * @param orderNo
     * @return
     */
    Result confirmDeliver(Integer userId, String orderNo);

    /***
     * 查询订单列表
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 根据ID查询订单详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);
}
