package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserAddressDAO;
import com.isxxc.domain.entity.UserAddressDO;
import com.isxxc.service.UserAddressService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 会员收货地址 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAddressServiceImpl extends ServiceImpl<UserAddressDAO, UserAddressDO> implements UserAddressService {

    @Resource
    private UserAddressDAO userAddressDAO;

    @Override
    public Result save(UserAddressDO userAddressDO) {
        if (userAddressDO.getIsDefault() != null && userAddressDO.getIsDefault() == CommonStateEnum.IsDefault.YES.code) {
            userAddressDAO.cancelDefault(userAddressDO.getUserId(), CommonStateEnum.IsDefault.NO.code);
        } else {
            userAddressDO.setIsDefault(CommonStateEnum.IsDefault.NO.code);
        }
        return insert(userAddressDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(UserAddressDO userAddressDO) {
        if (userAddressDO.getIsDefault() != null && userAddressDO.getIsDefault() == CommonStateEnum.IsDefault.YES.code) {
            userAddressDAO.cancelDefault(userAddressDO.getUserId(), CommonStateEnum.IsDefault.NO.code);
        } else {
            userAddressDO.setIsDefault(CommonStateEnum.IsDefault.NO.code);
        }
        return updateById(userAddressDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result list(Integer userId) {
        EntityWrapper<UserAddressDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", userId);
        return ResponseResult.success(selectList(entityWrapper));
    }

    @Override
    public Result getInfoByDefault(Integer userId) {
        EntityWrapper<UserAddressDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_default", CommonStateEnum.IsDefault.YES.code);
        entityWrapper.eq("user_id", userId);
        return ResponseResult.success(selectOne(entityWrapper));
    }

    @Override
    public Result deleteByUserId(Integer userId, Integer id) {
        EntityWrapper<UserAddressDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        delete(entityWrapper);
        return ResponseResult.success();
    }
}
