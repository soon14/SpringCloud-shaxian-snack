package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.AuthAccountDAO;
import com.isxxc.domain.entity.AuthAccountDO;
import com.isxxc.service.AuthAccountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会员授权对接用户信息 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountDAO, AuthAccountDO> implements AuthAccountService {

}
