package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductFreightTemplateDTO;
import com.isxxc.service.ProductFreightTemplateService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
public class ProductFreightTemplateClientImpl implements ProductFreightTemplateClient {

    @Resource
    private ProductFreightTemplateService productFreightTemplateService;

    @Override
    public Result save(@RequestBody ProductFreightTemplateDTO productFreightTemplateDTO) {
        return productFreightTemplateService.save(productFreightTemplateDTO);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return productFreightTemplateService.listPage(pager);
    }

    @Override
    public Result getInfoByIdAndStoreId(Integer id, Integer storeId) {
        return productFreightTemplateService.getInfoByIdAndStoreId(id, storeId);
    }

    @Override
    public Result delByIdAndStoreId(Integer id, Integer storeId) {
        return productFreightTemplateService.delByIdAndStoreId(id, storeId);
    }
}
