package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.ProductInformationDTO;
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
 * @author yk
 * @since 2018-05-17
 */
@Repository
public interface ProductSpuInfoDAO extends BaseMapper<ProductSpuDO> {

    /***
     * 查询SPU信息
     * @param id
     * @return
     */
    ProductInformationDTO selectDTOById(@Param("id") Integer id);

    /***
     * 查询列表
     * @param page
     * @param productInformationDTO
     * @return
     */
    List<ProductInformationDTO> selectDTOList(Page page, @Param("spu") ProductInformationDTO productInformationDTO);

    /***
     * 查询列表
     * @param productSearchDTO
     * @return
     */
    List<ProductSpuDO> selectDOList(Page page, @Param("search") ProductSearchDTO productSearchDTO, @Param("isShelves") Integer isShelves, @Param("isDeleted") Integer isDeleted);


}