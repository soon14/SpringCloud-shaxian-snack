package com.isxxc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.RedisKeyConstant;
import com.isxxc.constant.UserAgentEnum;
import com.isxxc.dao.UserAccountDAO;
import com.isxxc.dao.UserRoleRelationDAO;
import com.isxxc.dao.UserStoreProfilDAO;
import com.isxxc.domain.dto.UserAccountDTO;
import com.isxxc.domain.dto.UserInfoDTO;
import com.isxxc.domain.entity.UserAccountDO;
import com.isxxc.service.UserAccountService;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;
import cc.likq.util.MyBeanUtils;
import cc.likq.util.RandomUtils;
import cc.likq.util.TimeUtils;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAccountServiceImpl extends ServiceImpl<UserAccountDAO, UserAccountDO> implements UserAccountService {

    @Resource
    private UserAccountDAO userAccountDAO;

    @Resource
    private UserRoleRelationDAO userRoleRelationDAO;

    @Resource
    private UserStoreProfilDAO userStoreProfilDAO;

    @Resource
    private RedisClient redisClient;

    @Override
    public Result save(UserAccountDTO userAccountDTO) {
        String registerVerifyCodeKey = RedisKeyConstant.getRegisterVerifyCodeKey(userAccountDTO.getMobileNo());
        String registerVerifyCode = redisClient.get(registerVerifyCodeKey);
        if (StringUtils.isBlank(registerVerifyCode)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "验证码已过期,请重新获取");
        }
        if (!userAccountDTO.getVerifyCode().trim().equals(registerVerifyCode.trim())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "验证码不正确");
        }
        UserAccountDO userAccountDODB = userAccountDAO.selectByMobileNo(userAccountDTO.getMobileNo());
        if (userAccountDODB != null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "手机已经注册");
        }
        String userCode = Base64.encodeBase64String((TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED_SSS) + RandomUtils.generateString(5)).getBytes()).replaceAll("=", "");
        userAccountDTO.setCode(userCode);
        userAccountDTO.setNickname(userAccountDTO.getMobileNo());
        userAccountDTO.setPassword(DigestUtils.sha1Hex(userAccountDTO.getPassword().trim()).toUpperCase());
        userAccountDTO.setGmtModified(new Date());
        userAccountDTO.setGmtCreate(new Date());
        return insert(userAccountDTO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result login(String account, String password, String userAgent) {
        UserAccountDO userAccountDO = userAccountDAO.selectByMobileNo(account.trim());
        if (userAccountDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "账号不存在");
        }
        if (!userAccountDO.getPassword().toUpperCase().equals(DigestUtils.sha1Hex(password.trim()).toUpperCase())){
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "密码错误");
        }
        return saveToke(userAccountDO, userAgent);
    }

    @Override
    public Result updatePassword(Integer userId, String password, String newPassword) {
        UserAccountDO userAccountDO = selectById(userId);
        if (userAccountDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "会员不存在");
        }
        if (!userAccountDO.getPassword().equals(DigestUtils.sha1Hex(password.trim()))) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "原密码错误");
        }
        userAccountDO.setPassword(DigestUtils.sha1Hex(newPassword.trim()).toUpperCase());
        return baseUpdatePassword(userAccountDO);
    }

    @Override
    public Result updateUserInfo(UserAccountDO userAccountDO) {
        UserAccountDO userAccountDODB = selectById(userAccountDO.getId());
        userAccountDO.setCode(null);
        userAccountDO.setId(null);
        userAccountDO.setEmail(null);
        userAccountDO.setPassword(null);
        userAccountDO.setAvaterPath(null);
        userAccountDO.setMobileNo(null);
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userAccountDO, userAccountDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(userAccountDODB) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result logout(Integer userId, String userAgent) {
        String loginTokenKey = RedisKeyConstant.getLoginTokenKey(userId, userAgent);
        redisClient.del(loginTokenKey);
        return ResponseResult.success();
    }

    @Override
    public Result saveAvater(Integer userId, String avaterName) {
        if (!new File(CommonFolderConstant.getImageTempPath(avaterName)).exists()) {
            return ResponseResult.paramNotNull("头像文件不存在");
        }
        UserAccountDO userAccountDO = selectById(userId);
        if (StringUtils.isNotBlank(userAccountDO.getAvaterPath())) {
            FileUtils.delete(CommonFolderConstant.getAvaterPath(userAccountDO.getAvaterPath()));
        }
        userAccountDO.setAvaterPath(avaterName);
        updateById(userAccountDO);
        FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(avaterName), CommonFolderConstant.getAvaterPath());
        return ResponseResult.success();
    }

    @Override
    public Result loginByVerifycode(String account, String verifyCode, String userAgent) {
        UserAccountDO userAccountDO = userAccountDAO.selectByMobileNo(account.trim());
        if (userAccountDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "账号不存在");
        }
        String loginVerifyCodeKey = RedisKeyConstant.getLoginVerifyCodeKey(account);
        String loginVerifyCode = redisClient.get(loginVerifyCodeKey);
        if (StringUtils.isBlank(loginVerifyCode) || !verifyCode.trim().equals(loginVerifyCode.trim())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "验证码错误");
        }
        return saveToke(userAccountDO, userAgent);
    }

    @Override
    public Result resetPasswordByMobileNo(String mobileNo, String verifyCode, String newPassword) {
        UserAccountDO userAccountDO = userAccountDAO.selectByMobileNo(mobileNo);
        if (userAccountDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "帐户不存在");
        }
        String resetPasswordVerifyCodeKey = RedisKeyConstant.getResetPasswordVerifyCodeKey(mobileNo);
        String verifycodeDB = redisClient.get(resetPasswordVerifyCodeKey);
        if (StringUtils.isBlank(verifycodeDB) || !verifyCode.trim().equalsIgnoreCase(verifycodeDB)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "验证码错误,请重新获取再试");
        }
        userAccountDO.setPassword(DigestUtils.sha1Hex(newPassword.trim()).toUpperCase());
        return baseUpdatePassword(userAccountDO);
    }

    private Result baseUpdatePassword(UserAccountDO userAccountDO) {
        if (updateById(userAccountDO)) {
            String loginTokenKey = RedisKeyConstant.getLoginTokenKey(userAccountDO.getId(), UserAgentEnum.ALL);
            Set<String> loginTokenKeys = redisClient.getResource().keys(loginTokenKey);
            if (loginTokenKeys != null && !loginTokenKeys.isEmpty()) {
                redisClient.getResource().del(loginTokenKeys.toArray(new String[loginTokenKeys.size()]));
            }
            return ResponseResult.successMsg("密码更新成功");
        } else {
            return ResponseResult.serverError();
        }
    }

    @Override
    public Result listPage(Page page) {
        EntityWrapper<UserAccountDO> entityWrapper = new EntityWrapper<>();
        page = selectPage(page, entityWrapper);
        List<UserAccountDO> userAccountDOList = page.getRecords();
        List<UserAccountDTO> userAccountDTOList = new ArrayList<>(userAccountDOList.size());
        userAccountDOList.forEach(userAccountDO -> {
            userAccountDO.setPassword(null);
            userAccountDTOList.add(new UserAccountDTO(userAccountDO));
        });
        page.setRecords(userAccountDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result getInfoById(Integer userId) {
        UserAccountDO userAccountDO = selectById(userId);
        UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountDO);
        userAccountDTO.setPassword(null);
        return ResponseResult.success(userAccountDTO);
    }

    /***
     * 保存Toke
     * @param userAccountDO
     * @param userAgent
     * @return
     */
    @Override
    public Result saveToke(UserAccountDO userAccountDO, String userAgent) {
        //生成Token
        String token = RandomUtils.generateString(32);
        Integer storeId = userStoreProfilDAO.getIdByUserId(userAccountDO.getId());
        List<String> roleList = userRoleRelationDAO.getCodesByUserId(userAccountDO.getId(), CommonStateEnum.IsDisable.NOT_DISABLE.code);
        //Token默认有效时长7天
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userAccountDO.getId());
        userInfoDTO.setNickname(userAccountDO.getNickname());
        if (storeId != null) {
            userInfoDTO.setStoreId(storeId);
        }
        String roles = null;
        if (roleList != null && !roleList.isEmpty()) {
            userInfoDTO.setRoleList(roleList);
            roles = String.join(",", roleList);
        }
        redisClient.set(RedisKeyConstant.getLoginTokenKey(userAccountDO.getId(), userAgent), token, 604800);
        redisClient.set(RedisKeyConstant.getUserInfoKey(userAccountDO.getCode()), JSON.toJSONString(userInfoDTO), 604800);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("token", token);
        resultMap.put("userCode", userAccountDO.getCode());
        resultMap.put("nickname", userAccountDO.getNickname());
        resultMap.put("roles", roles);
        resultMap.put("userId",userAccountDO.getId());
        return ResponseResult.success(resultMap);
    }

    @Override
    public UserAccountDO selectByMobileNo(String mobileNo) {
        return userAccountDAO.selectByMobileNo(mobileNo);
    }

}
