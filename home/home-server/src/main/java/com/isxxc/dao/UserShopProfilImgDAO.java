package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserShopProfilImgDO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author likq
 * @since 2017-11-29
 */
public interface UserShopProfilImgDAO extends BaseMapper<UserShopProfilImgDO> {

    /***
     * 根据档案ID查询图片列表
     * @param shopProfilId
     * @return
     */
    List<UserShopProfilImgDO> selectByShopProfilId(Integer shopProfilId);
}