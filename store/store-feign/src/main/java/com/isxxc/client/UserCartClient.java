package com.isxxc.client;


import com.isxxc.domain.entity.UserCartDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 会员购物车 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/userCartClient")
public interface UserCartClient {

    /***
     * 查询购物车信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    Result list(@RequestParam("userId") Integer userId);

    /***
     * 更新购物车商品数量
     * @param userId
     * @param id
     * @param num
     * @return
     */
    @RequestMapping(value = "updateNum", method = RequestMethod.POST)
    Result updateNum(@RequestParam("userId") Integer userId, @RequestParam("id") Integer id, @RequestParam("num") Integer num);

    /***
     * 删除购物车商品
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    Result deleteById(@RequestParam("userId") Integer userId, @RequestParam("id") Integer id);

    /***
     * 批量删除订单
     * @param userId
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
    Result deleteByIds(@RequestParam("userId") Integer userId, @RequestParam("ids") String ids);

    /***
     * 添加商品到购物车
     * @param userCartDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(UserCartDO userCartDO);
}
