package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.OpenUserDO;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * 第三方登录信息 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-22
 */
@Repository
public interface OpenUserDAO extends BaseMapper<OpenUserDO> {

    /***
     * 根据帐号查询
     * @param openId
     * @return
     */
    OpenUserDO selectByOpenId(String openId);
}