package com.isxxc.dao;

import com.isxxc.domain.entity.ShopTransferImgDO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@Repository
public interface ShopTransferImgDAO extends BaseMapper<ShopTransferImgDO> {

    List<ShopTransferImgDO> selectByTransferId(Integer id);
}