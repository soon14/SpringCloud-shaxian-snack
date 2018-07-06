package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserAddressDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员收货地址 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@Repository
public interface UserAddressDAO extends BaseMapper<UserAddressDO> {

    /***
     *  取消默认
     * @param userId
     * @param defaultCode
     */
    void cancelDefault(@Param("userId") Integer userId, @Param("defaultCode") Integer defaultCode);
}