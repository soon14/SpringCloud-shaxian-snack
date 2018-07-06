package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserAccountDO;

import org.springframework.stereotype.Repository;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
@Repository
public interface UserAccountDAO extends BaseMapper<UserAccountDO> {

    /***
     * 根据手机号查询信息
     */
    UserAccountDO selectByMobileNo(String mobileNo);
}