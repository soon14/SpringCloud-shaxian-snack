package cc.likq.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

import cc.likq.base.domain.entity.SystemAdminDO;

/**
 * <p> Mapper 接口 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Repository
public interface SystemAdminDAO extends BaseMapper<SystemAdminDO> {

    /***
     * 根据帐号查询对象
     * @param account
     * @return
     */
    SystemAdminDO getByAccount(String account);
}