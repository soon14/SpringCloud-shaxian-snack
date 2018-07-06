package com.isxxc.client;

import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.InformationTypeDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * @author 泥水佬
 * @date 2018/1/15
 */
@RequestMapping("informationTypeClient")
public interface InformationTypeClient {

    /***
     * 添加
     * @param informationTypeDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(InformationTypeDO informationTypeDO);

    /**
     * 根据ID删除
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    Result delById(@RequestParam("id") Integer id);

    /***
     *更新信息
     * @param informationTypeDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(InformationTypeDO informationTypeDO);

    /***
     * 查询列表，带分页
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 查询列表
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    Result list(@RequestParam("category") Integer category);
}
