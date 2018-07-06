package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.InformationInfoDO;
import com.isxxc.service.InformationInfoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 新闻资讯 前端控制器
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@RestController
@RequestMapping("/informationInfo")
public class InformationInfoController {

    @Resource
    private InformationInfoService informationInfoService;

    /***
     * 查询列表
     * @param page
     * @param informationInfoDO
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page, InformationInfoDO informationInfoDO) {
        return informationInfoService.listPage(page, informationInfoDO);
    }

    /***
     * 增加阅读数
     * @param sourceName
     */
    @RequestMapping(value = "pageviewIncr", method = RequestMethod.POST)
    public Result pageviewIncr(String sourceName) {
        return informationInfoService.pageviewIncr(sourceName);
    }

    /***
     * 查询阅读数
     * @param sourceName
     * @return
     */
    @RequestMapping(value = "pageview", method = RequestMethod.GET)
    public int pageview(String sourceName) {
        return informationInfoService.pageview(sourceName);
    }

    /***
     * 猜你喜欢
     * @param size
     * @return
     */
    @RequestMapping(value = "mayLike", method = RequestMethod.GET)
    public Result mayLike(Integer size, Integer typeId, Integer category) {
        if (size == null || size <= 0) {
            return ResponseResult.paramNotNull("获取数据不能为空或小于等于0");
        }
        return informationInfoService.mayLike(size, typeId, category);
    }
}
