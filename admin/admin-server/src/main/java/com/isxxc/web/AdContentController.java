package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.AdContentDTO;
import com.isxxc.service.feign.home.AdContentService;

import org.springframework.web.bind.annotation.RequestBody;
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
     *添加广告内容
     * @param adContentDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody AdContentDTO adContentDTO) {
        if (adContentDTO.getAdSpaceId() == null) {
            return ResponseResult.paramNotNull("广告位ID不能为空");
        }
        return adContentService.save(adContentDTO);
    }

    /***
     *查询列表,带分页
     * @param page
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page) {
        return adContentService.listPage(page);
    }

    /***
     * 更新内容
     * @param adContentDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(@RequestBody AdContentDTO adContentDTO) {
        if (adContentDTO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (adContentDTO.getAdSpaceId() == null) {
            return ResponseResult.paramNotNull("广告位ID不能为空");
        }
        return adContentService.updateInfo(adContentDTO);
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
        return adContentService.delById(id);
    }

    /***
     * 发布
     * @param id
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public Result publish(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return adContentService.publish(id);

    }

    /***
     * 取消发布
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelPublish", method = RequestMethod.POST)
    public Result cancelPublish(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return adContentService.cancelPublish(id);
    }
}
