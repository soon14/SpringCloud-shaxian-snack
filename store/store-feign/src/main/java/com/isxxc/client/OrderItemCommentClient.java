package com.isxxc.client;


import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 订单商品评价 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@RequestMapping("/orderItemCommentClient")
public interface OrderItemCommentClient {

    /***
     * 添加评价
     * @param orderItemCommentDTO
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result add(OrderItemCommentDTO orderItemCommentDTO);

    /***
     * 查询商品SPUID评论列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 根据订单项ID查询评价
     * @param orderItemId
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    Result getInfoByOrderItemId(@RequestParam("orderItemId") Integer orderItemId);

    /***
     * 查询用户所有评价 by xdp
     * @param pager
     * @return
     */
    @RequestMapping(value = "getInfoByUserId", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result getInfoByUserId(Pager pager,@RequestParam("userId")Integer userId);
    /***
     * 查询商家所有评价
     * @param pager
     * @return
     */
    @RequestMapping(value = "getInfoByStoreId", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result getInfoByStoreId(Pager pager,@RequestParam("storeId")Integer storeId);

    /**
     * 商家回复
     * @param orderItemCommentDTO
     * @return
     */
    @RequestMapping(value = "replyComments",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result replyComments(OrderItemCommentDTO orderItemCommentDTO);
}
