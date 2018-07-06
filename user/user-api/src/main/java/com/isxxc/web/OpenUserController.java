package com.isxxc.web;


import com.isxxc.constant.UserAgentEnum;
import com.isxxc.service.feign.OpenUserService;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 第三方登录信息 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-22
 */
@RestController
@RequestMapping("/openUser")
public class OpenUserController {

    @Resource
    private OpenUserService openUserService;

    /**
     * 小吃铺授权登录
     */
    @RequestMapping(value = "xiaoChiPuLogin", method = RequestMethod.GET)
    public Result xiaoChiPuLogin(String account, String password, String userAgent) {
        if (StringUtils.isBlank(account)) {
            return ResponseResult.paramNotNull("请输入帐号");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseResult.paramNotNull("请输入密码");
        }
        if (StringUtils.isBlank(userAgent)) {
            return ResponseResult.paramNotNull("终端类型不能为空");
        }
        if (!EnumUtils.isValidEnum(UserAgentEnum.class, userAgent)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "终端类型错误");
        }
        return openUserService.xiaoChiPuLogin(account, password, userAgent);
    }

    /***
     * 绑定手机号
     * @param openUserId
     * @param mobileNo
     * @param verifyCode
     * @param userAgent
     * @return
     */
    @RequestMapping(value = "xiaoChiPuBindMobile", method = RequestMethod.POST)
    public Result xiaoChiPuBindMobile(Integer openUserId, String mobileNo, String verifyCode, String userAgent) {
        if (openUserId == null) {
            return ResponseResult.paramNotNull("授权用户标识不能为空");
        }
        if (StringUtils.isBlank(verifyCode)) {
            return ResponseResult.paramNotNull("验证码不能为空");
        }
        if (StringUtils.isBlank(userAgent)) {
            return ResponseResult.paramNotNull("终端类型不能为空");
        }
        if (!EnumUtils.isValidEnum(UserAgentEnum.class, userAgent)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "终端类型错误");
        }
        return openUserService.xiaoChiPuBindMobile(openUserId, mobileNo, verifyCode, userAgent);
    }
}
