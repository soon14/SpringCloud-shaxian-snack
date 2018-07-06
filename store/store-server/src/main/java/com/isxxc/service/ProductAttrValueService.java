package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ProductAttrValueDO;

/**
 * <p>
 * 商品属性值 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public interface ProductAttrValueService extends IService<ProductAttrValueDO> {

    /***
     * 根据名称获取属性值
     * @param name
     * @return
     */
    ProductAttrValueDO selectByName(String name);
}
