package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.UserRoleCodeEnum;
import com.isxxc.dao.UserSupportingAuditLogDAO;
import com.isxxc.dao.UserSupportingProfilDAO;
import com.isxxc.domain.dto.UserSupportingProfilDTO;
import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.domain.entity.UserSupportingAuditLogDO;
import com.isxxc.domain.entity.UserSupportingProfilDO;
import com.isxxc.service.UserSupportingProfilService;
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
public class UserSupportingProfilServiceImpl extends ServiceImpl<UserSupportingProfilDAO, UserSupportingProfilDO> implements UserSupportingProfilService {

    @Resource
    private UserSupportingAuditLogDAO userSupportingAuditLogDAO;

    @Resource
    private UserSupportingProfilDAO userSupportingProfilDAO;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    @Override
    public Result list(Page<UserSupportingProfilDTO> page, UserSupportingProfilDO userSupportingProfilDO) {
        EntityWrapper<UserSupportingProfilDO> entityWrapper = new EntityWrapper<>();
        if (userSupportingProfilDO.getAuditState() != null) {
            entityWrapper.eq("audit_state", userSupportingProfilDO.getAuditState());
        }
        List<UserSupportingProfilDO> userSupportingProfilDOList = userSupportingProfilDAO.selectPage(page, entityWrapper);
        List<UserSupportingProfilDTO> userSupportingProfilDTOList = new ArrayList<>(userSupportingProfilDOList.size());
        userSupportingProfilDOList.forEach(userSupportingProfilDODB -> userSupportingProfilDTOList.add(new UserSupportingProfilDTO(userSupportingProfilDODB)));
        page.setRecords(userSupportingProfilDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result audit(UserSupportingAuditLogDO userSupportingAuditLogDO) {
        UserSupportingProfilDO userSupportingProfilDO = selectById(userSupportingAuditLogDO.getSupportingProfilId());
        if (userSupportingAuditLogDO.getAuditState() == CommonStateEnum.AuditStateLog.PASSED.code) {
            userSupportingProfilDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);

            //权限关联
            UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();
            userRoleRelationDO.setUserId(userSupportingProfilDO.getUserId());
            userRoleRelationDO.setRoleCode(UserRoleCodeEnum.SUPPORTING.code);
            userRoleRelationDO.setIsDisable(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            userRoleRelationService.save(userRoleRelationDO);
        } else {
            userSupportingProfilDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        updateById(userSupportingProfilDO);
        userSupportingAuditLogDAO.insert(userSupportingAuditLogDO);
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        return ResponseResult.success(new UserSupportingProfilDTO(selectById(id)));
    }
}
