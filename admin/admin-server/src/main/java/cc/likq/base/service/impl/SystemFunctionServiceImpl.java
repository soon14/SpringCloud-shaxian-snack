package cc.likq.base.service.impl;

import cc.likq.base.domain.entity.SystemAdminRoleRelationDO;
import cc.likq.base.domain.entity.SystemFunctionRoleRelationDO;
import cc.likq.base.service.SystemAdminRoleRelationService;
import cc.likq.base.service.SystemFunctionRoleRelationService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cc.likq.base.dao.SystemFunctionDAO;
import cc.likq.base.domain.dto.SystemFunctionDTO;
import cc.likq.base.domain.entity.SystemFunctionDO;
import cc.likq.base.service.SystemFunctionService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.MyBeanUtils;

import javax.annotation.Resource;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemFunctionServiceImpl extends ServiceImpl<SystemFunctionDAO, SystemFunctionDO> implements SystemFunctionService {

    @Resource
    private SystemAdminRoleRelationService systemAdminRoleRelationService;

    @Resource
    private SystemFunctionRoleRelationService systemFunctionRoleRelationService;


    @Override
    public Result save(SystemFunctionDO systemFunctionDO) {
        return insert(systemFunctionDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result remove(Integer id) {
        return deleteById(id) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(SystemFunctionDO systemFunctionDO) {
        SystemFunctionDO systemFunctionDODB = systemFunctionDO.selectById(systemFunctionDO.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemFunctionDO, systemFunctionDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(systemFunctionDODB) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listTree() {
        EntityWrapper<SystemFunctionDO> entityWrapper = new EntityWrapper<>();
        List<SystemFunctionDO> systemFunctionDOList = selectList(entityWrapper);
        systemFunctionDOList.sort(Comparator.comparing(SystemFunctionDO::getParentId).thenComparing(SystemFunctionDO::getLevel));
        List<SystemFunctionDTO> systemFunctionParents = new ArrayList<>();
        systemFunctionDOList.stream().filter(systemFunction -> systemFunction.getParentId() == 0).forEach(systemFunctionDO -> systemFunctionParents.add(new SystemFunctionDTO(systemFunctionDO)));
        systemFunctionParents.forEach(systemFunctionDTO -> sortTree(systemFunctionDTO, systemFunctionDOList));
        return ResponseResult.success(systemFunctionParents);
    }

    /***
     * 递归构建树
     * @param currSystemFunctionDTO 父对像
     * @param systemFunctionDOList 数据列表
     */
    private void sortTree(SystemFunctionDTO currSystemFunctionDTO, List<SystemFunctionDO> systemFunctionDOList) {
        for (SystemFunctionDO systemFunction : systemFunctionDOList) {
            SystemFunctionDTO systemFunctionDTO = new SystemFunctionDTO(systemFunction);
            if (currSystemFunctionDTO.getId().equals(systemFunctionDTO.getParentId())) {
                if (currSystemFunctionDTO.getChildList() == null) {
                    currSystemFunctionDTO.setChildList(new ArrayList<SystemFunctionDTO>() {{
                        add(systemFunctionDTO);
                    }});
                } else {
                    currSystemFunctionDTO.getChildList().add(systemFunctionDTO);
                }
                sortTree(systemFunctionDTO, systemFunctionDOList);
            }
        }
    }

    /**
     * 通过所有子节点的parent_id归类集合
     * @param list
     * @return
     */
    private List<List<SystemFunctionDTO>> groupByParentId(List<SystemFunctionDO> list) {

        List<List<SystemFunctionDTO>> groupByList = new ArrayList<>();
        for (SystemFunctionDO entity : list) {
            boolean isContain = false;
            List<SystemFunctionDTO> childList = new ArrayList<>();
            for (SystemFunctionDO systemFunctionDO : list) {
                if (entity.getParentId() == systemFunctionDO.getParentId()) {
                    childList.add(new SystemFunctionDTO(systemFunctionDO));
                }
            }
            for (List<SystemFunctionDTO> dtoList : groupByList) {
                if (dtoList.get(0).getParentId() == entity.getParentId()) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain && !"-".equals(entity.getRouteUrl())) {
                groupByList.add(childList);
            }
        }
        return groupByList;
    }


    /**
     * 递归查询出该用户的权限的父级节点
     * @param groupByParentId
     * @param returnList
     */
    private void getParentsList(List<List<SystemFunctionDTO>> groupByParentId, List<SystemFunctionDTO> returnList) {
        for (List<SystemFunctionDTO> list : groupByParentId) {
            EntityWrapper<SystemFunctionDO> wrapper = new EntityWrapper<>();
            wrapper.eq("id", list.get(0).getParentId());
            SystemFunctionDTO entity = new SystemFunctionDTO(selectOne(wrapper));
            if (entity != null && entity.getParentId() == 0 ) {
                entity.setChildList(list);
                returnList.add(entity);
            } else {
                List<SystemFunctionDTO> third = new ArrayList<>();
                entity.setChildList(list);
                third.add(entity);
                List<List<SystemFunctionDTO>> lists = new ArrayList<>();
                lists.add(third);
                getParentsList(lists, returnList);
            }
        }
    }


    /**
     * 加载用户权限
     * @param adminId
     * @param token
     * @return
     */
    @Override
    public Result getFunction(String adminId, String token) {

        //通过adminId查询出
        EntityWrapper<SystemAdminRoleRelationDO> wrapper = new EntityWrapper<>();
        wrapper.eq("admin_Id", adminId);
        SystemAdminRoleRelationDO adminRoleRelationDO = systemAdminRoleRelationService.selectOne(wrapper);

        //通过roleId查询出该用户的function的集合
        EntityWrapper<SystemFunctionRoleRelationDO> wrapper1 = new EntityWrapper<>();
        wrapper.eq("role_id", adminRoleRelationDO.getRoleId());
        //查询出当前角色的所有的功能
        List<SystemFunctionRoleRelationDO> list = systemFunctionRoleRelationService.selectList(wrapper1);

        List<Integer> ids = new ArrayList<>();
        for (SystemFunctionRoleRelationDO systemFunctionRoleRelationDO : list) {
            ids.add(systemFunctionRoleRelationDO.getFunctionId());
        }
        EntityWrapper<SystemFunctionDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("id", ids);
        //得到所有的权限
        List<SystemFunctionDO> all = selectList(entityWrapper);

        List<List<SystemFunctionDTO>> groupByParentId = groupByParentId(all);

        List<SystemFunctionDTO> returnList = new ArrayList<>();

        getParentsList(groupByParentId, returnList);

        returnList.sort(Comparator.comparing(SystemFunctionDTO::getParentId).thenComparing(SystemFunctionDTO::getLevel));

        return ResponseResult.success(returnList);
    }

}
