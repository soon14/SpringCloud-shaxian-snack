package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserStoreProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
public interface UserStoreProfilService extends IService<UserStoreProfilDO> {

    /***
     * 保存原料供应商档案
     * @param storeProfilDO
     * @return
     */
    Result save(UserStoreProfilDO storeProfilDO);

    /***
     * 更新信息
     * @param userStoreProfilDO
     * @return
     */
    Result updateInfo(UserStoreProfilDO userStoreProfilDO);

    /***
     *根据ID获取档案信息
     * @param userId
     * @return
     */
    Result getInfoByUserId(Integer userId);

    /***
     * 完善信息
     * @param userStoreProfilDO
     * @return
     */
    Result completeInfo(UserStoreProfilDO userStoreProfilDO);
}
