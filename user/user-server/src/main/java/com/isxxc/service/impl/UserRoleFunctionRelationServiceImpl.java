package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserRoleFunctionRelationDAO;
import com.isxxc.domain.entity.UserRoleFunctionRelationDO;
import com.isxxc.service.UserRoleFunctionRelationService;

import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleFunctionRelationServiceImpl extends ServiceImpl<UserRoleFunctionRelationDAO, UserRoleFunctionRelationDO> implements UserRoleFunctionRelationService {

    @Override
    public Result roleFunctionRelation(Integer roleId, String functionIds) {
        EntityWrapper<UserRoleFunctionRelationDO> entityWrapper = new EntityWrapper<>();
        List<UserRoleFunctionRelationDO> userRoleFunctionRelationDOList = new ArrayList<>(functionIds.length());
        entityWrapper.eq("role_id", roleId);
        List<UserRoleFunctionRelationDO> userRoleFunctionRelationDODBList = selectList(entityWrapper);
        for (String functionId : functionIds.split(",")) {
            userRoleFunctionRelationDOList.add(new UserRoleFunctionRelationDO(Integer.parseInt(functionId), roleId));
        }

        if (userRoleFunctionRelationDODBList != null && !userRoleFunctionRelationDODBList.isEmpty()) {
            if (!ListUtils.subtract(userRoleFunctionRelationDOList, userRoleFunctionRelationDODBList).isEmpty()) {
                insertBatch(ListUtils.subtract(userRoleFunctionRelationDOList, userRoleFunctionRelationDODBList));
            }
            userRoleFunctionRelationDODBList.removeAll(userRoleFunctionRelationDOList);
            List<Integer> deleteIdList = userRoleFunctionRelationDODBList.stream().map(UserRoleFunctionRelationDO::getId).collect(Collectors.toList());
            if (!deleteIdList.isEmpty()) {
                deleteBatchIds(deleteIdList);
            }
        } else {
            insertBatch(userRoleFunctionRelationDOList);
        }
        return ResponseResult.success();
    }
}
