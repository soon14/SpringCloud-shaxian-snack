package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductSpuImgDTO;
import com.isxxc.domain.entity.ProductSpuImgDO;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
public interface ProductSpuImgService extends IService<ProductSpuImgDO> {

    /***
     * 根据SPUID查询数据集
     * @param spuId
     * @return
     */
    List<ProductSpuImgDTO> selectDTOBySpuId(Integer spuId, Integer isDeleted);

    /***
     * 查询主图
     * @param spuId
     * @return
     */
    String selectMainBySpuID(Integer spuId);
}
