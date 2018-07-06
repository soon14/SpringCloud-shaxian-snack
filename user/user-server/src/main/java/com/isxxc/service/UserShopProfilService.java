package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserShopProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
public interface UserShopProfilService extends IService<UserShopProfilDO> {

    /***
     *保存商户店铺信息
     * @param userShopProfilDO
     * @return
     */
    Result save(UserShopProfilDO userShopProfilDO);

}
