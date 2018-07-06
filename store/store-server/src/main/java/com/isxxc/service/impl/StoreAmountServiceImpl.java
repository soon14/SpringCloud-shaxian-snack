package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.StoreAmountEnum;
import com.isxxc.dao.StoreAmountDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.StoreAmountDO;
import com.isxxc.domain.entity.StoreAmountLogDO;
import com.isxxc.service.StoreAmountLogService;
import com.isxxc.service.StoreAmountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 商店金额 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreAmountServiceImpl extends ServiceImpl<StoreAmountDAO, StoreAmountDO> implements StoreAmountService {

    @Resource
    private StoreAmountLogService storeAmountLogService;

    @Resource
    private StoreAmountDAO storeAmountDAO;

    @Override
    public Result updateAmount(Integer storeId, Long amount, String orderNo, StoreAmountEnum.Type type) {
        EntityWrapper<StoreAmountDO> storeAmountDOEntityWrapper = new EntityWrapper<>();
        storeAmountDOEntityWrapper.eq("store_id", storeId);
        StoreAmountDO storeAmountDO = selectOne(storeAmountDOEntityWrapper);
        //日志
        StoreAmountLogDO storeAmountLogDO = new StoreAmountLogDO();
        storeAmountLogDO.setAmount(amount);
        storeAmountLogDO.setBoforeAmount(storeAmountDO.getAmount());
        storeAmountLogDO.setOrderNo(orderNo);
        storeAmountLogDO.setStoreAmountId(storeAmountDO.getId());
        storeAmountLogDO.setType(type.code);
        storeAmountLogDO.setGmtCreate(new Date());
        storeAmountLogDO.setGmtModified(new Date());
        storeAmountLogService.insert(storeAmountLogDO);
        //更新总金额及交易总额
        if (type == StoreAmountEnum.Type.IN_AMOUNT) {
            storeAmountDO.setAmount(storeAmountDO.getAmount() + amount);
        } else if (type == StoreAmountEnum.Type.REFUND_AMOUNT) {
            if (storeAmountDO.getAmount() < amount) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "余额不足以抵扣");
            }
            storeAmountDO.setAmount(storeAmountDO.getAmount() - amount);
        } else {
            if (storeAmountDO.getAmount() < amount) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "提款金额超出可提金额");
            }
            storeAmountDO.setAmount(storeAmountDO.getAmount() - amount);
        }
        updateById(storeAmountDO);
        return ResponseResult.success();
    }

    @Override
    public Result getInfoByStore(Integer storeId) {
        EntityWrapper<StoreAmountDO> storeAmountDOEntityWrapper = new EntityWrapper<>();
        storeAmountDOEntityWrapper.eq("store_id", storeId);
        StoreAmountDO storeAmountDO = selectOne(storeAmountDOEntityWrapper);
        if (storeAmountDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "数据错误,请联系管理员");
        }
        return ResponseResult.success(storeAmountDO);
    }

    @Override
    public Result getAmountLogByStore(Pager pager) {
        EntityWrapper<StoreAmountDO> storeAmountDOEntityWrapper = new EntityWrapper<>();
        storeAmountDOEntityWrapper.eq("store_id", pager.getParamMap().get("storeId"));
        StoreAmountDO storeAmountDO = selectOne(storeAmountDOEntityWrapper);
        if (storeAmountDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "数据错误,请联系管理员");
        }
        if (storeAmountDO.getId() != (int) pager.getParamMap().get("storeAmountId")) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "数据错误,请联系管理员");
        }
        EntityWrapper<StoreAmountLogDO> storeAmountLogDOEntityWrapper = new EntityWrapper<>();
        storeAmountLogDOEntityWrapper.eq("store_amount_id", storeAmountDO.getId());
        storeAmountLogDOEntityWrapper.orderBy("gmt_modified", false);
        pager = (Pager) storeAmountLogService.selectPage(pager, storeAmountLogDOEntityWrapper);
        return ResponseResult.success(pager);
    }

    @Override
    public Long permitExtractAmount(Integer storeId) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -15);
        String endTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime());
        return storeAmountDAO.permitExtractAmount(storeId, endTime);
    }
}
