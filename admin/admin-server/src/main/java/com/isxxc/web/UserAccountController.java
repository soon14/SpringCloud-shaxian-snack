package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.service.feign.user.UserAccountService;
import com.isxxc.service.feign.user.UserRoleRelationService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员管理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/userAccount")
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    /***
     * 查询列表，带分页
     * @param page
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page) {
        return userAccountService.listPage(page);
    }

    /***
     * 获取会员详情信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer userId) {
        if (userId == null) {
            return ResponseResult.paramNotNull("会员ID不能为空");
        }
        return userAccountService.getInfoById(userId);
    }

    /***
     * 角色与菜单关联
     * @param userId
     * @param roleCodes
     * @return
     */
    @RequestMapping(value = "userRoleRelation", method = RequestMethod.POST)
    public Result userRoleRelation(Integer userId, String roleCodes) {
        if (userId == null) {
            return ResponseResult.paramNotNull("会员ID不能为空");
        }
        if (StringUtils.isBlank(roleCodes)) {
            return ResponseResult.paramNotNull("会员角色不能为空");
        }
        return userRoleRelationService.userRoleRelation(userId, roleCodes);
    }

}
