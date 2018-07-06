package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.domain.dto.UserSupportingProfilDTO;
import com.isxxc.domain.entity.UserSupportingAuditLogDO;
import com.isxxc.domain.entity.UserSupportingProfilDO;
import com.isxxc.service.UserSupportingAuditLogService;
import com.isxxc.service.UserSupportingProfilService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p> 配套服务商/文化服务商档案 管理 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/userSupportingProfil")
public class UserSupportingProfilController {

    @Resource
    private UserSupportingProfilService userSupportingProfilService;

    @Resource
    private UserSupportingAuditLogService userSupportingAuditLogService;

    /***
     * 查询列表
     * @param page
     * @param userSupportingProfilDO
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Page<UserSupportingProfilDTO> page, UserSupportingProfilDO userSupportingProfilDO) {
        return userSupportingProfilService.list(page, userSupportingProfilDO);
    }

    /***
     *根据ID查询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID");
        }
        return userSupportingProfilService.getInfoById(id);
    }

    /***
     * 内容审核
     * @param userSupportingAuditLogDO
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(UserSupportingAuditLogDO userSupportingAuditLogDO) {
        if (userSupportingAuditLogDO.getSupportingProfilId() == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        if (userSupportingAuditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("审核状态不能为空");
        }
        if (!CommonStateEnum.AuditStateLog.isInclude(userSupportingAuditLogDO.getAuditState())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "审核状态错误");
        }
        return userSupportingProfilService.audit(userSupportingAuditLogDO);
    }

    /***
     * 查询审核日志
     */
    @RequestMapping(value = "auditLogBySupportingProfilId", method = RequestMethod.GET)
    public Result auditLogBySupportingProfilId(Integer supportingProfilId) {
        if (supportingProfilId == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        return userSupportingAuditLogService.auditLogBySupportingProfilId(supportingProfilId);
    }
}
