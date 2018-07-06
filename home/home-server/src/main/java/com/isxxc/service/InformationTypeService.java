package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.InformationTypeDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
public interface InformationTypeService extends IService<InformationTypeDO> {

    /***
     * 添加
     * @param informationTypeDO
     * @return
     */
    Result save(InformationTypeDO informationTypeDO);

    /**
     * 根据ID删除
     */
    Result delById(Integer id);

    /***
     *更新信息
     * @param informationTypeDO
     * @return
     */
    Result updateInfo(InformationTypeDO informationTypeDO);

    /***
     * 查询列表，带分页
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 查询列表
     * @return
     */
    Result list(Integer category);
}
