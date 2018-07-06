package cc.likq.base.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import cc.likq.base.dao.SystemFunctionRoleRelationDAO;
import cc.likq.base.domain.entity.SystemFunctionRoleRelationDO;
import cc.likq.base.service.SystemFunctionRoleRelationService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemFunctionRoleRelationServiceImpl extends ServiceImpl<SystemFunctionRoleRelationDAO, SystemFunctionRoleRelationDO> implements SystemFunctionRoleRelationService {

    @Override
    public Result roleFunctionRelation(Integer roleId, String functionIds) {
        List<SystemFunctionRoleRelationDO> systemFunctionRoleRelationDOList = new ArrayList<>();
        String[] functionIdss = functionIds.split(",");
        for (String functionId : functionIdss) {
            systemFunctionRoleRelationDOList.add(new SystemFunctionRoleRelationDO(Integer.parseInt(functionId), roleId));
        }
        return insertBatch(systemFunctionRoleRelationDOList) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
