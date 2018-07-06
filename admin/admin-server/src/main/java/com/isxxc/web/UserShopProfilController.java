package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.domain.dto.UserShopProfilDTO;
import com.isxxc.domain.entity.UserShopProfilAuditLogDO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.service.UserShopProfilAuditLogService;
import com.isxxc.service.UserShopProfilService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>商铺信息管理 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/userShopProfil")
public class UserShopProfilController {

    @Resource
    private UserShopProfilService userShopProfilService;

    @Resource
    private UserShopProfilAuditLogService userShopProfilAuditLogService;

    /***
     * 查询列表
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result list(Page<UserShopProfilDTO> page, UserShopProfilDO UserShopProfilDO) {
        return userShopProfilService.listPage(page, UserShopProfilDO);
    }

    /***
     * 内容审核
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(UserShopProfilAuditLogDO userShopProfilAuditLogDO) {
        if (userShopProfilAuditLogDO.getShopProfilId() == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        if (userShopProfilAuditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("审核状态不能为空");
        }
        if (!CommonStateEnum.AuditStateLog.isInclude(userShopProfilAuditLogDO.getAuditState())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "审核状态错误");
        }
        return userShopProfilService.audit(userShopProfilAuditLogDO);
    }

    /***
     *根据ID查询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return userShopProfilService.getInfoById(id);
    }

    /***
     * 查询审核日志
     * @param shopProfilId
     * @return
     */
    @RequestMapping(value = "auditLogByShopProfilId", method = RequestMethod.GET)
    public Result auditLogByShopProfilId(Integer shopProfilId) {
        if (shopProfilId == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        return userShopProfilAuditLogService.auditLogByShopProfilId(shopProfilId);
    }
}
