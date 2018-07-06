package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
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
     * 获取排队鉴权的URL
     * @return
     */
    List<String> getIgnoreUrl();
}
