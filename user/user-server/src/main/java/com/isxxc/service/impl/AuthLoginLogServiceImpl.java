package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.AuthLoginLogDAO;
import com.isxxc.domain.entity.AuthLoginLogDO;
import com.isxxc.service.AuthLoginLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 授权登录日志 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthLoginLogServiceImpl extends ServiceImpl<AuthLoginLogDAO, AuthLoginLogDO> implements AuthLoginLogService {

}
