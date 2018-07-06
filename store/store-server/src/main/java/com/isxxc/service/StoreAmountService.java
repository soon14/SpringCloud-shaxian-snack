package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.constant.StoreAmountEnum;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.StoreAmountDO;

import cc.likq.result.Result;

/**
 * <p>
 * 商店金额 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-05
 */
public interface StoreAmountService extends IService<StoreAmountDO> {

    /***
     * 更新金额
     * @param storeId
     * @param amount
     * @param orderNo
     * @param type
     * @return
     */
    Result updateAmount(Integer storeId, Long amount, String orderNo, StoreAmountEnum.Type type);

    /***
     * 查询商店金额信息
     * @param storeId
     * @return
     */
    Result getInfoByStore(Integer storeId);

    /***
     * 查询商店金额收支日志
     * @param pager
     * @return
     */
    Result getAmountLogByStore(Pager pager);

    /***
     * 查询商店可提取金额
     * @param storeId
     * @return
     */
    Long permitExtractAmount(Integer storeId);
}
