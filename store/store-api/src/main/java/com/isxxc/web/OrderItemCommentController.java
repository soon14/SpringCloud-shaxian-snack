package com.isxxc.web;


import com.alibaba.fastjson.JSON;
import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.OrderItemCommentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 订单商品评价 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@RestController
@RequestMapping("/orderItemComment")
public class OrderItemCommentController {

    Logger logger = LogManager.getLogger(OrderItemCommentController.class);
    @Resource
    private OrderItemCommentService orderItemCommentService;

    /***
     * 添加评价
     * @param orderItemCommentDTO
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestBody OrderItemCommentDTO orderItemCommentDTO) {
        logger.info("OrderItemCommentController add:"+JSON.toJSONString(orderItemCommentDTO));
        if (orderItemCommentDTO.getOrderItemId() == null) {
            return ResponseResult.paramNotNull("订单详情项ID不能为空");
        }
        if (orderItemCommentDTO.getDescribeScore() == null ||
                (orderItemCommentDTO.getDescribeScore() <= 0 && orderItemCommentDTO.getDescribeScore() >= 6)) {
            return ResponseResult.paramNotNull("请选择描述评分");
        }
        if (orderItemCommentDTO.getServiceScore() == null ||
                (orderItemCommentDTO.getServiceScore() <= 0 && orderItemCommentDTO.getServiceScore() >= 6)) {
            return ResponseResult.paramNotNull("请选择服务评分");
        }
        return orderItemCommentService.add(orderItemCommentDTO);
    }

    /***
     * 查询商品SPUID评论列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer spuId) {
        logger.info("查询评价列表："+pager+"/spuId:"+spuId);
        if (spuId == null) {
            return ResponseResult.paramNotNull("商品SPUID不能为空");
        }
        pager.putParam("spuId", spuId);
        return orderItemCommentService.listPage(pager);
    }

    /***
     * 根据订单项ID查询评价
     * @param orderItemId
     * @return
     */
    @RequestMapping(value = "getInfoByOrderItemId", method = RequestMethod.GET)
    public Result getInfoByOrderItemId(Integer orderItemId) {
        if (orderItemId == null) {
            return ResponseResult.paramNotNull("订单项ID不能为空");
        }
        return orderItemCommentService.getInfoByOrderItemId(orderItemId);
    }
    /***
     * 查询用户所有评价
     * @param pager
     * @return
     */
    @RequestMapping(value = "getCommentByUserId", method = RequestMethod.GET)
    public Result getCommentByUserId(Pager pager, Integer userId) {
        if (userId == null) {
            return ResponseResult.paramNotNull("用户ID不能为空");
        }
        pager.putParam("userId", userId);
        return orderItemCommentService.getInfoByUserId(pager,userId);
    }
}
