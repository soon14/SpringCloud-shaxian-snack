package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.UserFunctionDTO;
import com.isxxc.domain.entity.UserFunctionDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-21
 */
@Repository
public interface UserFunctionDAO extends BaseMapper<UserFunctionDO> {

    /***
     * 查询所有菜单
     * @return
     */
    List<UserFunctionDTO> list();

    /***
     * 根据Code查询实体
     * @param code
     * @return
     */
    UserFunctionDTO selectByCode(String code);
}
