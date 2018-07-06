package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.AdSpaceDO;
import com.isxxc.service.feign.home.AdSpaceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 广告位置标识 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@RestController
@RequestMapping("/adSpace")
public class AdSpaceController {

    @Resource
    private AdSpaceService adSpaceService;

    /***
     * 添加广告位
     * @param adSpaceDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(AdSpaceDO adSpaceDO) {
        if (StringUtils.isBlank(adSpaceDO.getCode())) {
            return ResponseResult.paramNotNull("Code不能为空");
        }
        return adSpaceService.save(adSpaceDO);
    }

    /***
     * 查询广告位,带分页
     * @param page
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page) {
        return adSpaceService.listPage(page);
    }

    /***
     * 查询广告位列表
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list() {
        return adSpaceService.list();
    }

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    public Result delById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return adSpaceService.delById(id);
    }

    /***
     * 更新内容
     * @param adSpaceDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(AdSpaceDO adSpaceDO) {
        if (adSpaceDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(adSpaceDO.getCode())) {
            return ResponseResult.paramNotNull("Code不能为空");
        }
        return adSpaceService.updateInfo(adSpaceDO);
    }
}
