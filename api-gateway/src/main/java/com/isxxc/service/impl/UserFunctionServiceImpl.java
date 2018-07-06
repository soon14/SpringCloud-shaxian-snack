package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserFunctionDAO;
import com.isxxc.domain.entity.UserFunctionDO;
import com.isxxc.service.UserFunctionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserFunctionServiceImpl extends ServiceImpl<UserFunctionDAO, UserFunctionDO> implements UserFunctionService {

    @Resource
    private UserFunctionDAO userFunctionDAO;


    @Override
    public List<String> getIgnoreUrl() {
        return userFunctionDAO.getIgnoreUrl();
    }
}
