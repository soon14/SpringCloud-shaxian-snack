package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserAddressDO;

import cc.likq.result.Result;

/**
 * <p>
 * 会员收货地址 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
public interface UserAddressService extends IService<UserAddressDO> {

    /***
     * 添加会员收货地址
     * @param userAddressDO
     * @return
     */
    Result save(UserAddressDO userAddressDO);

    /***
     * 更新信息
     * @param userAddressDO
     * @return
     */
    Result updateInfo(UserAddressDO userAddressDO);

    /***
     * 查询会员收货地址列表
     * @param userId
     * @return
     */
    Result list(Integer userId);

    /***
     * 查询会员默认收货地址
     * @param userId
     * @return
     */
    Result getInfoByDefault(Integer userId);

    /***
     * 删除会员收货地址
     * @param userId
     * @return
     */
    Result deleteByUserId(Integer userId, Integer id);
}
