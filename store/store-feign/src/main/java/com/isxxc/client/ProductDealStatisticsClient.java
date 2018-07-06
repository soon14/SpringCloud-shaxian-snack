package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductDealStatisticsDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>
 * 商品成交信息统计 服务治理实现类
 * </p>
 *
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@RequestMapping("/productDealStatisticsClient")
public interface ProductDealStatisticsClient {
    /***
     * 分页查询商品成交信息列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 查询商品成交信息列表
     *
     */
    @RequestMapping(value = "find",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDealStatisticsDTO> find();
}
