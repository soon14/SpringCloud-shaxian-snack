package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.ProductSpuImgDTO;
import com.isxxc.domain.entity.ProductSpuImgDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品图片 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Repository
public interface ProductSpuImgDAO extends BaseMapper<ProductSpuImgDO> {

    /***
     * 根据SPUID查询列表
     * @param spuId
     * @param isDeleted
     * @return
     */
    List<ProductSpuImgDTO> selectDTOBySpuId(@Param("spuId") Integer spuId, @Param("isDeleted") Integer isDeleted);

    /***
     * 查询主图
     * @param spuId
     * @return
     */
    String selectMainBySpuID(Integer spuId);
}