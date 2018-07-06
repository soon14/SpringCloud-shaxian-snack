package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.domain.entity.ProductSpuDO;

import java.util.List;

/**
 * <p>
 * 商品SPU信息 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public interface ProductSpuService extends IService<ProductSpuDO> {

    /***
     * 查询SPU信息
     * @param spuId
     * @return
     */
    ProductInfoDTO selectDTOById(Integer spuId);

    /***
     * 查询列表
     * @param page
     * @param productInfoDTO
     * @return
     */
    List<ProductInfoDTO> selectDTOList(Page page, ProductInfoDTO productInfoDTO);

    /***
     *查询列表
     * @param productSearchDTO
     * @return
     */
    List<ProductSpuDO> selectDOList(Page page, ProductSearchDTO productSearchDTO, Integer isShelves, Integer isDeleted);

    /***
     * 更新商品上下架状态
     * @param id
     * @param storeId
     * @param shelvesCode
     * @return
     */
    Integer updateShelves(Integer id, Integer storeId, int shelvesCode);
}
