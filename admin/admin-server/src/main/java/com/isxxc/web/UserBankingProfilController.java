package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.domain.dto.UserBankingProfilDTO;
import com.isxxc.domain.entity.UserBankingProfilAuditLogDO;
import com.isxxc.domain.entity.UserBankingProfilDO;
import com.isxxc.service.UserBankingProfilAuditLogService;
import com.isxxc.service.UserBankingProfilService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p> 金融服务 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/userBankingProfil")
public class UserBankingProfilController {

    @Resource
    private UserBankingProfilService userBankingProfilService;

    @Resource
    private UserBankingProfilAuditLogService userBankingProfilAuditLogService;

    /***
     * 查询列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Page<UserBankingProfilDTO> page, UserBankingProfilDO userBankingProfilDO) {
        return userBankingProfilService.list(page, userBankingProfilDO);
    }

    /***
     * 内容审核
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(UserBankingProfilAuditLogDO userBankingProfilAuditLogDO) {
        if (userBankingProfilAuditLogDO.getBankingProfilId() == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        if (userBankingProfilAuditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("审核状态不能为空");
        }
        if (!CommonStateEnum.AuditStateLog.isInclude(userBankingProfilAuditLogDO.getAuditState())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "审核状态错误");
        }
        return userBankingProfilService.audit(userBankingProfilAuditLogDO);
    }

    /***
     * 查询内容详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return userBankingProfilService.getInfoById(id);
    }

    /***
     * 查询审核日志
     * @param bankingProfilId
     * @return
     */
    @RequestMapping(value = "auditLogByBankingProfilId", method = RequestMethod.GET)
    public Result auditLogByBankingProfilId(Integer bankingProfilId) {
        if (bankingProfilId == null) {
            return ResponseResult.paramNotNull("档案ID不能为空");
        }
        return userBankingProfilAuditLogService.auditLogByBankingProfilId(bankingProfilId);
    }
}
