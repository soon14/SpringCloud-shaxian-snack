package cc.likq.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cc.likq.base.domain.entity.SystemDepartmentDO;
import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
public interface SystemDepartmentService extends IService<SystemDepartmentDO> {

    /***
     * 添加部门
     * @param systemDepartmentDO
     * @return
     */
    Result save(SystemDepartmentDO systemDepartmentDO);

    /***
     * 删除部门
     * @param id
     * @return
     */
    Result remove(Integer id);

    /***
     * 查询部门带分页
     * @return
     */
    Result listPage(Page<SystemDepartmentDO> page, String name, Integer parentId);

    /***
     *查询部门
     * @param name
     * @return
     */
    Result list(String name, Integer parentId);

    /***
     * 更新部门信息
     * @param systemDepartmentDO
     * @return
     */
    Result updateInfo(SystemDepartmentDO systemDepartmentDO);
}
