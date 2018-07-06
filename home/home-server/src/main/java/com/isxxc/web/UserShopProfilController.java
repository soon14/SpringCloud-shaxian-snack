package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.dto.UserShopProfilDTO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.service.UserShopProfilAuditLogService;
import com.isxxc.service.UserShopProfilService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 门店商户档案管理 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/userShopProfil")
public class UserShopProfilController {

    @Resource
    private UserShopProfilService userShopProfilService;

    @Resource
    private UserShopProfilAuditLogService userShopProfilAuditLogService;

    /***
     * 会员店铺档案保存
     * @param userShopProfilDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    private Result save(UserShopProfilDO userShopProfilDO) {
        if (StringUtils.isBlank(userShopProfilDO.getCompanyName())) {
            return ResponseResult.paramNotNull("企业/个体工商户名称不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("门店地址不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getBusinessLicenseImg())) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userShopProfilDO.getBusinessLicenseImg())).exists()) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不存在,请重新上传");
        }
        if (StringUtils.isBlank(userShopProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardFrontImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证正面不存在,请重新上传");
        }
        if (StringUtils.isBlank(userShopProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardBackImg())).exists()) {
            return ResponseResult.paramNotNull("个人身份证反面不存在,请重新上传");
        }
        return userShopProfilService.save(userShopProfilDO);
    }

    /***
     * 更新会员档按信息
     * @param userShopProfilDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    private Result updateInfo(UserShopProfilDO userShopProfilDO) {
        if (StringUtils.isBlank(userShopProfilDO.getCompanyName())) {
            return ResponseResult.paramNotNull("企业/个体工商户名称不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getAddress())) {
            return ResponseResult.paramNotNull("门店地址不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getContact())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getBusinessLicenseImg())) {
            return ResponseResult.paramNotNull("企业/个体工商户营业执照不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getIdentityCardFrontImg())) {
            return ResponseResult.paramNotNull("个人身份证正面不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDO.getIdentityCardBackImg())) {
            return ResponseResult.paramNotNull("个人身份证反面不能为空");
        }
        return userShopProfilService.updateInfo(userShopProfilDO);
    }

    /***
     * 根据会员ID查询档案详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "getInfoByUserId", method = RequestMethod.GET)
    public Result getInfoByUserId(Integer userId) {
        return userShopProfilService.getInfoByUserId(userId);
    }

    /***
     * 根据ID查询档案详情
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
     * 查询列表
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result list(Page<UserShopProfilDTO> page, UserShopProfilDO UserShopProfilDO) {
        return userShopProfilService.listPage(page, UserShopProfilDO);
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

    /***
     * 完善会员档按信息
     * @param userShopProfilDTO
     * @return
     */
    @RequestMapping(value = "completeInfo", method = RequestMethod.POST)
    private Result completeInfo(@RequestBody UserShopProfilDTO userShopProfilDTO, Integer userId) {
        userShopProfilDTO.setUserId(userId);
        if (userShopProfilDTO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (userShopProfilDTO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (userShopProfilDTO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(userShopProfilDTO.getAddress())) {
            return ResponseResult.paramNotNull("门店地址不能为空");
        }
        if (StringUtils.isBlank(userShopProfilDTO.getBusinessHours())) {
            return ResponseResult.paramNotNull("营业时间不能为空");
        }
        return userShopProfilService.completeInfo(userShopProfilDTO);
    }
}
