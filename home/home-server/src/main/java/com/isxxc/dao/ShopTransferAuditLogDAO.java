package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.ShopTransferAuditLogDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
@Repository
public interface ShopTransferAuditLogDAO extends BaseMapper<ShopTransferAuditLogDO> {

    /***
     * 查询日志
     * @param transferId
     * @return
     */
    List<ShopTransferAuditLogDO> selectByTransferId(Integer transferId);
}