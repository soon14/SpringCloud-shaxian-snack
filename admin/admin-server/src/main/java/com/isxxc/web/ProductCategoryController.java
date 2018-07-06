package com.isxxc.web;


import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.domain.entity.ProductCategoryDO;
import com.isxxc.service.feign.store.ProductCategoryService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商品分类 前端控制器
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
     * 添加分类
     * @param productCategoryDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(ProductCategoryDO productCategoryDO) {
        if (productCategoryDO.getParentId() == null) {
            return ResponseResult.paramNotNull("父ID不能为空");
        }
        if (StringUtils.isBlank(productCategoryDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        return productCategoryService.save(productCategoryDO);
    }

    /***
     * 查询列表,树结构
     * @param productCategoryDTO
     * @return
     */
    @RequestMapping(value = "listTree", method = RequestMethod.GET)
    public Result listTree(ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.listTree(productCategoryDTO);
    }

    /***
     * 更新分类
     * @param productCategoryDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(ProductCategoryDO productCategoryDO) {
        if (productCategoryDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (productCategoryDO.getParentId() == null) {
            return ResponseResult.paramNotNull("父ID不能为空");
        }
        if (StringUtils.isBlank(productCategoryDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        return productCategoryService.updateInfo(productCategoryDO);
    }
}
