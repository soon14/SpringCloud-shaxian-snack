package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.AdSpaceDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * 广告位管理
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
@RequestMapping("adSpaceClient")
public interface AdSpaceClient {

    /***
     * 添加广告位
     * @param adSpaceDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(AdSpaceDO adSpaceDO);

    /***
     * 查询广告位,带分页
     * @param page
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Page page);

    /***
     * 查询广告位列表
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    Result list();

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    Result delById(@RequestParam("id") Integer id);

    /***
     * 更新内容
     * @param adSpaceDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(AdSpaceDO adSpaceDO);
}
