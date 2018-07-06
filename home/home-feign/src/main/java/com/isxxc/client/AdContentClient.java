package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.AdContentDTO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2017/12/30
 */
@RequestMapping("adContentClient")
public interface AdContentClient {

    /***
     *添加广告内容
     * @param adContentDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(AdContentDTO adContentDTO);

    /***
     *查询列表,带分页
     * @param page
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Page page);

    /***
     * 更新内容
     * @param adContentDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(AdContentDTO adContentDTO);

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    Result delById(@RequestParam("id") Integer id);

    /***
     * 广告发布
     * @param id
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    Result publish(@RequestParam("id") Integer id);

    /***
     * 取消发布
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelPublish", method = RequestMethod.POST)
    Result cancelPublish(@RequestParam("id") Integer id);
}
