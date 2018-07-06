package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.service.feign.store.ProductInfoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员购物车 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;


    /***
     * 检索商品列表
     * @param productSearchDTO
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Result search(Pager pager, ProductSearchDTO productSearchDTO) {
        productSearchDTO.setPager(pager);
        return productInfoService.search(productSearchDTO);
    }

    /***
     * 获取商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("商品ID不能为空");
        }
        return productInfoService.getInfoById(id);
    }
}
