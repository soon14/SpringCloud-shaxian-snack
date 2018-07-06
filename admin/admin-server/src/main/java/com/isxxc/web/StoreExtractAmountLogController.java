package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.StoreExtractAmountLogDTO;
import com.isxxc.domain.entity.StoreExtractAmountAuditLogDO;
import com.isxxc.service.feign.store.StoreExtractAmountAuditLogService;
import com.isxxc.service.feign.store.StoreExtractAmountLogService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商店提取金额日志 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-06
 */
@RestController
@RequestMapping("/storeExtractAmountLog")
public class StoreExtractAmountLogController {

    @Resource
    private StoreExtractAmountLogService storeExtractAmountLogService;

    @Resource
    private StoreExtractAmountAuditLogService storeExtractAmountAuditLogService;

    /***
     * 查询申请列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer settlementState) {
        if (settlementState != null) {
            pager.putParam("settlementState", settlementState);
        }
        return storeExtractAmountLogService.listPage(pager);
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
        return storeExtractAmountLogService.getInfoById(id);
    }

    /***
     * 审核
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(StoreExtractAmountAuditLogDO auditLogDO) {
        if (auditLogDO.getExtractAmountLogId() == null) {
            return ResponseResult.paramNotNull("申请提现ID不能为空");
        }
        if (auditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("审核状态不能为空");
        }
        return storeExtractAmountAuditLogService.audit(auditLogDO);
    }

    /***
     * 结算
     * @return
     */
    @RequestMapping(value = "settlementAmount", method = RequestMethod.POST)
    public Result settlementAmount(StoreExtractAmountLogDTO storeExtractAmountLogDTO) {
        if (storeExtractAmountLogDTO.getId() == null) {
            return ResponseResult.paramNotNull("申请提现ID不能为空");
        }
        if (storeExtractAmountLogDTO.getSettlementAmount() == null) {
            return ResponseResult.paramNotNull("结算金额不能为空");
        }
        if (storeExtractAmountLogDTO.getPoundage() == null || storeExtractAmountLogDTO.getPoundage() < 0) {
            return ResponseResult.paramNotNull("手续费不能为空或小于0");
        }
        return storeExtractAmountLogService.settlementAmount(storeExtractAmountLogDTO);
    }
}
