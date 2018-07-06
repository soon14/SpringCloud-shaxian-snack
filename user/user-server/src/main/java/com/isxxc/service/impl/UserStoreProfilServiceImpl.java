package com.isxxc.service.impl;

import com.isxxc.domain.entity.UserStoreProfilDO;
import com.isxxc.dao.UserStoreProfilDAO;
import com.isxxc.service.UserStoreProfilService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 原料供应商档案 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserStoreProfilServiceImpl extends ServiceImpl<UserStoreProfilDAO, UserStoreProfilDO> implements UserStoreProfilService {
	
}
