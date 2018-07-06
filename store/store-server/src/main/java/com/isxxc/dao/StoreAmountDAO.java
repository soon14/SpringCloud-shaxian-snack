package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.StoreAmountDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商店金额 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-05
 */
@Repository
public interface StoreAmountDAO extends BaseMapper<StoreAmountDO> {

    /***
     * 查询可提现的金额
     * @param storeId
     * @param endTime
     * @return
     */
    Long permitExtractAmount(@Param("storeId") Integer storeId, @Param("endTime") String endTime);
}