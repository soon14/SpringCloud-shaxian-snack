package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductBrandDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 产品品牌 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/productBrandClient")
public interface ProductBrandClient {

    /***
     * 添加品牌信息
     * @param productBrand
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(ProductBrandDO productBrand);

    /***
     * 更新信息
     * @param productBrand
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(ProductBrandDO productBrand);

    /***
     * 查询列表,带分页
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager page);

    /***
     * 查询列表
     * @param productBrand
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result list(ProductBrandDO productBrand);

    /***
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    Result delById(@RequestParam("id") Integer id);
}
