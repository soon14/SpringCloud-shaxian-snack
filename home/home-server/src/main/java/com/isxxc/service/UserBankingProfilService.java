package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserBankingProfilDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
public interface UserBankingProfilService extends IService<UserBankingProfilDO> {

    /***
     * 保存会员金融档案
     * @param userBankingProfilDO
     * @return
     */
    Result save(UserBankingProfilDO userBankingProfilDO);

    /***
     * 更新信息
     * @param userBankingProfilDO
     * @return
     */
    Result updateInfo(UserBankingProfilDO userBankingProfilDO);

    /***
     * 根据会员ID获取档案详情
     * @param userId
     * @return
     */
    Result getInfoByUserId(Integer userId);

    /***
     * 完善信息
     * @param userBankingProfilDO
     * @return
     */
    Result completeInfo(UserBankingProfilDO userBankingProfilDO);
}
