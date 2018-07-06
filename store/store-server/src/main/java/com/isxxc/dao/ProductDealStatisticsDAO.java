package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductDealStatisticsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * <p>
 *  商品成交信息统计 Mapper接口
 * </p>
 * @author yk
 * @since  2018-05-23
 * @update 2018-05-30
 */
@Repository
public interface ProductDealStatisticsDAO extends BaseMapper<ProductDealStatisticsDTO> {

    /***
     * 分页查询商品成交信息统计列表
     * @param pager
     * @param paramMap
     * @return
     */
    List<ProductDealStatisticsDTO> listPage(Pager pager, @Param("params") Map<String, Object> paramMap);

    /***
     * 查询商品成交信息统计列表
     *
     */
    List<ProductDealStatisticsDTO> find();
}