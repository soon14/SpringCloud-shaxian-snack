package com.isxxc.service;

import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductShelvesStatisticsDTO;

import java.util.List;

/**
 * <p>
 * 商品上下架信息统计 服务类
 * </p>
 *
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
public interface ProductShelvesStatisticsService {


    /***
     * 分页查询商品上下架信息统计列表
     * @param pager
     * @return
     */
    Result listPage(Pager pager);


    /***
     * 查询商品上下架信息统计列表
     *
     */
    List<ProductShelvesStatisticsDTO> find();


}
