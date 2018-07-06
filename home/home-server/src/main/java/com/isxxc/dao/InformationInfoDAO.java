package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.InformationInfoDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@Repository
public interface InformationInfoDAO extends BaseMapper<InformationInfoDO> {

    /***
     * 查询ID列表
     * @param isDeleted
     * @param publishState
     * @return
     */
    List<Integer> selectIds(@Param("isDeleted") int isDeleted, @Param("publishState") int publishState, @Param("typeId") Integer typeId, @Param("category") Integer category);
}