package com.isxxc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.OpenUserStatus;
import com.isxxc.constant.UserRoleCodeEnum;
import com.isxxc.constant.XiaoChiPuAuthConstant;
import com.isxxc.dao.OpenUserDAO;
import com.isxxc.domain.entity.OpenUserDO;
import com.isxxc.domain.entity.UserAccountDO;
import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.service.OpenUserService;
import com.isxxc.service.UserAccountService;
import com.isxxc.service.UserRoleRelationService;
import com.isxxc.service.UserShopProfilService;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cc.likq.constant.RedisKeyConstant;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;
import cc.likq.util.HttpUtilResponse;
import cc.likq.util.HttpUtils;
import cc.likq.util.RandomUtils;
import cc.likq.util.TimeUtils;

/**
 * <p>
 * 第三方登录信息 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OpenUserServiceImpl extends ServiceImpl<OpenUserDAO, OpenUserDO> implements OpenUserService {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private OpenUserDAO openUserDAO;

    @Resource
    private RedisClient redisClient;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    @Resource
    private UserShopProfilService userShopProfilService;

    @Override
    public Result xiaoChiPuLogin(String account, String password, String userAgent) {
        // 生成授权签名
        String nonce = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        String signParams = "account=" + account + "&password=" + password + "&nonce=" + nonce + "&key=" + XiaoChiPuAuthConstant.getKey();
        String sign = DigestUtils.md5Hex(signParams).toUpperCase();

        //进行信息校验
        Map<String, String> responsMap = new HashMap<>(4);
        responsMap.put("account", account);
        responsMap.put("password", password);
        responsMap.put("nonce", nonce);
        responsMap.put("sign", sign);
        HttpUtilResponse httpUtilResponse = HttpUtils.get(XiaoChiPuAuthConstant.getServerUrl()).setParams(responsMap).execute();
        if (200 == httpUtilResponse.getCode()) {
            String content = httpUtilResponse.getContent().substring(1, httpUtilResponse.getContent().length() - 1).replaceAll("\\\\", "");
            JSONObject resultJson = JSONObject.parseObject(content);
            if (100 == resultJson.getIntValue("code")) {
                JSONObject openUserInfo = resultJson.getJSONObject("data");
                OpenUserDO openUserDO = openUserDAO.selectByOpenId(account);
                if (null == openUserDO) {
                    //第一次登录
                    openUserDO = new OpenUserDO();
                    openUserDO.setOpenId(account);
                    openUserDO.setStatus(OpenUserStatus.NOT_BOUND.code);
                    openUserDO.setGmtCreate(new Date());
                    insert(openUserDO);
                    redisClient.set(RedisKeyConstant.getOpenUserInfoKey(openUserDO.getId()), openUserInfo.toJSONString(), 3600);
                    Map<String, Object> resultMap = new HashMap<>(1);
                    resultMap.put("openUserId", openUserDO.getId());
                    return ResponseResult.failResult(ResultCodeEnum.FORBIDDEN, "请绑定手机号", resultMap, false);
                } else if (openUserDO.getStatus() == OpenUserStatus.ENABLED.code) {
                    //登录成功
                    UserAccountDO userAccountDO = userAccountService.selectById(openUserDO.getUserId());
                    return userAccountService.saveToke(userAccountDO, userAgent);
                } else if (openUserDO.getStatus() == OpenUserStatus.NOT_BOUND.code) {
                    //未绑定手机
                    redisClient.set(RedisKeyConstant.getOpenUserInfoKey(openUserDO.getId()), openUserInfo.toJSONString(), 3600);
                    Map<String, Object> resultMap = new HashMap<>(1);
                    resultMap.put("openUserId", openUserDO.getId());
                    return ResponseResult.failResult(ResultCodeEnum.FORBIDDEN, "请绑定手机号", resultMap, false);
                } else {
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "该账号已禁用", false);
                }
            } else if (200 == resultJson.getIntValue("code")) {
                return ResponseResult.serverError();
            } else {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, resultJson.getString("message"), false);
            }
        } else {
            return ResponseResult.serverError();
        }
    }

    @Override
    public Result xiaoChiPuBindMobile(Integer openUserId, String mobileNo, String verifyCode, String userAgent) {
        String userBindVerifycode = redisClient.get(RedisKeyConstant.getUserBindVerifycodeKey(mobileNo));
        if (StringUtils.isBlank(userBindVerifycode) || !verifyCode.equals(userBindVerifycode)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "验证码错误");
        }
        OpenUserDO openUserDO = selectById(openUserId);
        if (openUserDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "授权会员ID错误");
        }
        if (openUserDO.getUserId() != null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "授权会员已经绑定");
        }

        String openUserInfoStr = redisClient.get(RedisKeyConstant.getOpenUserInfoKey(openUserDO.getId()));
        if (StringUtils.isBlank(openUserInfoStr)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "绑定超时,请刷新页面再试");
        }
        JSONObject openUserInfoObj = StringUtils.isNotBlank(openUserInfoStr) ? JSONObject.parseObject(openUserInfoStr) : null;

        UserAccountDO userAccountDO = userAccountService.selectByMobileNo(mobileNo);
        if (userAccountDO == null) {
            userAccountDO = new UserAccountDO();
            if (openUserInfoObj != null && openUserInfoObj.containsKey("username") && StringUtils.isNotBlank(openUserInfoObj.getString("username"))) {
                userAccountDO.setNickname(openUserInfoObj.getString("username"));
            } else {
                userAccountDO.setNickname(mobileNo);
            }
            if (openUserInfoObj != null && openUserInfoObj.containsKey("userlogo") && StringUtils.isNotBlank(openUserInfoObj.getString("userlogo"))) {
                String fileName = "";
                try {
                    fileName = FileUtils.copyFileByUrl(openUserInfoObj.getString("userlogo"), CommonFolderConstant.getAvaterPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userAccountDO.setAvaterPath(fileName);
            }
            userAccountDO.setCode(Base64.encodeBase64String((TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED_SSS) + RandomUtils.generateString(5)).getBytes()).replaceAll("=", ""));
            userAccountDO.setGmtModified(new Date());
            userAccountDO.setGmtCreate(new Date());
            userAccountService.insert(userAccountDO);

            if (openUserInfoObj != null && openUserInfoObj.containsKey("level") && openUserInfoObj.getIntValue("level") == 2) {
                //权限关联
                UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();
                userRoleRelationDO.setUserId(userAccountDO.getId());
                userRoleRelationDO.setRoleCode(UserRoleCodeEnum.SHOP.code);
                userRoleRelationDO.setIsDisable(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                userRoleRelationService.insert(userRoleRelationDO);
            }
        }

        EntityWrapper<UserShopProfilDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", userAccountDO.getId());
        UserShopProfilDO userShopProfilDO = userShopProfilService.selectOne(entityWrapper);
        if (openUserInfoObj != null && openUserInfoObj.containsKey("level") && openUserInfoObj.getIntValue("level") == 2 && userShopProfilDO == null) {
            //添加店铺信息
            userShopProfilDO = new UserShopProfilDO();
            userShopProfilDO.setUserId(userAccountDO.getId());
            userShopProfilDO.setSignboardName(openUserInfoObj.getString("username"));
            userShopProfilDO.setCompanyName(openUserInfoObj.getString("username"));
            userShopProfilDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
            userShopProfilDO.setIsComplete(CommonStateEnum.IsComplete.WANE.code);
            userShopProfilService.save(userShopProfilDO);
        }
        openUserDO.setUserId(userAccountDO.getId());
        openUserDO.setStatus(OpenUserStatus.ENABLED.code);
        updateById(openUserDO);
        return userAccountService.saveToke(userAccountDO, userAgent);
    }
}
