package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.StoreExtractAmountLogDTO;
import com.isxxc.domain.entity.StoreExtractAmountLogDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商店提取金额日志 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@Repository
public interface StoreExtractAmountLogDAO extends BaseMapper<StoreExtractAmountLogDO> {

    /***
     * 查询商店提现中的金额
     * @param storeId
     * @return
     */
    Long extractProcessAmountByStoreId(Integer storeId);

    /***
     * 查询申请列表
     * @param pager
     * @return
     */
    List<StoreExtractAmountLogDTO> listPage(Pager pager, @Param("params") Map<String, Object> params);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    StoreExtractAmountLogDTO getInfoById(Integer id);
}