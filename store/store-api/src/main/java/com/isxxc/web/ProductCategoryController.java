package com.isxxc.web;


import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.service.feign.store.ProductCategoryService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;

    /***
     * 查询分类,树结构
     * @return
     */
    @RequestMapping(value = "listTree", method = RequestMethod.GET)
    public Result listTree(ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.listTree(productCategoryDTO);
    }
}
