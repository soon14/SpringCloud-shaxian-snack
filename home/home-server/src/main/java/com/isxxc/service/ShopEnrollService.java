package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ShopEnrollDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-27
 */
public interface ShopEnrollService extends IService<ShopEnrollDO> {

    /***
     * 保存登记
     * @param shopEnrollDO
     * @return
     */
    Result save(ShopEnrollDO shopEnrollDO);
}
