package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.InformationTypeDO;
import com.isxxc.service.feign.home.InformationTypeService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 新闻资讯类型 前端控制器
 * </p>
 *
 * @author likq
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/informationType")
public class InformationTypeController {

    @Resource
    private InformationTypeService informationTypeService;

    /***
     * 添加类型管理
     * @param informationTypeDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(InformationTypeDO informationTypeDO) {
        if (StringUtils.isBlank(informationTypeDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        return informationTypeService.save(informationTypeDO);
    }

    /***
     * 删除类型
     * @param id
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    public Result delById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return informationTypeService.delById(id);
    }

    /***
     * 更新信息
     * @param informationTypeDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(InformationTypeDO informationTypeDO) {
        if (informationTypeDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(informationTypeDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        return informationTypeService.updateInfo(informationTypeDO);
    }

    /***
     * 查询列表，带分页
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer category) {
        if (category != null) {
            pager.putParam("category", category);
        }
        return informationTypeService.listPage(pager);
    }

    /***
     * 查询列表
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(Integer category) {
        return informationTypeService.list(category);
    }

}
