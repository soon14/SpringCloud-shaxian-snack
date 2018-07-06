package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductBrandDO;
import com.isxxc.service.ProductBrandService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 产品品牌 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class ProductBrandClientImpl implements ProductBrandClient {

    @Resource
    private ProductBrandService productBrandService;

    @Override
    public Result save(@RequestBody ProductBrandDO productBrand) {
        return productBrandService.save(productBrand);
    }

    @Override
    public Result updateInfo(@RequestBody ProductBrandDO productBrand) {
        return productBrandService.updateInfo(productBrand);
    }

    @Override
    public Result listPage(@RequestBody Pager page) {
        return productBrandService.listPage(page);
    }

    @Override
    public Result list(@RequestBody ProductBrandDO productBrand) {
        return productBrandService.list(productBrand);
    }

    @Override
    public Result delById(Integer id) {
        return productBrandService.delById(id);
    }
}
