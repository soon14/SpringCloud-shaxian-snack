package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductSkuInfoDTO;
import com.isxxc.domain.entity.ProductSkuInfoDO;

import java.util.List;

import cc.likq.result.Result;

/**
 * <p>
 * 商品SKU信息 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public interface ProductSkuInfoService extends IService<ProductSkuInfoDO> {

    /***
     *根据SPU查询详情
     * @param spuId
     * @return
     */
    List<ProductSkuInfoDTO> selectBySpuId(Integer spuId, Integer isDeleted);

    /***
     * 根据ID查询SKU详情
     * @param id
     * @return
     */
    ProductSkuInfoDTO selectDTOById(Integer id);

    /***
     * 更新SKU商品上架状态
     * @param spuId
     * @param code
     * @return
     */
    Integer updateShelvesBySpuId(Integer spuId, int code);

    /***
     * 商品SKU上架
     * @param skuId
     * @param storeId
     * @return
     */
    Result onShelvesBySkuId(Integer skuId, Integer storeId);

    /***
     * 商品SKU下架
     * @param skuId
     * @param storeId
     * @return
     */
    Result offShelvesBySkuId(Integer skuId, Integer storeId);
}
