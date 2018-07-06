package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductShelvesStatisticsDTO;
import com.isxxc.service.ProductShelvesStatisticsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品上下架信息统计 服务治理
 * </p>
 *
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@RestController
public class ProductShelvesStatisticsClientImpl implements ProductShelvesStatisticsClient {

    @Resource
    private ProductShelvesStatisticsService productShelvesStatisticsService;

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return productShelvesStatisticsService.listPage(pager);
    }

    @Override
    public List<ProductShelvesStatisticsDTO> find( ) { return productShelvesStatisticsService.find(); }
}
