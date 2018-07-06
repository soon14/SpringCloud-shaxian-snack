package com.isxxc.web;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.isxxc.service.feign.store.ProductInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     商品详细信息 前端控制器
 * </p>
 * @author  yk
 * @since  2018-05-17
 */
@RestController
@RequestMapping("/productInformation")
public class ProductInformationController {

    @Resource
    private ProductInformationService productInformationService;


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
        return productInformationService.getInfoById(id);
    }

}
