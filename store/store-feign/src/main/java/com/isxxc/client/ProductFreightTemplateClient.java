package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductFreightTemplateDTO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 运费模版 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("productFreightTemplateClient")
public interface ProductFreightTemplateClient {

    /***
     * 添加运费模版
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(ProductFreightTemplateDTO productFreightTemplateDTO);

    /***
     * 查询列表,带分页
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 根据ID及店铺ID查询详情
     * @param id
     * @param storeId
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    Result getInfoByIdAndStoreId(@RequestParam("id") Integer id, @RequestParam("storeId") Integer storeId);

    /***
     * 删除运费模版
     * @param id
     * @param storeId
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.POST)
    Result delByIdAndStoreId(@RequestParam("id") Integer id, @RequestParam("storeId") Integer storeId);
}
