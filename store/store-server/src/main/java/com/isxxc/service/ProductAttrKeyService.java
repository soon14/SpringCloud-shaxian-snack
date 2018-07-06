package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductAttrKeyDO;

import cc.likq.result.Result;

/**
 * <p>
 * 商品属性名称 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public interface ProductAttrKeyService extends IService<ProductAttrKeyDO> {

    /***
     * 添加属性
     * @param productAttrKeyDO
     * @return
     */
    Result save(ProductAttrKeyDO productAttrKeyDO);

    /***
     *查询列表,带分页
     * @param pager
     * @return
     */
    Result listPager(Pager pager);
}
