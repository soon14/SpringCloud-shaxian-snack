package cc.likq.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import cc.likq.base.domain.entity.SystemDepartmentDO;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Repository
public interface SystemDepartmentDAO extends BaseMapper<SystemDepartmentDO> {


    /**
     * 查询部门列表,带分布
     */
    List<SystemDepartmentDO> list(Page<SystemDepartmentDO> page, @Param("name") String name, @Param("parentId") Integer parentId, @Param("deletedState") int deletedState);

    /**
     * 查询部门列表
     */
    List<SystemDepartmentDO> list(@Param("name") String name, @Param("parentId") Integer parentId, @Param("deletedState") int deletedState);

}