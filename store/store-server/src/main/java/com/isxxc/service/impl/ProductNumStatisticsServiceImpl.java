package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.isxxc.dao.ProductNumStatisticsDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductNumStatisticsDTO;
import com.isxxc.service.ProductNumStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/***
 * <P>
 * 商品数量信息统计 服务实现类
 * </P>
 * @author  yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductNumStatisticsServiceImpl implements ProductNumStatisticsService {

    @Resource
    private ProductNumStatisticsDAO productNumStatisticsDAO;


    @Override
    public Result listPage(Pager pager) {
        List<ProductNumStatisticsDTO> productNumStatisticsDTOList = productNumStatisticsDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(productNumStatisticsDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public List<ProductNumStatisticsDTO> find() {
        List<ProductNumStatisticsDTO> productNumStatisticsDTOList = productNumStatisticsDAO.find();
        return productNumStatisticsDTOList;
    }
}
