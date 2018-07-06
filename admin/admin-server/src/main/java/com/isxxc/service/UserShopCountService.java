package com.isxxc.service;

import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.UserRoleDO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.domain.entity.UserShopProfilImgDO;

/**
 * <p>
 * 门店数统计 服务类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-22
 */
public interface UserShopCountService extends IService<UserShopProfilDO> {

    /***
     * 门店数统计
     * @param userShopProfilDO
     * @return
     */
    Result countUserShop(UserShopProfilDO userShopProfilDO);
    /***
     * 门店数  -- 时间段查询
     * @param pager
     * @return
     */
    Pager listPage(Pager pager);
}
