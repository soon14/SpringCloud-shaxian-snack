package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.ProductFreightTemplateItemDTO;
import com.isxxc.domain.entity.ProductFreightTemplateItemDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 运费模版项  Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
@Repository
public interface ProductFreightTemplateItemDAO extends BaseMapper<ProductFreightTemplateItemDO> {

    /***
     * 根据运费模版查询列表
     * @param freightTemplateId
     * @return
     */
    List<ProductFreightTemplateItemDTO> selectByFreightTemplateId(Integer freightTemplateId);
}