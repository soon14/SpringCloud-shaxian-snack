package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.AdContentDTO;
import com.isxxc.domain.entity.AdContentDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 广告内容 Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@Repository
public interface AdContentDAO extends BaseMapper<AdContentDO> {

    /***
     * 根据Code查询内容列表
     * @param code
     * @return
     */
    List<AdContentDTO> listByCode(@Param("code") String code, @Param("publishState") Integer publishState, @Param("isDeleted") Integer isDeleted);
}