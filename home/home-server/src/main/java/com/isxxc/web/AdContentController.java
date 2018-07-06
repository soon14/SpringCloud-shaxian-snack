package com.isxxc.web;


import com.isxxc.service.AdContentService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 广告内容 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@RestController
@RequestMapping("/adContent")
public class AdContentController {

    @Resource
    private AdContentService adContentService;

    /***
     * 根据Code查询内容
     * @return
     */
    @RequestMapping(value = "listByCode", method = RequestMethod.GET)
    public Result listByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseResult.paramNotNull("Code不能为空");
        }
        return adContentService.listByCode(code);
    }
}
