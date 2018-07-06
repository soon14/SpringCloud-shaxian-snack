package com.isxxc.web;


import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserSupportingProfilDO;
import com.isxxc.service.UserSupportingAuditLogService;
import com.isxxc.service.UserSupportingProfilService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 会员配套服务商/文化服务商档案 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/userSupportingProfil")
public class UserSupportingProfilController {

    @Resource
    private UserSupportingProfilService userSupportingProfilService;

    @Resource
    private UserSupportingAuditLogService userSupportingAuditLogService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    private Result save(UserSupportingProfilDO userSupportingProfilDO) {
        if (StringUtils.isBlank(userSupportingProfilDO.getCompanyName())) {
            return ResponseResult.paramNotNull("企业/个体工商户名称不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("门店地址不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getBusinessLicenseImg())) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getBusinessLicenseImg())).exists()) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不存在,请重新上传");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardFrontImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证正面不存在,请重新上传");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardBackImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证反面不存在,请重新上传");
        }
        return userSupportingProfilService.save(userSupportingProfilDO);
    }

    /***
     * 更新档案
     * @param userSupportingProfilDO
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    private Result updateInfo(UserSupportingProfilDO userSupportingProfilDO) {
        if (userSupportingProfilDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getCompanyName())) {
            return ResponseResult.paramNotNull("企业/个体工商户名称不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("门店地址不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getBusinessLicenseImg())) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        return userSupportingProfilService.updateInfo(userSupportingProfilDO);
    }

    /***
     * 根据会员ID查询档案详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByUserId", method = RequestMethod.GET)
    public Result getInfoByUserId(Integer userId) {
        return userSupportingProfilService.getInfoByUserId(userId);
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

    /***
     * 完善会员档按信息
     * @return
     */
    @RequestMapping(value = "completeInfo", method = RequestMethod.POST)
    private Result completeInfo(UserSupportingProfilDO userSupportingProfilDO) {
        if (userSupportingProfilDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userSupportingProfilDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userSupportingProfilDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userSupportingProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        return userSupportingProfilService.completeInfo(userSupportingProfilDO);
    }
}
