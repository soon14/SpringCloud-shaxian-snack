package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserRoleCountDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.dto.UserRoleDTO;
import com.isxxc.domain.entity.UserRoleDO;
import com.isxxc.service.UserRoleCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleCountServiceImpl extends ServiceImpl<UserRoleCountDAO, UserRoleDO> implements UserRoleCountService {

    @Resource
    private UserRoleCountDAO userRoleCountDAO;

    @Override
    public Result countUserShop(UserRoleDTO userRoleDTO) {
        Integer integer = userRoleCountDAO.countUserShop(userRoleDTO);
        return ResponseResult.success(integer);
    }

    @Override
    public Result countUserStore(UserRoleDTO userRoleDTO) {
        Integer integer = userRoleCountDAO.countUserStore(userRoleDTO);
        return ResponseResult.success(integer);
    }

    @Override
    public Result countUserSupporting(UserRoleDTO userRoleDTO) {
        Integer integer = userRoleCountDAO.countUserSupporting(userRoleDTO);
        return ResponseResult.success(integer);
    }

    @Override
    public Result countUserBanking(UserRoleDTO userRoleDTO) {
        Integer integer = userRoleCountDAO.countUserBanking(userRoleDTO);
        return ResponseResult.success(integer);
    }

    @Override
    public Pager listPage(Pager pager) {
        List<UserAccountOtherDTO> userAccountOtherDTOS = userRoleCountDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(userAccountOtherDTOS);
        return pager;
    }
}
