package com.isxxc.web;


import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserStoreProfilDO;
import com.isxxc.service.UserStoreProfilAuditLogService;
import com.isxxc.service.UserStoreProfilService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 原料供应商档案 管理 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/userStoreProfil")
public class UserStoreProfilController {

    @Resource
    private UserStoreProfilService userStoreProfilService;

    @Resource
    private UserStoreProfilAuditLogService userStoreProfilAuditLogService;

    /***
     * 原料供应商档案保存
     * @param userStoreProfilDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(UserStoreProfilDO userStoreProfilDO) {
        if (StringUtils.isBlank(userStoreProfilDO.getCompanyName())) {
            return ResponseResult.paramNotNull("企业/个体工商户名称不能为空");
        }
        if (userStoreProfilDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userStoreProfilDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userStoreProfilDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getBankUserName())) {
            return ResponseResult.paramNotNull("开户名不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getBankName())) {
            return ResponseResult.paramNotNull("开户银行不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getBankNo())) {
            return ResponseResult.paramNotNull("对公银行账号/银行卡号不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getIndividualBusinessLicenseImg())) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIndividualBusinessLicenseImg())).exists()) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照已失效,请重新上传");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIdentityCardFrontImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证正面已失效,请重新上传");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIdentityCardBackImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证反面已失效,请重新上传");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getBankImg())) {
            return ResponseResult.paramNotNull("银行卡照片不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBankImg())).exists()) {
            return ResponseResult.paramNotNull("银行卡照片已失效,请重新上传");
        }
        return userStoreProfilService.save(userStoreProfilDO);
    }

    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(UserStoreProfilDO userStoreProfilDO) {
        if (StringUtils.isBlank(userStoreProfilDO.getCompanyName())) {
            return ResponseResult.paramNotNull("企业/个体工商户名称不能为空");
        }
        if (userStoreProfilDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userStoreProfilDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userStoreProfilDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getIndividualBusinessLicenseImg())) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getBankImg())) {
            return ResponseResult.paramNotNull("银行卡照片不能为空");
        }
        return userStoreProfilService.updateInfo(userStoreProfilDO);
    }


    /***
     * 根据会员ID查询档案详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByUserId", method = RequestMethod.GET)
    public Result getInfoByUserId(Integer userId) {
        return userStoreProfilService.getInfoByUserId(userId);
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

    /***
     * 完善会员档案信息
     * @return
     */
    @RequestMapping(value = "completeInfo", method = RequestMethod.POST)
    private Result completeInfo(UserStoreProfilDO userStoreProfilDO) {
        if (userStoreProfilDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userStoreProfilDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userStoreProfilDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userStoreProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        return userStoreProfilService.completeInfo(userStoreProfilDO);
    }
}
