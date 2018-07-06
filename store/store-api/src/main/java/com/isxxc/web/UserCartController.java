package com.isxxc.web;


import com.isxxc.domain.entity.UserCartDO;
import com.isxxc.service.feign.user.UserCartService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员购物车 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@RestController
@RequestMapping("/userCart")
public class UserCartController {

    @Resource
    private UserCartService userCartService;

    /***
     * 添加
     * @param userId
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserCartDO userCartDO, Integer userId) {
        if (userCartDO.getSkuId() == null) {
            return ResponseResult.paramNotNull("商品SKUID不能为空");
        }
        if (userCartDO.getNum() == null || userCartDO.getNum() <= 0) {
            return ResponseResult.paramNotNull("商品数量不能为空或小于1");
        }
        if (userCartDO.getSalePrice() == null || userCartDO.getSalePrice() <= 0) {
            return ResponseResult.paramNotNull("商品销售价不能为空或小于0");
        }
        if (userCartDO.getPriceType() == null) {
            return ResponseResult.paramNotNull("价格类型不能为空");
        }
        userCartDO.setUserId(userId);
        return userCartService.save(userCartDO);
    }

    /***
     * 查询购物车列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Integer userId) {
        return userCartService.list(userId);
    }

    /***
     * 更新购物车商品数量
     * @param userId
     * @param id
     * @param num
     * @return
     */
    @RequestMapping(value = "updateNum", method = RequestMethod.POST)
    public Result updateNum(Integer userId, Integer id, Integer num) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (num == null || num <= 0) {
            return ResponseResult.paramNotNull("数量不能小于1");
        }
        return userCartService.updateNum(userId, id, num);
    }

    /***
     * 删除会员购物车商品
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    public Result deleteById(Integer userId, Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return userCartService.deleteById(userId, id);
    }

    /***
     * 批量删除会员购物车
     * @param userId
     * @param ids 所有购物车ID以 "," 分隔
     * @return
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
    public Result deleteByIds(Integer userId, String ids) {
        if (StringUtils.isBlank(ids)) {
            return ResponseResult.paramNotNull("购物车IDS不能为空");
        }
        return userCartService.deleteByIds(userId, ids);
    }
}
