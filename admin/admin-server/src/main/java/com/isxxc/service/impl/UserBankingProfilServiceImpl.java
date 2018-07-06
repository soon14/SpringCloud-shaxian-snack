package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.UserRoleCodeEnum;
import com.isxxc.dao.UserBankingProfilAuditLogDAO;
import com.isxxc.dao.UserBankingProfilDAO;
import com.isxxc.domain.dto.UserBankingProfilDTO;
import com.isxxc.domain.entity.UserBankingProfilAuditLogDO;
import com.isxxc.domain.entity.UserBankingProfilDO;
import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.service.UserBankingProfilService;
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
public class UserBankingProfilServiceImpl extends ServiceImpl<UserBankingProfilDAO, UserBankingProfilDO> implements UserBankingProfilService {

    @Resource
    private UserBankingProfilAuditLogDAO userBankingProfilAuditLogDAO;

    @Resource
    private UserBankingProfilDAO userBankingProfilDAO;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    @Override
    public Result list(Page<UserBankingProfilDTO> page, UserBankingProfilDO userBankingProfilDO) {
        EntityWrapper<UserBankingProfilDO> entityWrapper = new EntityWrapper<>();
        if (userBankingProfilDO.getAuditState() != null) {
            entityWrapper.eq("audit_state", userBankingProfilDO.getAuditState());
        }
        List<UserBankingProfilDO> userBankingProfilDOList = userBankingProfilDAO.selectPage(page, entityWrapper);
        List<UserBankingProfilDTO> userBankingProfilDTOList = new ArrayList<>(userBankingProfilDOList.size());
        userBankingProfilDOList.forEach(userBankingProfilDODB -> userBankingProfilDTOList.add(new UserBankingProfilDTO(userBankingProfilDODB)));
        page.setRecords(userBankingProfilDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result audit(UserBankingProfilAuditLogDO userBankingProfilAuditLogDO) {
        UserBankingProfilDO userBankingProfilDO = selectById(userBankingProfilAuditLogDO.getBankingProfilId());
        if (userBankingProfilAuditLogDO.getAuditState() == CommonStateEnum.AuditStateLog.PASSED.code) {
            userBankingProfilDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);

            //权限关联
            UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();
            userRoleRelationDO.setUserId(userBankingProfilDO.getUserId());
            userRoleRelationDO.setRoleCode(UserRoleCodeEnum.BANKING.code);
            userRoleRelationDO.setIsDisable(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            userRoleRelationService.save(userRoleRelationDO);
        } else {
            userBankingProfilDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        updateById(userBankingProfilDO);
        userBankingProfilAuditLogDAO.insert(userBankingProfilAuditLogDO);
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        return ResponseResult.success(new UserBankingProfilDTO(selectById(id)));
    }
}
