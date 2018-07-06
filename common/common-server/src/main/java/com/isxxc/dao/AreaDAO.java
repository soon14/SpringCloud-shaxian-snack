package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.AreaDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Repository
public interface AreaDAO extends BaseMapper<AreaDO> {

    /***
     * 查询所有省
     */
    List<AreaDO> selectByParentCode(Integer parentCode);
}