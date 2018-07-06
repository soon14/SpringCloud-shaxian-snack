package com.isxxc.web;


import com.isxxc.constant.UserAgentEnum;
import com.isxxc.domain.dto.UserAccountDTO;
import com.isxxc.service.feign.UserAccountService;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.ValidatorUtil;

/**
 * <p> 会员信息 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
@RestController
@RequestMapping("/userAccount")
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    /***
     * 注册
     * @param userAccountDTO 会员信息
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserAccountDTO userAccountDTO) {
        if (StringUtils.isBlank(userAccountDTO.getMobileNo())) {
            return ResponseResult.paramNotNull("手机号不能为空");
        }
        if (StringUtils.isBlank(userAccountDTO.getPassword())) {
            return ResponseResult.paramNotNull("密码不能为空");
        }
        if (StringUtils.isBlank(userAccountDTO.getVerifyCode())) {
            return ResponseResult.paramNotNull("验证码不能为空");
        }
        return userAccountService.save(userAccountDTO);
    }

    /***
     * 会员登录
     * @param account
     * @param password
     * @param userAgent
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Result login(String account, String password, String userAgent) {
        if (StringUtils.isBlank(account)) {
            return ResponseResult.paramNotNull("帐号不能为空");
        }
        if (!ValidatorUtil.isMobile(account)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "帐号格式不正确");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseResult.paramNotNull("密码不能为空");
        }
        if (StringUtils.isBlank(userAgent)) {
            return ResponseResult.paramNotNull("终端类型不能为空");
        }
        if (!EnumUtils.isValidEnum(UserAgentEnum.class, userAgent)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "终端类型错误");
        }
        return userAccountService.login(account, password, userAgent);
    }

    /***
     * 会员登录,验证码登录
     */
    @RequestMapping(value = "loginByVerifycode", method = RequestMethod.GET)
    public Result loginByVerifycode(String account, String verifyCode, String userAgent) {
        if (StringUtils.isBlank(account)) {
            return ResponseResult.paramNotNull("帐号不能为空");
        }
        if (!ValidatorUtil.isMobile(account)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "帐号格式不正确");
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
        return userAccountService.loginByVerifycode(account, verifyCode, userAgent);
    }

    /***
     * 更新密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public Result updatePassword(Integer userId, String password, String newPassword) {
        if (userId == null) {
            return ResponseResult.paramNotNull("会员ID不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseResult.paramNotNull("密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            return ResponseResult.paramNotNull("新密码不能为空");
        }
        return userAccountService.updatePassword(userId, password, newPassword);
    }

    /***
     * 更新用户信息
     * @param userAccountDTO
     * @return
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public Result updateUserInfo(UserAccountDTO userAccountDTO, Integer userId) {
        userAccountDTO.setId(userId);
        if (StringUtils.isBlank(userAccountDTO.getNickname())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "昵称不能为空");
        }
        return userAccountService.updateUserInfo(userAccountDTO);
    }

    /***
     *注销登录
     * @param userId
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Result logout(Integer userId, String userAgent) {
        if (userId == null) {
            return ResponseResult.paramNotNull("UserId不能为空");
        }
        return userAccountService.logout(userId, userAgent);
    }

    /**
     * 保存头像
     */
    @RequestMapping(value = "saveAvater", method = RequestMethod.POST)
    public Result saveAvater(Integer userId, String avaterName) {
        if (StringUtils.isBlank(avaterName)) {
            return ResponseResult.paramNotNull("头像不能为空");
        }
        return userAccountService.saveAvater(userId, avaterName);
    }

    /***
     * 重置密码
     * @param mobileNo
     * @param verifyCode
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "resetPasswordByMobileNo", method = RequestMethod.POST)
    public Result resetPasswordByMobileNo(String mobileNo, String verifyCode, String newPassword) {
        if (StringUtils.isBlank(mobileNo)) {
            return ResponseResult.paramNotNull("手机不能为空");
        }
        if (!ValidatorUtil.isMobile(mobileNo)) {
            return ResponseResult.paramNotNull("手机号格式不正确");
        }
        if (StringUtils.isBlank(verifyCode)) {
            return ResponseResult.paramNotNull("验证码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            return ResponseResult.paramNotNull("新密码不能为空");
        }
        return userAccountService.resetPasswordByMobileNo(mobileNo, verifyCode, newPassword);
    }

    /***
     * 根据用户详细信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByUserId", method = RequestMethod.GET)
    public Result getInfoByUserId(Integer userId) {
        return userAccountService.getInfoById(userId);
    }
}
