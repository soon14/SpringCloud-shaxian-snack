package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserShopProfilDAO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.service.UserShopProfilService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserShopProfilServiceImpl extends ServiceImpl<UserShopProfilDAO, UserShopProfilDO> implements UserShopProfilService {

    @Override
    public Result save(UserShopProfilDO userShopProfilDO) {
        insert(userShopProfilDO);
        return ResponseResult.success();
    }
}
