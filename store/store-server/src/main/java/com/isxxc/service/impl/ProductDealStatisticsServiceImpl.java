package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.isxxc.dao.ProductDealStatisticsDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductDealStatisticsDTO;
import com.isxxc.service.ProductDealStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/***
 * <P>
 * 商品成交信息统计 服务实现类
 * </P>
 * @author  yk
 * @since  2018-05-23
 * @update 2018-05-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductDealStatisticsServiceImpl implements ProductDealStatisticsService {

    @Resource
    private ProductDealStatisticsDAO productDealStatisticsDAO;


    @Override
    public Result listPage(Pager pager) {
        List<ProductDealStatisticsDTO> productDealStatisticsDTOList = productDealStatisticsDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(productDealStatisticsDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public List<ProductDealStatisticsDTO> find() {
        List<ProductDealStatisticsDTO> productDealStatisticsDTOList = productDealStatisticsDAO.find();
        return productDealStatisticsDTOList;
    }
}
