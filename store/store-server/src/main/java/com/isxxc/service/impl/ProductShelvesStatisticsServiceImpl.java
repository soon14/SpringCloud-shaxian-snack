package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.isxxc.dao.ProductShelvesStatisticsDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductShelvesStatisticsDTO;
import com.isxxc.service.ProductShelvesStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/***
 * <P>
 * 商品上下架信息统计 服务实现类
 * </P>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductShelvesStatisticsServiceImpl implements ProductShelvesStatisticsService {

    @Resource
    private ProductShelvesStatisticsDAO productShelvesStatisticsDAO;


    @Override
    public Result listPage(Pager pager) {
        List<ProductShelvesStatisticsDTO> productShelvesStatisticsDTOList = productShelvesStatisticsDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(productShelvesStatisticsDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public  List<ProductShelvesStatisticsDTO> find( ) {
        List<ProductShelvesStatisticsDTO> productShelvesStatisticsDTOList = productShelvesStatisticsDAO.find();
        return productShelvesStatisticsDTOList;
    }


}
