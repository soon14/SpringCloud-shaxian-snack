package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductAttrInfoDTO;
import com.isxxc.domain.entity.ProductSkuAttrRelationDO;

import java.util.List;

/**
 * <p>
 * SKU与属性关联 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public interface ProductSkuAttrRelationService extends IService<ProductSkuAttrRelationDO> {

    /***
     * 查询属性列表
     * @param skuId
     * @return
     */
    List<ProductAttrInfoDTO> selectDTOBySkuId(Integer skuId, Integer isDeleted);

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    ProductAttrInfoDTO selectDTOById(Integer id);
}
