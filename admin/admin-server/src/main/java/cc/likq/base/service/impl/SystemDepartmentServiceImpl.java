package cc.likq.base.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cc.likq.base.dao.SystemDepartmentDAO;
import cc.likq.base.domain.entity.SystemDepartmentDO;
import cc.likq.base.service.SystemDepartmentService;
import com.isxxc.constant.CommonStateEnum.IsDeleted;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.MyBeanUtils;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemDepartmentServiceImpl extends ServiceImpl<SystemDepartmentDAO, SystemDepartmentDO> implements SystemDepartmentService {

    @Resource
    private SystemDepartmentDAO systemDepartmentDAO;

    @Override
    public Result save(SystemDepartmentDO systemDepartmentDO) {
        systemDepartmentDO.setIsDeleted(IsDeleted.NOT_DELETED.code);
        return insert(systemDepartmentDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result remove(Integer id) {
        SystemDepartmentDO systemDepartmentDO = selectById(id);
        systemDepartmentDO.setIsDeleted(IsDeleted.HAVE_DELETED.code);
        return updateById(systemDepartmentDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Page<SystemDepartmentDO> page, String name, Integer parentId) {
        List<SystemDepartmentDO> systemDepartmentDOList = systemDepartmentDAO.list(page, name, parentId, IsDeleted.NOT_DELETED.code);
        page.setRecords(systemDepartmentDOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result list(String name, Integer parentId) {
        List<SystemDepartmentDO> systemDepartmentDOList = systemDepartmentDAO.list(name, parentId, IsDeleted.NOT_DELETED.code);
        return ResponseResult.success(systemDepartmentDOList);
    }

    @Override
    public Result updateInfo(SystemDepartmentDO systemDepartmentDO) {
        SystemDepartmentDO systemDepartmentDODB = systemDepartmentDO.selectById(systemDepartmentDO.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemDepartmentDO, systemDepartmentDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(systemDepartmentDODB) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
