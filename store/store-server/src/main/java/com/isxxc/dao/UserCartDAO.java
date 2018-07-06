package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.UserCartDTO;
import com.isxxc.domain.entity.UserCartDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 会员购物车 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@Repository
public interface UserCartDAO extends BaseMapper<UserCartDO> {

    /***
     * 根据会员查询购物车信息
     * @param userId
     * @return
     */
    List<UserCartDTO> selectDTOByUserId(Integer userId);

    /***
     * 批量查询
     * @param userId
     * @param idList
     * @return
     */
    int deleteByIdList(@Param("userId") Integer userId, @Param("idList") List<Integer> idList);
}