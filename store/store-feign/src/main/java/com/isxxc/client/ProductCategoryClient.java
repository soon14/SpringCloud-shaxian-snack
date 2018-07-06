package com.isxxc.client;


import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.domain.entity.ProductCategoryDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/productCategoryClient")
public interface ProductCategoryClient {


    /***
     * 添加分类
     * @param productCategoryDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(ProductCategoryDO productCategoryDO);

    /***
     * 查询分类,树结构
     * @param productCategoryDTO
     * @return
     */
    @RequestMapping(value = "listTree", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listTree(ProductCategoryDTO productCategoryDTO);

    /***
     *修改内容信息
     * @param productCategoryDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(ProductCategoryDO productCategoryDO);
}
