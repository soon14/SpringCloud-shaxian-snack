package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductNumStatisticsDTO;
import com.isxxc.service.ProductNumStatisticsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品数量信息统计 服务治理
 * </p>
 *
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@RestController
public class ProductNumStatisticsClientImpl implements ProductNumStatisticsClient {

    @Resource
    private ProductNumStatisticsService productNumStatisticsService;

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return productNumStatisticsService.listPage(pager);
    }

    @Override
    public List<ProductNumStatisticsDTO> find() { return productNumStatisticsService.find(); }
}
