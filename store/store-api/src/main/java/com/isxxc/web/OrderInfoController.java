package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.SubmitOrderDTO;
import com.isxxc.domain.dto.SubmitOrderSkuDTO;
import com.isxxc.domain.dto.SubmitStoreOrderDTO;
import com.isxxc.service.feign.store.OrderInfoService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 订单提交
     * @param submitOrderDTO
     * @return
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Result submit(@RequestBody SubmitOrderDTO submitOrderDTO, Integer userId) {
        submitOrderDTO.setUserId(userId);
        if (StringUtils.isBlank(submitOrderDTO.getConsigneeName())) {
            return ResponseResult.paramNotNull("收件人不能为空");
        }
        if (StringUtils.isBlank(submitOrderDTO.getConsigneeMobile())) {
            return ResponseResult.paramNotNull("收件人号码不能为空");
        }
        if (StringUtils.isBlank(submitOrderDTO.getConsigneeAddress())) {
            return ResponseResult.paramNotNull("收件人地址不能为空");
        }
        if (submitOrderDTO.getActualPrice() == null || submitOrderDTO.getActualPrice() < 0) {
            return ResponseResult.paramNotNull("订单应付金额不为空或小于0");
        }
        if (CollectionUtils.isEmpty(submitOrderDTO.getStoreOrderList())) {
            return ResponseResult.paramNotNull("商品不能为空信息不能为空");
        }
        //总订单应付金额
        Long actualPrice = 0L;
        for (SubmitStoreOrderDTO storeOrder : submitOrderDTO.getStoreOrderList()) {
            if (storeOrder.getTotalPrice() == null || storeOrder.getTotalPrice() < 0) {
                return ResponseResult.paramNotNull("商品总价不为空或小于0");
            }
            if (storeOrder.getFreightPrice() == null || storeOrder.getFreightPrice() < 0) {
                return ResponseResult.paramNotNull("订单运费不为空或小于0");
            }
            if (CollectionUtils.isEmpty(storeOrder.getSkuList())) {
                return ResponseResult.paramNotNull("商品SKU信息不能为空");
            }
            Long productTotalPrice = 0L;
            for (SubmitOrderSkuDTO orderSkuDTO : storeOrder.getSkuList()) {
                if (orderSkuDTO.getNum() == null || orderSkuDTO.getNum() <= 0) {
                    return ResponseResult.paramNotNull("购买商品数量不能为空或小于0");
                }
                if (orderSkuDTO.getSkuId() == null) {
                    return ResponseResult.paramNotNull("商品SKUID不能为空");
                }
                if (orderSkuDTO.getSalePrice() == null || orderSkuDTO.getSalePrice() < 0) {
                    return ResponseResult.paramNotNull("商品销售价不能为空或小于0");
                }
                if (orderSkuDTO.getTotalPrice() == null || orderSkuDTO.getTotalPrice() < 0) {
                    return ResponseResult.paramNotNull("商品总价不能为空或小于0");
                }
                if (!orderSkuDTO.getTotalPrice().equals(orderSkuDTO.getNum() * orderSkuDTO.getSalePrice())) {
                    return ResponseResult.paramNotNull("单个商品总价计算错误");
                }
                productTotalPrice += orderSkuDTO.getTotalPrice();
            }
            if (!productTotalPrice.equals(storeOrder.getTotalPrice())) {
                return ResponseResult.paramNotNull("商品总价计算错误");
            }
            if (!storeOrder.getActualPrice().equals(storeOrder.getTotalPrice() + storeOrder.getFreightPrice())) {
                return ResponseResult.paramNotNull("商店订单应付金额计算错误");
            }
            actualPrice += storeOrder.getActualPrice();
        }
        if (!submitOrderDTO.getActualPrice().equals(actualPrice)) {
            return ResponseResult.paramNotNull("总订单应付金额计算错误");
        }
        return orderInfoService.submit(submitOrderDTO);
    }

    /***
     * 获取多商品运费
     * @param storeId
     * @param spuIds
     * @return
     */
    @RequestMapping(value = "getFreightPriceByOrder", method = RequestMethod.GET)
    public Result getFreightPriceByOrder(Integer storeId, String spuIds) {
        if (storeId == null) {
            return ResponseResult.paramNotNull("商店ID不能为空");
        }
        if (StringUtils.isBlank(spuIds)) {
            return ResponseResult.paramNotNull("商品SPUID不能为空");
        }
        return orderInfoService.getFreightPriceByOrder(storeId, spuIds);
    }

    /***
     * 根据会员查询订单
     * @param pager
     * @param userId
     * @param state 0:待付款、1:待发货、2:待收货、3:待评价
     * @return
     */
    @RequestMapping(value = "listPageByUser", method = RequestMethod.GET)
    public Result listPageByUser(Pager pager, Integer userId, Integer state) {
        pager.putParam("userId", userId);
        if (state != null) {
            pager.putParam("state", state);
        }
        return orderInfoService.listPageByUser(pager);
    }

    /***
     * 查询会员订单详情
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndUser", method = RequestMethod.GET)
    public Result getInfoByIdAndUser(Integer userId, Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("订单ID不能为空");
        }
        return orderInfoService.getInfoByIdAndUser(userId, id);
    }

    /***
     * 查询订单支付状态
     * @return
     */
    @RequestMapping(value = "getOrderPayState", method = RequestMethod.GET)
    public Result getOrderPayState(Integer userId, String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        return orderInfoService.getOrderPayState(userId, orderNo);
    }

    /***
     * 查询订单支付状态
     * @return
     */
    @RequestMapping(value = "getMergerPayState", method = RequestMethod.GET)
    public Result getMergerPayState(String payMergerNo) {
        if (StringUtils.isBlank(payMergerNo)) {
            return ResponseResult.paramNotNull("合并支付单号不能为空");
        }
        return orderInfoService.getMergerPayState(payMergerNo);
    }

    /***
     * 确认收货
     * @param userId
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "confirmDeliver", method = RequestMethod.POST)
    public Result confirmDeliver(Integer userId, String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        return orderInfoService.confirmDeliver(userId, orderNo);
    }
}