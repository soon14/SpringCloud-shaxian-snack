package com.isxxc.client;


import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.domain.entity.ProductCategoryDO;
import com.isxxc.service.ProductCategoryService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class ProductCategoryClientImpl implements ProductCategoryClient {

    @Resource
    private ProductCategoryService productCategoryService;

    @Override
    public Result save(@RequestBody ProductCategoryDO productCategoryDO) {
        return productCategoryService.save(productCategoryDO);
    }

    @Override
    public Result listTree(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.listTree(productCategoryDTO);
    }

    @Override
    public Result updateInfo(@RequestBody ProductCategoryDO productCategoryDO) {
        return productCategoryService.updateInfo(productCategoryDO);
    }
}
