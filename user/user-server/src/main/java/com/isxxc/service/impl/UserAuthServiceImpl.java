package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.domain.entity.AuthAccountDO;
import com.isxxc.domain.entity.AuthLoginLogDO;
import com.isxxc.domain.entity.UserAccountDO;
import com.isxxc.service.AuthAccountService;
import com.isxxc.service.AuthLoginLogService;
import com.isxxc.service.UserAccountService;
import com.isxxc.service.UserAuthService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * 会员授权服务
 *
 * @author 泥水佬
 * @date 2018/1/13
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Resource
    private AuthAccountService authAccountService;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private AuthLoginLogService authLoginLogService;

    @Override
    public Result authLogin(String authAccount, String authPassword, String account, String password) {
        //校验授权用户信息
        EntityWrapper<AuthAccountDO> authEntityWrapper = new EntityWrapper<>();
        authEntityWrapper.eq("account", authAccount);
        AuthAccountDO authAccountDO = authAccountService.selectOne(authEntityWrapper);
        authPassword = DigestUtils.sha1Hex(authPassword).toUpperCase();

        //授权登录日志
        AuthLoginLogDO authLoginLogDO = new AuthLoginLogDO();
        authLoginLogDO.setAuthAccount(authAccount);
        authLoginLogDO.setUserAccount(account);

        if (authAccountDO == null || !authPassword.equals(authAccountDO.getPassword().toUpperCase())) {
            authLoginLogDO.setDescription("授权用户帐号或密码错误");
            authLoginLogDO.setIsSuccess(CommonStateEnum.IsSuccess.FAIL.code);
            authLoginLogService.insert(authLoginLogDO);
            return ResponseResult.failResult(ResultCodeEnum.UNAUTHORIZED, "授权用户帐号或密码错误");
        }

        //校验会员信息
        EntityWrapper<UserAccountDO> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("mobile_no", account);
        UserAccountDO userAccountDO = userAccountService.selectOne(userEntityWrapper);
        password = DigestUtils.sha1Hex(password).toUpperCase();
        if (userAccountDO == null || !password.equals(userAccountDO.getPassword().toUpperCase())) {
            authLoginLogDO.setDescription("会员帐号或密码错误");
            authLoginLogDO.setIsSuccess(CommonStateEnum.IsSuccess.FAIL.code);
            authLoginLogService.insert(authLoginLogDO);
            return ResponseResult.failResult(ResultCodeEnum.UNAUTHORIZED, "会员帐号或密码错误");
        }

        authLoginLogDO.setDescription("授权登录成功");
        authLoginLogDO.setIsSuccess(CommonStateEnum.IsSuccess.SUCCEED.code);
        authLoginLogService.insert(authLoginLogDO);

        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("userCode", userAccountDO.getCode());
        resultMap.put("nickname", userAccountDO.getNickname());
        resultMap.put("avaterPath", StringUtils.isNotBlank(userAccountDO.getAvaterPath()) ? CommonFolderConstant.getAvaterWebPath(userAccountDO.getAvaterPath()) : "");
        return ResponseResult.success(resultMap);
    }
}
