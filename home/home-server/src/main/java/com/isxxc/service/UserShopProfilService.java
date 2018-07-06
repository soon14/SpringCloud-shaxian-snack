package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.UserShopProfilDTO;
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

    /***
     * 更新信息
     * @param userShopProfilDO
     * @return
     */
    Result updateInfo(UserShopProfilDO userShopProfilDO);

    /***
     * 根据ID查询档案详情
     * @param userId
     * @return
     */
    Result getInfoByUserId(Integer userId);

    /***
     * 查询列表
     * @param page
     * @param userShopProfilDO
     * @return
     */
    Result listPage(Page<UserShopProfilDTO> page, UserShopProfilDO userShopProfilDO);

    /***
     * 完善档案信息
     * @param userShopProfilDTO
     * @return
     */
    Result completeInfo(UserShopProfilDTO userShopProfilDTO);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);
}
