package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.UserStoreProfilDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 原料供应商档案 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-31
 */
@Repository
public interface UserStoreProfilDAO extends BaseMapper<UserStoreProfilDO> {

    /***
     * 根据城市Code查询商店ID
     * @param cityCode
     * @param auditState
     * @return
     */
    List<Integer> selectIdByDistrict(@Param("provinceCode") Integer provinceCode, @Param("cityCode") Integer cityCode, @Param("areaCode") Integer areaCode, @Param("auditState") int auditState);

    /***
     * 根据ID查询商店信息
     * @param id
     * @return
     */
    UserStoreProfilDTO selectDTOById(Integer id);

    /***
     * 根据会员ID查询商城ID
     * @param userId
     * @return
     */
    Integer getIdByUserId(Integer userId);
}