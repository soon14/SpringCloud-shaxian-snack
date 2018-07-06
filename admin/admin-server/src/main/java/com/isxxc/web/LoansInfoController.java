package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.dto.LoansInfoDTO;
import com.isxxc.domain.entity.LoansInfoAuditLogDO;
import com.isxxc.domain.entity.LoansInfoDO;
import com.isxxc.service.LoansInfoAuditLogService;
import com.isxxc.service.LoansInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 小贷信息管理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@RestController
@RequestMapping("/loansInfo")
public class LoansInfoController {

    @Resource
    private LoansInfoService loansInfoService;

    @Resource
    private LoansInfoAuditLogService loansInfoAuditLogService;

    /***
     * 保存小贷信息
     * @param loansInfoDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(LoansInfoDO loansInfoDO) {
        if (StringUtils.isBlank(loansInfoDO.getName())) {
            return ResponseResult.paramNotNull("产品名称不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getDescription())) {
            return ResponseResult.paramNotNull("产品简介不能为空");
        }
        if (loansInfoDO.getCategory() == null) {
            return ResponseResult.paramNotNull("请选择类别");
        }
        if (loansInfoDO.getType() == null) {
            return ResponseResult.paramNotNull("请选择小类");
        }
        if (StringUtils.isBlank(loansInfoDO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系电话不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getAdImg())) {
            return ResponseResult.paramNotNull("广告图不能为空");
        }
        if (!new File(CommonFolderConstant.getImageTempPath(loansInfoDO.getAdImg())).exists()) {
            return ResponseResult.paramNotNull("广告图已失效,请重新上传");
        }
        return loansInfoService.save(loansInfoDO);
    }

    /***
     *查询列表
     * @param page
     * @param loansInfoDTO
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page, LoansInfoDTO loansInfoDTO) {
        return loansInfoService.listPage(page, loansInfoDTO);
    }

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return loansInfoService.getInfoById(id);
    }

    /***
     * 更新信息
     * @param loansInfoDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(LoansInfoDO loansInfoDO) {
        if (loansInfoDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getName())) {
            return ResponseResult.paramNotNull("产品名称不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getDescription())) {
            return ResponseResult.paramNotNull("产品简介不能为空");
        }
        if (loansInfoDO.getCategory() == null) {
            return ResponseResult.paramNotNull("请选择类别");
        }
        if (loansInfoDO.getType() == null) {
            return ResponseResult.paramNotNull("请选择小类");
        }
        if (StringUtils.isBlank(loansInfoDO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系电话不能为空");
        }
        if (StringUtils.isBlank(loansInfoDO.getAdImg())) {
            return ResponseResult.paramNotNull("广告图不能为空");
        }
        return loansInfoService.updateInfo(loansInfoDO);
    }

    /***
     *查询日志
     * @param loansInfoId
     * @return
     */
    @RequestMapping(value = "getAuditLogByLoansInfoId", method = RequestMethod.GET)
    public Result getAuditLogByLoansInfoId(Integer loansInfoId) {
        if (loansInfoId == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return loansInfoAuditLogService.getAuditLogByLoansInfoId(loansInfoId);
    }

    /***
     * 审核
     * @param loansInfoAuditLogDO
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(LoansInfoAuditLogDO loansInfoAuditLogDO) {
        if (loansInfoAuditLogDO.getLoansInfoId() == null) {
            return ResponseResult.paramNotNull("投资融资ID不能为空");
        }
        if (loansInfoAuditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("请选择状态");
        }
        return loansInfoAuditLogService.audit(loansInfoAuditLogDO);
    }

    /***
     *更新推荐状态
     * @param id
     * @return
     */
    @RequestMapping(value = "updateRecommend", method = RequestMethod.POST)
    public Result updateRecommend(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return loansInfoService.updateRecommend(id);
    }

    /***
     * 删除信息
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    public Result delById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return loansInfoService.delById(id);
    }
}
