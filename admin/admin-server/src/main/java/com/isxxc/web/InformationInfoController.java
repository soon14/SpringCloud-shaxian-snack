package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.InformationInfoDTO;
import com.isxxc.domain.entity.InformationInfoDO;
import com.isxxc.service.InformationInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 新闻资讯管理 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/informationInfo")
public class InformationInfoController {

    @Resource
    private InformationInfoService informationInfoService;

    /***
     * 添加资讯
     * @param informationInfoDTO
     *  类别(0:首页—新闻,1:首页—公告,2:首页—文化服务商,3:首页—产品设计,4
     *  :小吃文化—美食品鉴,5:小吃文化—美食故事,6:小吃文化—舌尖旅行)
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody InformationInfoDTO informationInfoDTO) {
        if (informationInfoDTO.getCategory() == null) {
            return ResponseResult.paramNotNull("请选择发布位置");
        }
        if (informationInfoDTO.getTypeId() == null) {
            return ResponseResult.paramNotNull("请选择资讯类别");
        }
        if (StringUtils.isBlank(informationInfoDTO.getTitle())) {
            return ResponseResult.paramNotNull("标题不能为空");
        }
        if (informationInfoDTO.getCategory() != 1 && StringUtils.isBlank(informationInfoDTO.getAuthor())) {
            return ResponseResult.paramNotNull("作者不能为空");
        }
        if ((informationInfoDTO.getCategory() == 0 || informationInfoDTO.getCategory() == 5 || informationInfoDTO.getCategory() == 6)
                && StringUtils.isBlank(informationInfoDTO.getSubtitle())) {
            return ResponseResult.paramNotNull("副标题不能为空");
        }
        if (StringUtils.isBlank(informationInfoDTO.getContent())) {
            return ResponseResult.paramNotNull("内容不能为空");
        }
        if (informationInfoDTO.getCoverImgList() == null || informationInfoDTO.getContent().isEmpty()) {
            return ResponseResult.paramNotNull("文章封面图片不能为空");
        }
        return informationInfoService.save(informationInfoDTO);
    }

    /***
     * 获取信息详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return informationInfoService.getInfoById(id);
    }

    /***
     * 更新信息
     * @param informationInfoDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(@RequestBody InformationInfoDTO informationInfoDTO) {
        if (informationInfoDTO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (informationInfoDTO.getCategory() == null) {
            return ResponseResult.paramNotNull("请选择发布位置");
        }
        if (informationInfoDTO.getTypeId() == null) {
            return ResponseResult.paramNotNull("请选择资讯类别");
        }
        if (StringUtils.isBlank(informationInfoDTO.getTitle())) {
            return ResponseResult.paramNotNull("标题不能为空");
        }
        if (informationInfoDTO.getTypeId() != 1 && StringUtils.isBlank(informationInfoDTO.getAuthor())) {
            return ResponseResult.paramNotNull("作者不能为空");
        }
        if ((informationInfoDTO.getTypeId() == 0 || informationInfoDTO.getTypeId() == 5 || informationInfoDTO.getTypeId() == 6)
                && StringUtils.isBlank(informationInfoDTO.getSubtitle())) {
            return ResponseResult.paramNotNull("副标题不能为空");
        }
        if (StringUtils.isBlank(informationInfoDTO.getContent())) {
            return ResponseResult.paramNotNull("内容不能为空");
        }
        if (informationInfoDTO.getCoverImgList() == null || informationInfoDTO.getContent().isEmpty()) {
            return ResponseResult.paramNotNull("文章封面图片不能为空");
        }
        return informationInfoService.updateInfo(informationInfoDTO);
    }

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
     * 发布
     * @param id
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public Result publish(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return informationInfoService.publish(id);

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
        return informationInfoService.cancelPublish(id);
    }

}
