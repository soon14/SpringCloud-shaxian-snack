package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserRoleDAO;
import com.isxxc.domain.entity.UserRoleDO;
import com.isxxc.service.UserRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDAO, UserRoleDO> implements UserRoleService {

    @Override
    public Result save(UserRoleDO userRoleDO) {
        userRoleDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return insert(userRoleDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(UserRoleDO userRoleDO) {
        return updateById(userRoleDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Page<UserRoleDO> page) {
        EntityWrapper<UserRoleDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        page = selectPage(page, entityWrapper);
        return ResponseResult.success(page);
    }

    @Override
    public Result deleted(Integer id) {
        UserRoleDO userRoleDO = selectById(id);
        userRoleDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(userRoleDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

}
