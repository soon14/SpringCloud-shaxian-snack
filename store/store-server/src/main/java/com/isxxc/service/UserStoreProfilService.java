package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.UserStoreProfilDO;

import java.util.List;

/**
 * <p>
 * 原料供应商档案 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-31
 */
public interface UserStoreProfilService extends IService<UserStoreProfilDO> {

    /***
     * 根据区域Code查询商店ID
     * @param cityCode
     * @return
     */
    List<Integer> selectIdByDistrict(Integer provinceCode, Integer cityCode, Integer areaCode);

    /***
     * 查询商店信息
     * @param id
     * @return
     */
    UserStoreProfilDTO selectDTOById(Integer id);
}
