package com.isxxc.client;


import com.isxxc.domain.dto.ConsignmentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.SubmitOrderDTO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 订单信息 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/orderInfoClient")
public interface OrderInfoClient {

    /***
     * 订单提交
     * @param submitOrderDTO
     * @return
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result submit(SubmitOrderDTO submitOrderDTO);

    /***
     * 计算运费
     * @param storeId
     * @param spuIds
     * @return
     */
    @RequestMapping(value = "getFreightPriceByOrder", method = RequestMethod.GET)
    Result getFreightPriceByOrder(@RequestParam("storeId") Integer storeId, @RequestParam("spuIds") String spuIds);

    /***
     * 根据会员查询订单
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPageByUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPageByUser(Pager pager);

    /***
     * 查询商店订单
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPageByStore", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPageByStore(Pager pager);

    /***
     * 查询会员订单详情
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndUser", method = RequestMethod.GET)
    Result getInfoByIdAndUser(@RequestParam("userId") Integer userId, @RequestParam("id") Integer id);

    /***
     * 查询商店订单详情
     * @param storeId
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndStore", method = RequestMethod.GET)
    Result getInfoByIdAndStore(@RequestParam("storeId") Integer storeId, @RequestParam("id") Integer id);

    /***
     * 发货
     * @param consignmentDTO
     * @return
     */
    @RequestMapping(value = "consignment", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result consignment(ConsignmentDTO consignmentDTO);

    /***
     * 查询订单状态
     * @param userId
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "getOrderPayState", method = RequestMethod.GET)
    Result getOrderPayState(@RequestParam("userId") Integer userId, @RequestParam("orderNo") String orderNo);

    /***
     * 查询合并支付订单状态
     * @param payMergerNo
     * @return
     */
    @RequestMapping(value = "getMergerPayState", method = RequestMethod.GET)
    Result getMergerPayState(@RequestParam("payMergerNo") String payMergerNo);

    /***
     * 确认收货
     * @param userId
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "confirmDeliver", method = RequestMethod.POST)
    Result confirmDeliver(@RequestParam("userId") Integer userId, @RequestParam("orderNo") String orderNo);

    /***
     * 查询订单列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 根据ID查询订单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    Result getInfoById(@RequestParam("id") Integer id);
}
