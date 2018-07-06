package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserBankingProfilDO;

import org.springframework.stereotype.Repository;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Repository
public interface UserBankingProfilDAO extends BaseMapper<UserBankingProfilDO> {
    /***
     *根据用户ID档案
     * @param userId
     * @return
     */
    UserBankingProfilDO selectByUserId(Integer userId);
}