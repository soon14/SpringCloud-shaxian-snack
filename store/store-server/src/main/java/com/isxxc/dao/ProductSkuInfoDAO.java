package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.ProductSkuInfoDTO;
import com.isxxc.domain.entity.ProductSkuInfoDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品SKU信息 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Repository
public interface ProductSkuInfoDAO extends BaseMapper<ProductSkuInfoDO> {

    /***
     * 根据SPUID查询SKU
     * @param spuId
     * @return
     */
    List<ProductSkuInfoDTO> selectBySpuId(@Param("spuId") Integer spuId, @Param("isDeleted") Integer isDeleted);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    ProductSkuInfoDTO selectDTOById(Integer id);

    /***
     * 根据SPUID更新上下架状态
     * @param spuId
     * @param shelvesCode
     * @return
     */
    Integer updateShelvesBySpuId(@Param("spuId") Integer spuId, @Param("shelvesCode") int shelvesCode, @Param("isDeleted") int isDeleted);
}