package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.LoansInfoDTO;
import com.isxxc.domain.entity.LoansInfoDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
public interface LoansInfoService extends IService<LoansInfoDO> {

    /***
     * 保存小贷信息
     * @param loansInfoDO
     * @return
     */
    Result save(LoansInfoDO loansInfoDO);

    /***
     * 查询列表
     * @param page
     * @param loansInfoDTO
     * @return
     */
    Result listPage(Page page, LoansInfoDTO loansInfoDTO);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);

    /***
     * 更新信息
     * @param loansInfoDO
     * @return
     */
    Result updateInfo(LoansInfoDO loansInfoDO);

    /***
     * 根据会员查询信息列表
     * @param page
     * @param loansInfoDTO
     * @return
     */
    Result listPageByUserId(Page page, LoansInfoDTO loansInfoDTO);

    /***
     * 取消发布
     * @param id
     * @param userId
     * @return
     */
    Result cancelPublist(Integer id, Integer userId);

    /***
     * 发布上线
     * @param id
     * @param userId
     * @return
     */
    Result publist(Integer id, Integer userId);

    /***
     * 猜你喜欢
     * @param size
     * @return
     */
    Result mayLike(Integer size);
}
