package com.isxxc.web;


import com.isxxc.domain.dto.ConsignmentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.OrderInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 订单信息 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    /***
     * 查询商店订单
     * @param pager
     * @param storeId
     * @param state 0:待付款、1:待发货、2:待收货、3:待评价、4:交易成功、5:交易关闭
     * @return
     */
    @RequestMapping(value = "listPageByStore")
    public Result listPageByStore(Pager pager, Integer storeId, Integer state) {
        pager.putParam("storeId", storeId);
        if (state != null) {
            pager.putParam("state", state);
        }
        return orderInfoService.listPageByStore(pager);
    }

    /***
     * 查询会员订单详情
     * @param storeId
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndStore", method = RequestMethod.GET)
    public Result getInfoByIdAndStore(Integer storeId, Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("订单ID不能为空");
        }
        return orderInfoService.getInfoByIdAndStore(storeId, id);
    }

    /***
     * 发货
     * @param consignmentDTO
     * @return
     */
    @RequestMapping(value = "consignment", method = RequestMethod.POST)
    public Result consignment(ConsignmentDTO consignmentDTO) {
        if (StringUtils.isBlank(consignmentDTO.getOrderNo())) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        if (StringUtils.isBlank(consignmentDTO.getExpressName())) {
            return ResponseResult.paramNotNull("请选择快递公司");
        }
        if (StringUtils.isBlank(consignmentDTO.getExpressCode())) {
            return ResponseResult.paramNotNull("快递编码不能为空");
        }
        if (StringUtils.isBlank(consignmentDTO.getExpressNo())) {
            return ResponseResult.paramNotNull("快递单号不能为空");
        }
        return orderInfoService.consignment(consignmentDTO);
    }
}
