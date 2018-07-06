package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.StoreAmountEnum;
import com.isxxc.dao.StoreExtractAmountLogDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.StoreExtractAmountLogDTO;
import com.isxxc.domain.entity.StoreAmountDO;
import com.isxxc.domain.entity.StoreAmountLogDO;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;
import com.isxxc.service.StoreAmountLogService;
import com.isxxc.service.StoreAmountService;
import com.isxxc.service.StoreExtractAmountLogService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 商店提取金额日志 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreExtractAmountLogServiceImpl extends ServiceImpl<StoreExtractAmountLogDAO, StoreExtractAmountLogDO> implements StoreExtractAmountLogService {

    @Resource
    private StoreAmountService storeAmountService;

    @Resource
    private StoreExtractAmountLogDAO storeExtractAmountLogDAO;

    @Resource
    private StoreAmountLogService storeAmountLogService;

    @Override
    public Result save(StoreExtractAmountLogDO storeExtractAmountLogDO) {
        Long permitExtractAmount = permitExtractAmount(storeExtractAmountLogDO.getStoreId());
        if (storeExtractAmountLogDO.getExtractAmount() > permitExtractAmount) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "提现金额不能超过可提现金额,可提现金额为: " + (permitExtractAmount / 1000) + " 元");
        }
        storeExtractAmountLogDO.setPoundage(0L);
        storeExtractAmountLogDO.setSettlementAmount(0L);
        storeExtractAmountLogDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        storeExtractAmountLogDO.setSettlementState(CommonStateEnum.SettlementState.NO.code);
        storeExtractAmountLogDO.setGmtModified(new Date());
        storeExtractAmountLogDO.setGmtCreate(new Date());

        return insert(storeExtractAmountLogDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listPageByStoreId(Pager pager) {
        EntityWrapper<StoreExtractAmountLogDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_id", pager.getParamMap().get("storeId"));
        if (pager.getParamMap().containsKey("settlementState")) {
            entityWrapper.eq("settlement_state", pager.getParamMap().get("settlementState"));
        }
        pager = (Pager) selectPage(pager, entityWrapper);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getAmountInfo(Integer storeId) {
        EntityWrapper<StoreAmountDO> storeAmountDOEntityWrapper = new EntityWrapper<>();
        storeAmountDOEntityWrapper.eq("store_id", storeId);
        StoreAmountDO storeAmountDO = storeAmountService.selectOne(storeAmountDOEntityWrapper);
        Long extractProcessAmount = extractProcessAmount(storeId);
        Long permitExtractAmount = permitExtractAmount(storeId);
        Map<String, Long> result = new HashMap<>(4);
        result.put("amount", storeAmountDO.getAmount());
        result.put("totalExtractionAmount", storeAmountDO.getTotalExtractionAmount());
        result.put("extractProcessAmount", extractProcessAmount);
        result.put("permitExtractAmount", permitExtractAmount);
        return ResponseResult.success(result);
    }

    @Override
    public Result listPage(Pager pager) {
        List<StoreExtractAmountLogDTO> dtoList = storeExtractAmountLogDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(dtoList);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoById(Integer id) {
        StoreExtractAmountLogDTO storeExtractAmountLogDTO = storeExtractAmountLogDAO.getInfoById(id);
        return storeExtractAmountLogDTO != null ? ResponseResult.success(storeExtractAmountLogDTO) : ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "ID错误,数据不存在");
    }

    @Override
    public Result settlementAmount(Integer extractAmountLogId, Long settlementAmount, Long poundage, String remark) {
        StoreExtractAmountLogDO storeExtractAmountLogDO = storeExtractAmountLogDAO.selectById(extractAmountLogId);
        if (storeExtractAmountLogDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "申请提现ID错误");
        }
        if (storeExtractAmountLogDO.getAuditState() != CommonStateEnum.AuditState.PASSED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "审核未通过,不允许结算");
        }
        if (storeExtractAmountLogDO.getSettlementState() == CommonStateEnum.SettlementState.YES.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经结算,不要重复结算");
        }
        if (!settlementAmount.equals(storeExtractAmountLogDO.getExtractAmount() - poundage)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "结算金额计算错误");
        }
        if (settlementAmount > storeExtractAmountLogDO.getExtractAmount()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "结算金额不能大于提现金额");
        }
        if (StringUtils.isNotBlank(remark)) {
            storeExtractAmountLogDO.setRemarkService(remark);
        }
        storeExtractAmountLogDO.setPoundage(poundage);
        storeExtractAmountLogDO.setSettlementAmount(settlementAmount);
        storeExtractAmountLogDO.setSettlementState(CommonStateEnum.SettlementState.YES.code);


        EntityWrapper<StoreAmountDO> storeAmountDOEntityWrapper = new EntityWrapper<>();
        storeAmountDOEntityWrapper.eq("store_id", storeExtractAmountLogDO.getStoreId());
        StoreAmountDO storeAmountDO = storeAmountService.selectOne(storeAmountDOEntityWrapper);

        StoreAmountLogDO storeAmountLogDO = new StoreAmountLogDO();
        storeAmountLogDO.setStoreAmountId(storeAmountDO.getId());
        storeAmountLogDO.setAmount(0L - storeExtractAmountLogDO.getExtractAmount());
        storeAmountLogDO.setBoforeAmount(storeAmountDO.getAmount());
        storeAmountLogDO.setType(StoreAmountEnum.Type.WITHDRAW.code);
        storeAmountLogDO.setOrderNo("");
        storeAmountLogDO.setGmtModified(new Date());
        storeAmountLogDO.setGmtCreate(new Date());

        storeAmountDO.setAmount(storeAmountDO.getAmount() - storeExtractAmountLogDO.getExtractAmount());
        storeAmountDO.setTotalExtractionAmount(storeAmountDO.getTotalExtractionAmount() + storeExtractAmountLogDO.getExtractAmount());
        storeAmountService.updateById(storeAmountDO);
        storeAmountLogService.insert(storeAmountLogDO);
        storeExtractAmountLogDAO.updateById(storeExtractAmountLogDO);
        return ResponseResult.success();
    }

    /***
     * 获取可提现金额,15天后
     * @param storeId
     * @return
     */
    private Long permitExtractAmount(Integer storeId) {
        return storeAmountService.permitExtractAmount(storeId) - extractProcessAmount(storeId);
    }

    /***
     * 获取提现中的金额
     * @param storeId
     * @return
     */
    private Long extractProcessAmount(Integer storeId) {
        return storeExtractAmountLogDAO.extractProcessAmountByStoreId(storeId);
    }
}
