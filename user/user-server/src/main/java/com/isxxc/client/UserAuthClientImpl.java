package com.isxxc.client;

import com.alibaba.fastjson.JSON;
import com.isxxc.constant.RedisKeyConstant;
import com.isxxc.service.UserAuthService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import com.isxxc.domain.dto.UserInfoDTO;
import redis.clients.jedis.Jedis;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RestController
public class UserAuthClientImpl implements UserAuthClient {

    @Resource
    private UserAuthService userAuthService;

    @Resource
    private RedisClient redisClient;

    @Override
    public Result checkToken(String token, String userCode, String userAgent) {
        String userInfoKey = RedisKeyConstant.getUserInfoKey(userCode);
        String userInfoJson = redisClient.get(userInfoKey);
        if (StringUtils.isBlank(userInfoJson)) {
            return ResponseResult.failResult(ResultCodeEnum.UNAUTHORIZED, "当前登录已经失效,请重新登录");
        }
        UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJson, UserInfoDTO.class);
        String tokenKey = RedisKeyConstant.getLoginTokenKey(userInfoDTO.getId(), userAgent);
        String tokenDB = redisClient.get(tokenKey);
        if (StringUtils.isBlank(tokenDB) || !token.trim().equals(tokenDB)) {
            return ResponseResult.failResult(ResultCodeEnum.UNAUTHORIZED, "当前登录已经失效,请重新登录");
        }
        Jedis jedis = redisClient.getResource();
        //重置当前Token有效期为7天
        jedis.expire(tokenKey, 604800);
        redisClient.returnBrokenResource(jedis);
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    @Override
    public Result authLogin(String authAccount, String authPassword, String account, String password) {
        return userAuthService.authLogin(authAccount, authPassword, account, password);
    }
}
