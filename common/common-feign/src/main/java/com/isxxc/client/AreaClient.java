package com.isxxc.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * 省市区
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
@RequestMapping("areaClient")
public interface AreaClient {

    /***
     * 获取所有省
     * @return
     */
    @RequestMapping(value = "provinceAll", method = RequestMethod.GET)
    Result provinceAll();

    /***
     * 根据父code查询
     * @param parentCode
     * @return
     */
    @RequestMapping(value = "selectByParentCode", method = RequestMethod.GET)
    Result selectByParentCode(@RequestParam("parentCode") Integer parentCode);
}
