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
     * 审核
     * @param id
     * @return
     */
    Result updateRecommend(Integer id);

    /***
     * 删除信息
     * @param id
     * @return
     */
    Result delById(Integer id);
}
