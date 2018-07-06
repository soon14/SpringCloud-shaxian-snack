package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.domain.entity.ProductSpuDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品SPU信息 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Repository
public interface ProductSpuDAO extends BaseMapper<ProductSpuDO> {

    /***
     * 查询SPU信息
     * @param id
     * @return
     */
    ProductInfoDTO selectDTOById(@Param("id") Integer id);

    /***
     * 查询列表
     * @param page
     * @param productInfoDTO
     * @return
     */
    List<ProductInfoDTO> selectDTOList(Page page, @Param("spu") ProductInfoDTO productInfoDTO);

    /***
     * 查询列表
     * @param productSearchDTO
     * @return
     */
    List<ProductSpuDO> selectDOList(Page page, @Param("search") ProductSearchDTO productSearchDTO, @Param("isShelves") Integer isShelves, @Param("isDeleted") Integer isDeleted);

    /***
     * 更新上下架状态
     * @param id
     * @param storeId
     * @param shelvesCode
     * @return
     */
    Integer updateShelves(@Param("id") Integer id, @Param("storeId") Integer storeId, @Param("shelvesCode") int shelvesCode);
}