package com.isxxc.service.impl;

import com.isxxc.service.SmsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import javax.annotation.Resource;

import cc.likq.constant.RedisKeyConstant;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.AliSmsUtils;
import cc.likq.util.RandomUtils;

/**
 * 短信服务
 *
 * @author 泥水佬
 * @date 2018/1/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisClient redisClient;

    @Override
    public Result registerVerifycode(String mobileNo) {
        String registerVerifyCodeNumKey = RedisKeyConstant.getRegisterVerifyCodeNumKey(mobileNo);
        String registerVerifyCodeNumStr = redisClient.get(registerVerifyCodeNumKey);
        int registerVerifyCodeNum = StringUtils.isNotBlank(registerVerifyCodeNumStr) ? Integer.parseInt(registerVerifyCodeNumStr) : 0;
        if (registerVerifyCodeNum >= 5) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "获取验证码过于频繁,请15分钟后再试");
        }
        String code = RandomUtils.generateNumberString(4);
        Result result = AliSmsUtils.sendSms(AliSmsUtils.TemplateType.COMMON_TEMPLATE, mobileNo, new HashMap<String, String>(1) {{
            put("code", code);
        }});
        if (ResponseResult.success().getCode() != result.getCode()) {
            return result;
        }
        String registerVerifyCodeKey = RedisKeyConstant.getRegisterVerifyCodeKey(mobileNo);
        redisClient.set(registerVerifyCodeKey, code, 60);
        redisClient.set(registerVerifyCodeNumKey, String.valueOf(++registerVerifyCodeNum), 900);
        return ResponseResult.success();
    }

    @Override
    public Result loginVerifycode(String mobileNo) {
        String loginVerifyCodeNumKey = RedisKeyConstant.getLoginVerifyCodeNumKey(mobileNo);
        String loginVerifyCodeNumKeyStr = redisClient.get(loginVerifyCodeNumKey);
        int registerVerifyCodeNum = StringUtils.isNotBlank(loginVerifyCodeNumKeyStr) ? Integer.parseInt(loginVerifyCodeNumKeyStr) : 0;
        if (registerVerifyCodeNum >= 5) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "获取验证码过于频繁,请15分钟后再试");
        }
        String code = RandomUtils.generateNumberString(4);
        Result result = AliSmsUtils.sendSms(AliSmsUtils.TemplateType.COMMON_TEMPLATE, mobileNo, new HashMap<String, String>(1) {{
            put("code", code);
        }});
        if (ResponseResult.success().getCode() != result.getCode()) {
            return result;
        }
        String loginVerifyCodeKey = RedisKeyConstant.getLoginVerifyCodeKey(mobileNo);
        redisClient.set(loginVerifyCodeKey, code, 60);
        redisClient.set(loginVerifyCodeNumKey, String.valueOf(++registerVerifyCodeNum), 900);
        return ResponseResult.success();
    }

    @Override
    public Result resetPassword(String mobileNo) {
        String resetPasswordVerifyCodeNumKey = RedisKeyConstant.getResetPasswordVerifyCodeNumKey(mobileNo);
        String resetPasswordVerifyCodeNumKeyStr = redisClient.get(resetPasswordVerifyCodeNumKey);
        int resetPasswordVerifyCodeNum = StringUtils.isNotBlank(resetPasswordVerifyCodeNumKeyStr) ? Integer.parseInt(resetPasswordVerifyCodeNumKeyStr) : 0;
        if (resetPasswordVerifyCodeNum >= 5) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "获取验证码过于频繁,请15分钟后再试");
        }
        String code = RandomUtils.generateNumberString(4);
        Result result = AliSmsUtils.sendSms(AliSmsUtils.TemplateType.COMMON_TEMPLATE, mobileNo, new HashMap<String, String>(1) {{
            put("code", code);
        }});
        if (ResponseResult.success().getCode() != result.getCode()) {
            return result;
        }
        String resetPasswordVerifyCodeKey = RedisKeyConstant.getResetPasswordVerifyCodeKey(mobileNo);
        redisClient.set(resetPasswordVerifyCodeKey, code, 60);
        redisClient.set(resetPasswordVerifyCodeNumKey, String.valueOf(++resetPasswordVerifyCodeNum), 900);
        return ResponseResult.success();
    }

    @Override
    public Result bindMobile(String mobileNo) {
        String userBindVerifycodeNumKey = RedisKeyConstant.getUserBindVerifycodeNumKey(mobileNo);
        String userBindVerifycodeNumKeyStr = redisClient.get(userBindVerifycodeNumKey);
        int userBindVerifycodeNum = StringUtils.isNotBlank(userBindVerifycodeNumKeyStr) ? Integer.parseInt(userBindVerifycodeNumKeyStr) : 0;
        if (userBindVerifycodeNum >= 5) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "获取验证码过于频繁,请15分钟后再试");
        }
        String code = RandomUtils.generateNumberString(4);
        Result result = AliSmsUtils.sendSms(AliSmsUtils.TemplateType.COMMON_TEMPLATE, mobileNo, new HashMap<String, String>(1) {{
            put("code", code);
        }});
        if (ResponseResult.success().getCode() != result.getCode()) {
            return result;
        }
        String userBindVerifycodeKey = RedisKeyConstant.getUserBindVerifycodeKey(mobileNo);
        redisClient.set(userBindVerifycodeKey, code, 60);
        redisClient.set(userBindVerifycodeNumKey, String.valueOf(++userBindVerifycodeNum), 900);
        return ResponseResult.success();
    }
}
