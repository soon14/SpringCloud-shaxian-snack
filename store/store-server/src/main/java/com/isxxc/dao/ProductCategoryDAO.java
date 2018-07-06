package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.domain.entity.ProductCategoryDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@Repository
public interface ProductCategoryDAO extends BaseMapper<ProductCategoryDO> {

    /***
     * 查询结果列表
     */
    List<ProductCategoryDTO> selectDTOList(ProductCategoryDTO productCategoryDTO);

    /***
     * 查询所有子类ID
     * @param parentId
     * @return
     */
    List<Integer> selectChildId(Integer parentId);
}