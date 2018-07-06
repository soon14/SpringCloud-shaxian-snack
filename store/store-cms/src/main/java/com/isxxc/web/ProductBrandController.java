package com.isxxc.web;


import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductBrandDO;
import com.isxxc.service.feign.store.ProductBrandService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.FileUtils;

/**
 * <p>
 * 产品品牌 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/productBrand")
public class ProductBrandController {

    @Resource
    private ProductBrandService productBrandService;

    /***
     * 添加品牌
     * @param productBrand
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(ProductBrandDO productBrand) {
        if (StringUtils.isBlank(productBrand.getName())) {
            return ResponseResult.paramNotNull("品牌名称不能为空");
        }
        return productBrandService.save(productBrand);
    }

    /***
     * 更新信息
     * @param productBrand
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(ProductBrandDO productBrand) {
        if (productBrand.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(productBrand.getName())) {
            return ResponseResult.paramNotNull("品牌名称不能为空");
        }
        return productBrandService.updateInfo(productBrand);
    }

    /***
     * 查询列表,带分页
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager page, Integer storeId) {
        page.putParam("storeId", storeId);
        return productBrandService.listPage(page);
    }

    /***
     * 查询列表
     * @param productBrand
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(ProductBrandDO productBrand) {
        return productBrandService.list(productBrand);
    }

    /***
     * 删除品牌
     * @param id
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    public Result delById(Integer id) {
        return productBrandService.delById(id);
    }
}
