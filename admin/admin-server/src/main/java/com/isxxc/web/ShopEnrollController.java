package com.isxxc.web;


import com.isxxc.service.ShopEnrollService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>店铺登记 前端控制器
 * 注: 业务现已取消
 * </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/shopEnroll")
public class ShopEnrollController {

    @Resource
    private ShopEnrollService shopEnrollService;

    /***
     * 查询所有登记
     * @param request
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result findAll(HttpServletRequest request) {
        return shopEnrollService.findAll();
    }

    /***
     * 根据ID查询
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Result findById(HttpServletRequest request, Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopEnrollService.findById(id);
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    public Result deleteById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopEnrollService.deleteById(id) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @RequestMapping("exportExcel")
    public Result exportExcel(HttpServletResponse response) {
        return shopEnrollService.exportExcel(response);
    }
}

