package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.UserAccountDTO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;


/**
 * @author 泥水佬
 * @date 2017/12/17
 */
@RequestMapping("/userAccountClient")
public interface UserAccountClient {

    /***
     * 保存用户信息
     * @param userAccountDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(UserAccountDTO userAccountDTO);

    /***
     *登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    Result login(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("userAgent") String userAgent);

    /***
     * 更改密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    Result updatePassword(@RequestParam("userId") Integer userId, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword);

    /***
     * 更新用户信息
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateUserInfo(UserAccountDTO userAccountDTO);

    /***
     * 注销登录
     * @param userId
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    Result logout(@RequestParam("userId") Integer userId, @RequestParam("userAgent") String userAgent);

    /***
     * 添加头像
     * @param userId
     * @param avaterName
     * @return
     */
    @RequestMapping(value = "saveAvater", method = RequestMethod.POST)
    Result saveAvater(@RequestParam("userId") Integer userId, @RequestParam("avaterName") String avaterName);

    /***
     *验证码登录
     * @param account
     * @param verifycode
     * @param userAgent
     * @return
     */
    @RequestMapping(value = "loginByVerifycode", method = RequestMethod.POST)
    Result loginByVerifycode(@RequestParam("account") String account, @RequestParam("verifycode") String verifycode, @RequestParam("userAgent") String userAgent);

    /***
     * 重置密码
     * @param mobileNo
     * @param verifycode
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "resetPasswordByMobileNo", method = RequestMethod.POST)
    Result resetPasswordByMobileNo(@RequestParam("mobileNo") String mobileNo, @RequestParam("verifycode") String verifycode, @RequestParam("newPassword") String newPassword);

    /***
     * 查询列表
     * @param page
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Page page);

    /***
     * 获取详情信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    Result getInfoById(@RequestParam("userId") Integer userId);
}
