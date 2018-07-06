package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.ExpressCompanyService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 快递管理前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-06
 */
@RestController
@RequestMapping("/expressCompany")
public class ExpressCompanyController {

    @Resource
    private ExpressCompanyService expressCompanyService;

    /***
     * 快递列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, String name) {
        if (StringUtils.isNotBlank(name)) {
            pager.putParam("name", name);
        }
        return expressCompanyService.listPage(pager);
    }
}
