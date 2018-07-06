package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserAccountDTO;
import com.isxxc.domain.entity.UserAccountDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
public interface UserAccountService extends IService<UserAccountDO> {

    /***
     * 保存用户信息
     * @param userAccountDTO
     * @return
     */
    Result save(UserAccountDTO userAccountDTO);

    /***
     *登录
     * @param account
     * @param password
     * @return
     */
    Result login(String account, String password, String userAgent);

    /***
     * 更改密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    Result updatePassword(Integer userId, String password, String newPassword);

    /***
     * 更新用户信息
     */
    Result updateUserInfo(UserAccountDO userAccountDO);

    /***
     * 注销登录
     * @param userId
     * @return
     */
    Result logout(Integer userId, String userAgent);

    /***
     *
     * @param userId
     * @param avaterName
     * @return
     */
    Result saveAvater(Integer userId, String avaterName);

    /***
     *验证码登录
     * @param account
     * @param verifycode
     * @param userAgent
     * @return
     */
    Result loginByVerifycode(String account, String verifycode, String userAgent);

    /***
     * 重置密码
     * @param mobileNo
     * @param verifycode
     * @param newPassword
     * @return
     */
    Result resetPasswordByMobileNo(String mobileNo, String verifycode, String newPassword);

    /***
     * 查询列表
     * @param page
     * @return
     */
    Result listPage(Page page);

    /***
     * 获取详情信息
     * @param userId
     * @return
     */
    Result getInfoById(Integer userId);

    /***
     * 保存Token信息
     * @param userAccountDO
     * @param userAgent
     * @return
     */
    Result saveToke(UserAccountDO userAccountDO, String userAgent);

    /***
     * 根据手机号查询
     * @param mobileNo
     * @return
     */
    UserAccountDO selectByMobileNo(String mobileNo);
}
