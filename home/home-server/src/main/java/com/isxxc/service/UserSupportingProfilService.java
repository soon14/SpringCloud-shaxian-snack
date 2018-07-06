package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserSupportingProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
public interface UserSupportingProfilService extends IService<UserSupportingProfilDO> {

    /***
     * 保存
     * @param userSupportingProfilDO
     * @return
     */
    Result save(UserSupportingProfilDO userSupportingProfilDO);

    /***
     * 更新信息
     * @param userSupportingProfilDO
     * @return
     */
    Result updateInfo(UserSupportingProfilDO userSupportingProfilDO);

    /***
     * 根据ID查询档案
     * @param userId
     * @return
     */
    Result getInfoByUserId(Integer userId);

    /***
     * 完善资料
     * @param userSupportingProfilDO
     * @return
     */
    Result completeInfo(UserSupportingProfilDO userSupportingProfilDO);
}
