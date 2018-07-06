package com.isxxc.web;


import com.isxxc.service.feign.common.AreaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    @Resource
    private AreaService areaService;

    /***
     * 查询所有省
     * @return
     */
    @RequestMapping(value = "provinceAll", method = RequestMethod.GET)
    public Result provinceAll() {
        return areaService.provinceAll();
    }

    /***
     * 根据父Code查询
     * @param parentCode
     * @return
     */
    @RequestMapping(value = "selectByParentCode", method = RequestMethod.GET)
    public Result selectByParentCode(Integer parentCode) {
        return areaService.selectByParentCode(parentCode);
    }
}
