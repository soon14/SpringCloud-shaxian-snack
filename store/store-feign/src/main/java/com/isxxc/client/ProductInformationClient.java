package com.isxxc.client;

import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductSearchDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *     商品详细信息 服务治理
 * </p>
 * @author  yk
 * @since  2018-05-17
 */
@RequestMapping("/ProductInformationClient")
public interface ProductInformationClient {

    /***
     * 获取商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    Result getInfoById(@RequestParam("id") Integer id);


    /***
     * 查询商品列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 检索商品
     * @param productSearchDTO
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result search(ProductSearchDTO productSearchDTO);
}
