package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.ProductAttrInfoDTO;
import com.isxxc.domain.entity.ProductSkuAttrRelationDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * SKU与属性关联 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Repository
public interface ProductSkuAttrRelationDAO extends BaseMapper<ProductSkuAttrRelationDO> {

    /***
     * 查询属性列表
     * @param skuId
     * @return
     */
    List<ProductAttrInfoDTO> selectDTOBySkuId(@Param("skuId") Integer skuId, @Param("isDeleted") Integer isDeleted);

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    ProductAttrInfoDTO selectDTOById(Integer id);
}