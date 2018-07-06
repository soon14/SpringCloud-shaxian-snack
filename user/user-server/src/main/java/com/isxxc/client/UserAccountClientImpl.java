package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.UserAccountDTO;
import com.isxxc.service.UserAccountService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RestController
public class UserAccountClientImpl implements UserAccountClient {

    @Resource
    private UserAccountService userAccountService;

    @Override
    public Result save(@RequestBody UserAccountDTO userAccountDTO) {
        return userAccountService.save(userAccountDTO);
    }

    @Override
    public Result login(String account, String password, String userAgent) {
        return userAccountService.login(account, password, userAgent);
    }

    @Override
    public Result updatePassword(Integer userId, String password, String newPassword) {
        return userAccountService.updatePassword(userId, password, newPassword);
    }

    @Override
    public Result updateUserInfo(@RequestBody UserAccountDTO userAccountDTO) {
        return userAccountService.updateUserInfo(userAccountDTO);
    }

    @Override
    public Result logout(Integer userId, String userAgent) {
        return userAccountService.logout(userId, userAgent);
    }

    @Override
    public Result saveAvater(Integer userId, String avaterName) {
        return userAccountService.saveAvater(userId, avaterName);
    }

    @Override
    public Result loginByVerifycode(String account, String verifycode, String userAgent) {
        return userAccountService.loginByVerifycode(account, verifycode, userAgent);
    }

    @Override
    public Result resetPasswordByMobileNo(String mobileNo, String verifycode, String newPassword) {
        return userAccountService.resetPasswordByMobileNo(mobileNo, verifycode, newPassword);
    }

    @Override
    public Result listPage(@RequestBody Page page) {
        return userAccountService.listPage(page);
    }

    @Override
    public Result getInfoById(Integer userId) {
        return userAccountService.getInfoById(userId);
    }
}
