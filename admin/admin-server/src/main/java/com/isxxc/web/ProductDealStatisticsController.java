package com.isxxc.web;


import cc.likq.result.Result;
import cc.likq.util.JxlsExcelView;
import cc.likq.util.TimeUtils;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductDealStatisticsDTO;
import com.isxxc.service.feign.store.ProductDealStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品成交信息统计 前端控制器
 * </p>
 *
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@RestController
@RequestMapping("/productDealStatistics")
public class ProductDealStatisticsController {

    @Resource
    private ProductDealStatisticsService productDealStatisticsService;

    /***
     * 分页查询商品成交信息列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Date startTime, Date endTime) {
        if (startTime != null) {
            pager.putParam("startTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(startTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        if (endTime != null) {
            pager.putParam("endTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(endTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        return productDealStatisticsService.listPage(pager);
    }

    /***
     * 商品成交信息列表导出
     */
    @RequestMapping(value = "export",method = RequestMethod.GET)
    public ModelAndView export (){
        Map<String, Object> map = new HashMap<>();
        List<ProductDealStatisticsDTO> list = productDealStatisticsService.find();
        map.put("lists", list);
        return new ModelAndView(new JxlsExcelView("template/ProductDealExport.xlsx", "excel"), map);
    }


}
