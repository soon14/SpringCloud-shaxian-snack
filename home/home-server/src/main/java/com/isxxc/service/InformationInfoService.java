package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.InformationInfoDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
public interface InformationInfoService extends IService<InformationInfoDO> {

    /***
     * 查询列表
     * @param page
     * @param informationInfoDO
     * @return
     */
    Result listPage(Page page, InformationInfoDO informationInfoDO);

    /***
     * 增加阅读数
     * @param sourceName
     * @return
     */
    Result pageviewIncr(String sourceName);

    /***
     * 查询阅读数
     * @param sourceName
     * @return
     */
    int pageview(String sourceName);

    /***
     * 猜你喜欢
     * @param size
     * @return
     */
    Result mayLike(Integer size, Integer typeId, Integer category);
}
