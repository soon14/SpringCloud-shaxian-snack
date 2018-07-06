package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.FinancingInfoDO;

import org.apache.ibatis.annotations.Param;
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
public interface FinancingInfoDAO extends BaseMapper<FinancingInfoDO> {

    /***
     * 查询ID列表
     * @param isDeleted
     * @param publishState
     * @return
     */
    List<Integer> selectIds(@Param("isDeleted") int isDeleted, @Param("publishState") int publishState, @Param("category") Integer category);
}