package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductAttrKeyDO;
import com.isxxc.service.feign.store.ProductAttrKeyService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商品属性名称 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-16
 */
@RestController
@RequestMapping("/productAttrKey")
public class ProductAttrKeyController {

    @Resource
    private ProductAttrKeyService productAttrKeyService;

    /***
     * 添加属性名称
     * @param productAttrKeyDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(ProductAttrKeyDO productAttrKeyDO) {
        if (productAttrKeyDO.getCategoryId() == null) {
            return ResponseResult.paramNotNull("请选择分类");
        }
        if (StringUtils.isBlank(productAttrKeyDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        return productAttrKeyService.save(productAttrKeyDO);
    }

    /***
     * 查询列表,带分页
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer categoryId) {
        pager.putParam("categoryId", categoryId);
        return productAttrKeyService.listPager(pager);
    }
}
