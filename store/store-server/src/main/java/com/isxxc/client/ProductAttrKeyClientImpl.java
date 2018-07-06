package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductAttrKeyDO;
import com.isxxc.service.ProductAttrKeyService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * SKU属性名称 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class ProductAttrKeyClientImpl implements ProductAttrKeyClient {

    @Resource
    private ProductAttrKeyService productAttrKeyService;

    @Override
    public Result save(@RequestBody ProductAttrKeyDO productAttrKeyDO) {
        return productAttrKeyService.save(productAttrKeyDO);
    }

    @Override
    public Result listPager(@RequestBody Pager pager) {
        return productAttrKeyService.listPager(pager);
    }
}
