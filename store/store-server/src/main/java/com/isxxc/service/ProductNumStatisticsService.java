package com.isxxc.service;

import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductNumStatisticsDTO;

import java.util.List;

/**
 * <p>
 * 商品数量信息统计 服务类
 * </p>
 *
 * @author yk
 * @since 2018-05-23
 */
public interface ProductNumStatisticsService {


    /***
     * 分页查询商品数量信息统计列表
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 查询商品数量信息统计列表
     *
     */
    List<ProductNumStatisticsDTO> find();
}
