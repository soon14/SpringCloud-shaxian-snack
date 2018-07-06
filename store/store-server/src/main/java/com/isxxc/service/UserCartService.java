package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserCartDO;

import java.util.List;

import cc.likq.result.Result;

/**
 * <p>
 * 会员购物车 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
public interface UserCartService extends IService<UserCartDO> {

    /***
     * 查询购物车列表
     * @param userId
     * @return
     */
    Result list(Integer userId);

    /***
     * 更新商品数量
     * @param userId
     * @param id
     * @param num
     * @return
     */
    Result updateNum(Integer userId, Integer id, Integer num);

    /***
     * 删除会员购物车商品
     * @param userId
     * @param id
     * @return
     */
    Result deleteByIdAndUserId(Integer userId, Integer id);

    /***
     * 批量删除订单
     * @param userId
     * @param ids
     * @return
     */
    Result deleteByIds(Integer userId, String ids);

    /***
     *添加商品到购物车
     * @param userCartDO
     * @return
     */
    Result save(UserCartDO userCartDO);
}
