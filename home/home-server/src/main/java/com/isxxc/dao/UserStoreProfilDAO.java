package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserStoreProfilDO;

import org.springframework.stereotype.Repository;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Repository
public interface UserStoreProfilDAO extends BaseMapper<UserStoreProfilDO> {

    /**
     * 保存会员档案
     */
    UserStoreProfilDO selectByUserId(Integer userId);
}