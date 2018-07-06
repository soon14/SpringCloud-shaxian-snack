package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserAccountDAO;
import com.isxxc.dao.UserRoleRelationDAO;
import com.isxxc.domain.entity.UserRoleRelationDO;
import com.isxxc.service.UserRoleRelationService;

import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationDAO, UserRoleRelationDO> implements UserRoleRelationService {

    @Resource
    private UserRoleRelationDAO userRoleRelationDAO;

    @Resource
    private UserAccountDAO userAccountDAO;

    @Override
    public Result userRoleRelation(Integer userId, String roleCodes) {
        List<UserRoleRelationDO> UserRoleRelationDODBList = userRoleRelationDAO.selectByUserId(userId);
        String[] roleCodess = roleCodes.split(",");
        List<UserRoleRelationDO> userRoleRelationDOList = new ArrayList<>(roleCodess.length);
        for (String codes : roleCodess) {
            userRoleRelationDOList.add(new UserRoleRelationDO(userId, codes, CommonStateEnum.IsDeleted.NOT_DELETED.code));
        }
        if (UserRoleRelationDODBList != null && !UserRoleRelationDODBList.isEmpty()) {
            if (!ListUtils.subtract(userRoleRelationDOList, UserRoleRelationDODBList).isEmpty()) {
                insertBatch(ListUtils.subtract(userRoleRelationDOList, UserRoleRelationDODBList));
            }
            UserRoleRelationDODBList.removeAll(userRoleRelationDOList);
            List<Integer> deleteIdList = UserRoleRelationDODBList.stream().map(UserRoleRelationDO::getId).collect(Collectors.toList());
            if (!deleteIdList.isEmpty()) {
                deleteBatchIds(deleteIdList);
            }
        } else {
            insertBatch(userRoleRelationDOList);
        }

        return ResponseResult.success();
    }
}
