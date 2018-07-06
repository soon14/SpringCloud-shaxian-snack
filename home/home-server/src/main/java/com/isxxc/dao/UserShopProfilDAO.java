package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserShopProfilDO;

import org.springframework.stereotype.Repository;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Repository
public interface UserShopProfilDAO extends BaseMapper<UserShopProfilDO> {

    /***
     * 根据用户ID查询档案
     * @param userId
     * @return
     */
    UserShopProfilDO selectByUserId(Integer userId);
}