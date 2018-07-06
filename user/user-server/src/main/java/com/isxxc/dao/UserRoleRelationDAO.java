package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.UserRoleRelationDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-14
 */
@Repository
public interface UserRoleRelationDAO extends BaseMapper<UserRoleRelationDO> {

    /***
     * 根据会员ID查询角色
     * @param userId
     * @return
     */
    List<UserRoleRelationDO> selectByUserId(Integer userId);

    /***
     * 根据会员ID查询角色
     * @param userId
     * @return
     */
    List<String> getCodesByUserId(@Param("userId") Integer userId, @Param("isDisable") int isDisable);
}