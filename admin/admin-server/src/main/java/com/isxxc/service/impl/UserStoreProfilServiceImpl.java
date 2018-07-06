package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.UserRoleCodeEnum;
import com.isxxc.dao.UserStoreProfilAuditLogDAO;
import com.isxxc.dao.UserStoreProfilDAO;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.StoreAmountDO;
import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.domain.entity.UserStoreProfilAuditLogDO;
import com.isxxc.domain.entity.UserStoreProfilDO;
import com.isxxc.service.UserStoreProfilService;
import com.isxxc.service.feign.store.StoreAmountService;
import com.isxxc.service.feign.user.UserRoleRelationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserStoreProfilServiceImpl extends ServiceImpl<UserStoreProfilDAO, UserStoreProfilDO> implements UserStoreProfilService {

    @Resource
    private UserStoreProfilAuditLogDAO userStoreProfilAuditLogDAO;

    @Resource
    private UserStoreProfilDAO userStoreProfilDAO;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    @Resource
    private StoreAmountService storeAmountService;

    @Override
    public Result list(Page<UserStoreProfilDTO> page, UserStoreProfilDO userStoreProfilDO) {
        EntityWrapper<UserStoreProfilDO> entityWrapper = new EntityWrapper<>();
        if (userStoreProfilDO.getAuditState() != null) {
            entityWrapper.eq("audit_state", userStoreProfilDO.getAuditState());
        }
        List<UserStoreProfilDO> userStoreProfilDOList = userStoreProfilDAO.selectPage(page, entityWrapper);
        List<UserStoreProfilDTO> userStoreProfilDTOS = new ArrayList<>(userStoreProfilDOList.size());
        userStoreProfilDOList.forEach(userStoreProfilDODB -> userStoreProfilDTOS.add(new UserStoreProfilDTO(userStoreProfilDODB)));
        page.setRecords(userStoreProfilDTOS);
        return ResponseResult.success(page);
    }

    @Override
    public Result audit(UserStoreProfilAuditLogDO userStoreProfilAuditLogDO) {
        UserStoreProfilDO userStoreProfilDO = selectById(userStoreProfilAuditLogDO.getStoreProfilId());
        if (userStoreProfilAuditLogDO.getAuditState() == CommonStateEnum.AuditStateLog.PASSED.code) {
            userStoreProfilDO.setAuditState(CommonStateEnum.AuditState.PASSED.code);

            //权限关联
            UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();
            userRoleRelationDO.setUserId(userStoreProfilDO.getUserId());
            userRoleRelationDO.setRoleCode(UserRoleCodeEnum.STORE.code);
            userRoleRelationDO.setIsDisable(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            userRoleRelationService.save(userRoleRelationDO);

            //初始化总金额信息
            StoreAmountDO storeAmountDO = new StoreAmountDO();
            storeAmountDO.setAmount(0L);
            storeAmountDO.setStoreId(userStoreProfilDO.getId());
            storeAmountDO.setGmtModified(new Date());
            storeAmountDO.setGmtCreate(new Date());
            Result result = storeAmountService.save(storeAmountDO);
            if (result.getCode() != ResponseResult.success().getCode()) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "初始化商店金额信息失败,请联系管理会员");
            }
        } else {
            userStoreProfilDO.setAuditState(CommonStateEnum.AuditState.REJECT.code);
        }
        updateById(userStoreProfilDO);
        userStoreProfilAuditLogDAO.insert(userStoreProfilAuditLogDO);
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        return ResponseResult.success(new UserStoreProfilDTO(selectById(id)));
    }


}
