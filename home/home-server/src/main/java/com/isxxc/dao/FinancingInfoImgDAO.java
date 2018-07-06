package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.FinancingInfoImgDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@Repository
public interface FinancingInfoImgDAO extends BaseMapper<FinancingInfoImgDO> {

    /***
     * 查询图片
     * @param financingInfoId
     * @return
     */
    List<FinancingInfoImgDO> selectByFinancingInfoId(Integer financingInfoId);
}