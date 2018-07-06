package com.isxxc.web;


import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserBankingProfilDO;
import com.isxxc.service.UserBankingProfilAuditLogService;
import com.isxxc.service.UserBankingProfilService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.ValidatorUtil;

/**
 * <p> 金融服务档案 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/userBankingProfil")
public class UserBankingProfilController {

    @Resource
    private UserBankingProfilService userBankingProfilService;

    @Resource
    private UserBankingProfilAuditLogService userBankingProfilAuditLogService;

    /***
     * 保存用户金融档案
     * @param userBankingProfilDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserBankingProfilDO userBankingProfilDO) {
        if (StringUtils.isBlank(userBankingProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getIdentityCardNo())) {
            return ResponseResult.paramNotNull("身份证号不能为空");
        }
        if (!ValidatorUtil.isIDCard(userBankingProfilDO.getIdentityCardNo())) {
            return ResponseResult.paramNotNull("身份证号不正确");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardFrontImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证正面不存在,请重新上传");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardBackImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证反面不存在,请重新上传");
        }
        return userBankingProfilService.save(userBankingProfilDO);
    }

    /***
     * 档案更新
     * @param userBankingProfilDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(UserBankingProfilDO userBankingProfilDO) {
        if (StringUtils.isBlank(userBankingProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getIdentityCardNo())) {
            return ResponseResult.paramNotNull("身份证号不能为空");
        }
        if (!ValidatorUtil.isIDCard(userBankingProfilDO.getIdentityCardNo())) {
            return ResponseResult.paramNotNull("身份证号不正确");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        return userBankingProfilService.updateInfo(userBankingProfilDO);
    }

    /***
     * 根据会员ID查询档案详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByUserId", method = RequestMethod.GET)
    public Result getInfoByUserId(Integer userId) {
        return userBankingProfilService.getInfoByUserId(userId);
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

    /***
     * 完善会员档按信息
     * @return
     */
    @RequestMapping(value = "completeInfo", method = RequestMethod.POST)
    private Result completeInfo(UserBankingProfilDO userBankingProfilDO) {
        if (userBankingProfilDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userBankingProfilDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userBankingProfilDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userBankingProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        return userBankingProfilService.completeInfo(userBankingProfilDO);
    }
}
