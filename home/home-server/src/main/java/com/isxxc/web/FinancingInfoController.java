package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.FinancingInfoDTO;
import com.isxxc.service.FinancingInfoAuditLogService;
import com.isxxc.service.FinancingInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 融资信息 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@RestController
@RequestMapping("/financingInfo")
public class FinancingInfoController {

    @Resource
    private FinancingInfoService financingInfoService;

    @Resource
    private FinancingInfoAuditLogService financingInfoAuditLogService;

    /***
     *保存融资信息
     * @param financingInfoDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody FinancingInfoDTO financingInfoDTO, Integer userId) {
        financingInfoDTO.setUserId(userId);
        if (StringUtils.isBlank(financingInfoDTO.getName())) {
            return ResponseResult.paramNotNull("项目名称不能为空");
        }
        if (financingInfoDTO.getCategory() == null) {
            return ResponseResult.paramNotNull("请选择融资方式");
        }
        if (financingInfoDTO.getAmount() == null) {
            return ResponseResult.paramNotNull("融资金额不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getDescription())) {
            return ResponseResult.paramNotNull("项目简介不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getCharacteristic())) {
            return ResponseResult.paramNotNull("项目特色不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系电话不能为空");
        }
        return financingInfoService.save(financingInfoDTO);
    }

    /***
     * 查询列表，带分页
     * @param page
     * @param financingInfoDTO
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page, FinancingInfoDTO financingInfoDTO) {
        return financingInfoService.listPage(page, financingInfoDTO);
    }

    /***
     * 根据会员信息列表
     * @param page
     * @param financingInfoDTO
     * @return
     */
    @RequestMapping(value = "listPageByUserId", method = RequestMethod.GET)
    public Result listPageByUserId(Page page, FinancingInfoDTO financingInfoDTO) {
        return financingInfoService.listPageByUserId(page, financingInfoDTO);
    }

    /***
     * 更新
     * @param financingInfoDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(@RequestBody FinancingInfoDTO financingInfoDTO) {
        if (financingInfoDTO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getName())) {
            return ResponseResult.paramNotNull("项目名称不能为空");
        }
        if (financingInfoDTO.getCategory() == null) {
            return ResponseResult.paramNotNull("请选择融资方式");
        }
        if (financingInfoDTO.getAmount() == null) {
            return ResponseResult.paramNotNull("融资金额不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getDescription())) {
            return ResponseResult.paramNotNull("项目简介不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getCharacteristic())) {
            return ResponseResult.paramNotNull("项目特色不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getRemark())) {
            return ResponseResult.paramNotNull("备注不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(financingInfoDTO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系电话不能为空");
        }
        return financingInfoService.updateInfo(financingInfoDTO);
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
        return financingInfoService.getInfoById(id);
    }

    /***
     *查询日志
     * @param financingInfoId
     * @return
     */
    @RequestMapping(value = "getAuditLogByFinancingInfoId", method = RequestMethod.GET)
    public Result getAuditLogByFinancingInfoId(Integer financingInfoId) {
        if (financingInfoId == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return financingInfoAuditLogService.getAuditLogByFinancingInfoId(financingInfoId);
    }

    /***
     * 取消发布
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelPublist", method = RequestMethod.POST)
    public Result cancelPublist(Integer id, Integer userId) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return financingInfoService.cancelPublist(id, userId);
    }

    /***
     * 发布上线
     * @param id
     * @return
     */
    @RequestMapping(value = "publist", method = RequestMethod.POST)
    public Result publist(Integer id, Integer userId) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return financingInfoService.publist(id, userId);
    }

    /***
     * 猜你喜欢
     * @param size
     * @return
     */
    @RequestMapping(value = "mayLike", method = RequestMethod.GET)
    public Result mayLike(Integer size, Integer category) {
        if (size == null || size <= 0) {
            return ResponseResult.paramNotNull("获取数据不能为空或小于等于0");
        }
        return financingInfoService.mayLike(size, category);
    }
}
