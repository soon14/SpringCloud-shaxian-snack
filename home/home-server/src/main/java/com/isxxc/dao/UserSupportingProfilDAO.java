package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserSupportingProfilDO;

import org.springframework.stereotype.Repository;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Repository
public interface UserSupportingProfilDAO extends BaseMapper<UserSupportingProfilDO> {

    /***
     *根据用户ID查询服务商信息
     * @param userId
     * @return
     */
    UserSupportingProfilDO selectByUserId(Integer userId);
}