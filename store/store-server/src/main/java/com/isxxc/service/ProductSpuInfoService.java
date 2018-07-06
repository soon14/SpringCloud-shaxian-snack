package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductInformationDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.domain.entity.ProductSpuDO;

import java.util.List;

/**
 * <p>
 * 商品SPU信息 服务类
 * </p>
 *
 * @author yk
 * @since 2018-05-17
 */
public interface ProductSpuInfoService extends IService<ProductSpuDO> {

    /***
     * 查询SPU信息
     * @param spuId
     * @return
     */
    ProductInformationDTO selectDTOById(Integer spuId);

    /***
     * 查询列表
     * @param page
     * @param productInformationDTO
     * @return
     */
    List<ProductInformationDTO> selectDTOList(Page page, ProductInformationDTO productInformationDTO);

    /***
     *查询列表
     * @param productSearchDTO
     * @return
     */
    List<ProductSpuDO> selectDOList(Page page, ProductSearchDTO productSearchDTO, Integer isShelves, Integer isDeleted);


}
