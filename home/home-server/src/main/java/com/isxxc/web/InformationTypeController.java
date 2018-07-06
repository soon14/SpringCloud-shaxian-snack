package com.isxxc.web;


import com.isxxc.service.InformationTypeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 *新闻资讯管理 前端控制器
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@RestController
@RequestMapping("/informationType")
public class InformationTypeController {

    @Resource
    private InformationTypeService informationTypeService;

    /***
     * 查询列表
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Integer category) {
        return informationTypeService.list(category);
    }
}
