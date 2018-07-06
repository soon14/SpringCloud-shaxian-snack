package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductDealStatisticsDTO;
import com.isxxc.service.ProductDealStatisticsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品成交信息统计 服务治理
 * </p>
 *
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@RestController
public class ProductDealStatisticsClientImpl implements ProductDealStatisticsClient {

    @Resource
    private ProductDealStatisticsService productDealStatisticsService;

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return productDealStatisticsService.listPage(pager);
    }

    @Override
    public List<ProductDealStatisticsDTO> find() { return productDealStatisticsService.find(); }
}
