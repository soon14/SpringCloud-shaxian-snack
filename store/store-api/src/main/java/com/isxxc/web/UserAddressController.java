package com.isxxc.web;


import com.isxxc.domain.entity.UserAddressDO;
import com.isxxc.service.feign.user.UserAddressService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员收货地址 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-01-29
 */
@RestController
@RequestMapping("/userAddress")
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    /***
     * 添加会员收货地址
     * @param userAddressDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserAddressDO userAddressDO) {
        if (userAddressDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userAddressDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userAddressDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userAddressDO.getName())) {
            return ResponseResult.paramNotNull("姓名不能为空");
        }
        if (StringUtils.isBlank(userAddressDO.getMobileNo())) {
            return ResponseResult.paramNotNull("号码不能为空");
        }
        if (StringUtils.isBlank(userAddressDO.getAddr())) {
            return ResponseResult.paramNotNull("详细地址不能为空");
        }
        return userAddressService.save(userAddressDO);
    }

    /***
     * 更新信息
     * @param userAddressDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(UserAddressDO userAddressDO) {
        if (userAddressDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (userAddressDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userAddressDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userAddressDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userAddressDO.getName())) {
            return ResponseResult.paramNotNull("姓名不能为空");
        }
        if (StringUtils.isBlank(userAddressDO.getMobileNo())) {
            return ResponseResult.paramNotNull("号码不能为空");
        }
        if (StringUtils.isBlank(userAddressDO.getAddr())) {
            return ResponseResult.paramNotNull("详细地址不能为空");
        }
        return userAddressService.updateInfo(userAddressDO);
    }

    /***
     * 查询会员收货地址
     * @param userId
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Integer userId) {
        return userAddressService.list(userId);
    }

    /***
     * 查询会员默认地址
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByDefault", method = RequestMethod.GET)
    public Result getInfoByDefault(Integer userId) {
        return userAddressService.getInfoByDefault(userId);
    }

    /***
     * 删除会员收货地址
     * @return
     */
    @RequestMapping(value = "deleteByUserId", method = RequestMethod.POST)
    public Result deleteById(Integer userId, Integer id) {
        return userAddressService.deleteByUserId(userId, id);
    }
}
