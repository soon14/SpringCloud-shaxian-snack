package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.service.ProductInfoService;
import com.isxxc.service.ProductSkuInfoService;

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
public class ProductInfoClientImpl implements ProductInfoClient {

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private ProductSkuInfoService productSkuInfoService;

    @Override
    public Result save(@RequestBody ProductInfoDTO productInfoDTO) {
        return productInfoService.save(productInfoDTO);
    }

    @Override
    public Result updateInfo(@RequestBody ProductInfoDTO productInfoDTO) {
        return productInfoService.updateInfo(productInfoDTO);
    }

    @Override
    public Result getInfoById(Integer id) {
        return productInfoService.getInfoById(id);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return productInfoService.listPage(pager);
    }

    @Override
    public Result search(@RequestBody ProductSearchDTO productSearchDTO) {
        return productInfoService.search(productSearchDTO);
    }

    @Override
    public Result getInfoByIdAndStoreId(Integer id, Integer storeId) {
        return productInfoService.getInfoByIdAndStoreId(id, storeId);
    }

    @Override
    public Result onShelves(Integer id, Integer storeId) {
        return productInfoService.onShelves(id, storeId);
    }

    @Override
    public Result offShelves(Integer id, Integer storeId) {
        return productInfoService.offShelves(id, storeId);
    }
}
