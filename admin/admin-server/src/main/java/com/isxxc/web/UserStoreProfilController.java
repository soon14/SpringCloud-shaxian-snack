package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.UserStoreProfilAuditLogDO;
import com.isxxc.domain.entity.UserStoreProfilDO;
import com.isxxc.service.UserStoreProfilAuditLogService;
import com.isxxc.service.UserStoreProfilService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p> 原料供应商商户档案 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/userStoreProfil")
public class UserStoreProfilController {

    @Resource
    private UserStoreProfilService userStoreProfilService;

    @Resource
    private UserStoreProfilAuditLogService userStoreProfilAuditLogService;

    /***
     * 查询列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Page<UserStoreProfilDTO> page, UserStoreProfilDO userStoreProfilDO) {
        return userStoreProfilService.list(page, userStoreProfilDO);
    }

    /***
     * 内容审核
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(UserStoreProfilAuditLogDO userStoreProfilAuditLogDO) {
        if (userStoreProfilAuditLogDO.getStoreProfilId() == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        if (userStoreProfilAuditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("审核状态不能为空");
        }
        if (!CommonStateEnum.AuditStateLog.isInclude(userStoreProfilAuditLogDO.getAuditState())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "审核状态错误");
        }
        return userStoreProfilService.audit(userStoreProfilAuditLogDO);
    }

    /***
     * 查询详情信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID 不能为空");
        }
        return userStoreProfilService.getInfoById(id);
    }


    /***
     * 查询审核日志
     * @param storeProfilId
     * @return
     */
    @RequestMapping(value = "auditLogByStoreProfilId", method = RequestMethod.GET)
    public Result auditLogByStoreProfilId(Integer storeProfilId) {
        if (storeProfilId == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        return userStoreProfilAuditLogService.auditLogByStoreProfilId(storeProfilId);
    }
}
