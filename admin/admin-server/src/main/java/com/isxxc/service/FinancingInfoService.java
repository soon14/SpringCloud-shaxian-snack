package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.FinancingInfoDTO;
import com.isxxc.domain.entity.FinancingInfoDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
public interface FinancingInfoService extends IService<FinancingInfoDO> {

    /***
     *
     * @return
     */
    Result save(FinancingInfoDTO financingInfoDTO);

    /***
     * 查询列表，带分页
     * @param page
     * @param financingInfoDTO
     * @return
     */
    Result listPage(Page page, FinancingInfoDTO financingInfoDTO);

    /***
     * 内容更新
     * @param financingInfoDTO
     * @return
     */
    Result updateInfo(FinancingInfoDTO financingInfoDTO);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);

    /***
     * 更新推荐状态
     * @param id
     * @return
     */
    Result updateRecommend(Integer id);

    /***
     * 添加删除
     * @param id
     * @return
     */
    Result delById(Integer id);
}
