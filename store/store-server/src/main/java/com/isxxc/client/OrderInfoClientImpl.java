package com.isxxc.client;


import com.isxxc.domain.dto.ConsignmentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.SubmitOrderDTO;
import com.isxxc.service.OrderInfoService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 订单信息 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class OrderInfoClientImpl implements OrderInfoClient {

    @Resource
    private OrderInfoService orderInfoService;

    @Override
    public Result submit(@RequestBody SubmitOrderDTO submitOrderDTO) {
        return orderInfoService.submitOrderDTO(submitOrderDTO);
    }

    @Override
    public Result getFreightPriceByOrder(Integer storeId, String spuIds) {
        return orderInfoService.getFreightPriceByOrder(storeId, spuIds);
    }

    @Override
    public Result listPageByUser(@RequestBody Pager pager) {
        return orderInfoService.listPageByUser(pager);
    }

    @Override
    public Result listPageByStore(@RequestBody Pager pager) {
        return orderInfoService.listPageByStore(pager);
    }

    @Override
    public Result getInfoByIdAndUser(Integer userId, Integer id) {
        return orderInfoService.getInfoByIdAndUser(userId, id);
    }

    @Override
    public Result getInfoByIdAndStore(Integer storeId, Integer id) {
        return orderInfoService.getInfoByIdAndStore(storeId, id);
    }

    @Override
    public Result consignment(@RequestBody ConsignmentDTO consignmentDTO) {
        return orderInfoService.consignment(consignmentDTO);
    }

    @Override
    public Result getOrderPayState(Integer userId, String orderNo) {
        return orderInfoService.getOrderPayState(userId, orderNo);
    }

    @Override
    public Result getMergerPayState(String payMergerNo) {
        return orderInfoService.getMergerPayState(payMergerNo);
    }

    @Override
    public Result confirmDeliver(Integer userId, String orderNo) {
        return orderInfoService.confirmDeliver(userId, orderNo);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return orderInfoService.listPage(pager);
    }

    @Override
    public Result getInfoById(Integer id) {
        return orderInfoService.getInfoById(id);
    }

}
