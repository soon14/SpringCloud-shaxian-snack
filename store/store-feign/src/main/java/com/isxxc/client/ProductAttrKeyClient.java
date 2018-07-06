package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductAttrKeyDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.likq.result.Result;

/**
 * <p>
 * SKU属性名称 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/productAttrKeyClient")
public interface ProductAttrKeyClient {

    /***
     * 添加
     * @param productAttrKeyDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(ProductAttrKeyDO productAttrKeyDO);

    /***
     * 查询列表,带分页
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPager(Pager pager);
}
