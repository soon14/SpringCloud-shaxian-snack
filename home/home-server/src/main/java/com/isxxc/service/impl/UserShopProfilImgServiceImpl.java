package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserShopProfilImgDAO;
import com.isxxc.domain.entity.UserShopProfilImgDO;
import com.isxxc.service.UserShopProfilImgService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserShopProfilImgServiceImpl extends ServiceImpl<UserShopProfilImgDAO, UserShopProfilImgDO> implements UserShopProfilImgService {
	
}
