package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserStoreProfilDO;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * 原料供应商档案 Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@Repository
public interface UserStoreProfilDAO extends BaseMapper<UserStoreProfilDO> {

    /***
     * 根据会员ID查询商城ID
     * @param userId
     * @return
     */
    Integer getIdByUserId(Integer userId);
}