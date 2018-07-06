package com.isxxc.client;


import com.isxxc.domain.entity.UserAddressDO;
import com.isxxc.domain.entity.UserCartDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import cc.likq.result.Result;

/**
 * <p>
 * 会员收货地址 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/userAddressClient")
public interface UserAddressClient {


    /***
     * 添加会员收货地址
     * @param userAddressDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(UserAddressDO userAddressDO);

    /***
     * 更新信息
     * @param userAddressDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(UserAddressDO userAddressDO);

    /***
     * 查询会员收货地址
     * @param userId
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    Result list(@RequestParam("userId") Integer userId);

    /***
     * 查询会员默认地址
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByDefault", method = RequestMethod.GET)
    Result getInfoByDefault(@RequestParam("userId") Integer userId);

    /***
     * 删除会员收货地址
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteByUserId", method = RequestMethod.POST)
    Result deleteByUserId(@RequestParam("userId") Integer userId, @RequestParam("id") Integer id);
}
