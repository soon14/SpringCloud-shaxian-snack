package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.UserRoleCodeEnum;
import com.isxxc.dao.UserShopProfilAuditLogDAO;
import com.isxxc.dao.UserShopProfilDAO;
import com.isxxc.domain.dto.UserShopProfilDTO;
import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.domain.entity.UserShopProfilAuditLogDO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.service.UserShopProfilService;
import com.isxxc.service.feign.user.UserRoleRelationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserShopProfilServiceImpl extends ServiceImpl<UserShopProfilDAO, UserShopProfilDO> implements UserShopProfilService {

    @Resource
    private UserShopProfilAuditLogDAO userShopProfilAuditLogDAO;

    @Resource
    private UserShopProfilDAO userShopProfilDAO;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    @Override
    public Result listPage(Page<UserShopProfilDTO> page, UserShopProfilDO userShopProfilDO) {
        EntityWrapper<UserShopProfilDO> entityWrapper = new EntityWrapper<>();
        if (userShopProfilDO.getAuditState() != null) {
            entityWrapper.eq("audit_state", userShopProfilDO.getAuditState());
        }
        List<UserShopProfilDO> userShopProfilDOList = userShopProfilDAO.selectPage(page, entityWrapper);
        List<UserShopProfilDTO> userShopProfilDTOList = new ArrayList<>(userShopProfilDOList.size());
        userShopProfilDOList.forEach(userShopProfilDODB -> userShopProfilDTOList.add(new UserShopProfilDTO(userShopProfilDODB)));
        page.setRecords(userShopProfilDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result audit(UserShopProfilAuditLogDO userShopProfilAuditLogDO) {
        UserShopProfilDO userShopProfilDO = selectById(userShopProfilAuditLogDO.getShopProfilId());
        if (userShopProfilAuditLogDO.getAuditState() == CommonStateEnum.AuditStateLog.PASSED.code) {
            userShopProfilDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);

            //权限关联
            UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();
            userRoleRelationDO.setUserId(userShopProfilDO.getUserId());
            userRoleRelationDO.setRoleCode(UserRoleCodeEnum.SHOP.code);
            userRoleRelationDO.setIsDisable(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            userRoleRelationService.save(userRoleRelationDO);
        } else {
            userShopProfilDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        updateById(userShopProfilDO);
        userShopProfilAuditLogDAO.insert(userShopProfilAuditLogDO);
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        UserShopProfilDO userShopProfilDO = selectById(id);
        return ResponseResult.success(new UserShopProfilDTO(userShopProfilDO));
    }
}
