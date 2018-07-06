package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserStoreProfilDAO;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.UserStoreProfilDO;
import com.isxxc.service.UserStoreProfilService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 原料供应商档案 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserStoreProfilServiceImpl extends ServiceImpl<UserStoreProfilDAO, UserStoreProfilDO> implements UserStoreProfilService {

    @Resource
    private UserStoreProfilDAO userStoreProfilDAO;

    @Override
    public List<Integer> selectIdByDistrict(Integer provinceCode, Integer cityCode, Integer areaCode) {
        return userStoreProfilDAO.selectIdByDistrict(provinceCode, cityCode, areaCode, CommonStateEnum.AuditState.PASSED.code);
    }

    @Override
    public UserStoreProfilDTO selectDTOById(Integer id) {
        return userStoreProfilDAO.selectDTOById(id);
    }
}
