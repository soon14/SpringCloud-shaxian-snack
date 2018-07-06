package cc.likq.base.dao;

import cc.likq.base.domain.entity.SystemRoleDO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/***
 * <P>
 * 系统角色表 Mapper接口
 * </P>
 * @author  yk
 * @since  2018-05-30
 *
 */
public interface SystemRoleDAO extends BaseMapper<SystemRoleDO> {

    /***
     * 分页查询系统角色
     * @param pager
     * @return
     */
    List<SystemRoleDO> listPage (Pager pager, @Param("params") Map<String, Object> paramMap);

    /***
     * 查询系统角色
     */
    List<SystemRoleDO> query ();
}

