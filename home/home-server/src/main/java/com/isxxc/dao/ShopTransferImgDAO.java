package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.ShopTransferImgDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
@Repository
public interface ShopTransferImgDAO extends BaseMapper<ShopTransferImgDO> {

    /***
     * 根据
     * @param transferId
     * @return
     */
    List<ShopTransferImgDO> selectByTransferId(Integer transferId);
}