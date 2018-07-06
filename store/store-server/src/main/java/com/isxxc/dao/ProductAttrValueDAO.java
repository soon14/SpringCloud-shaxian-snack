package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.ProductAttrKeyDO;
import com.isxxc.domain.entity.ProductAttrValueDO;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品属性值 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Repository
public interface ProductAttrValueDAO extends BaseMapper<ProductAttrValueDO> {

    /***
     * 根据名称获取属性
     * @param name
     * @return
     */
    ProductAttrValueDO selectByName(String name);
}