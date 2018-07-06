package com.isxxc.service;

import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductSearchDTO;

/**
 * 商品详细信息 服务类
 *
 * @author yk
 * @date 2018-05-17
 */
public interface ProductInformationService {

    /***
     * 查询商品详情
     * @param id
     * @return
     */
    Result getInfoById(Integer id);

    /***
     * 查询列表
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 检索商品
     * @param productSearchDTO
     * @return
     */
    Result search(ProductSearchDTO productSearchDTO);


}
