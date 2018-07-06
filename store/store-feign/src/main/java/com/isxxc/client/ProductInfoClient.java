package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/productInfoClient")
public interface ProductInfoClient {

    /***
     * 添加商品
     * @param productInfoDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(ProductInfoDTO productInfoDTO);

    /***
     *更新商品信息
     * @param productInfoDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(ProductInfoDTO productInfoDTO);

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

    /***
     * 根据商品ID及商店ID查询商品
     * @param id
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndStoreId", method = RequestMethod.GET)
    Result getInfoByIdAndStoreId(@RequestParam("id") Integer id, @RequestParam("storeId") Integer storeId);

    /***
     * 商品上架
     * @param id
     * @param storeId
     * @return
     */
    @RequestMapping(value = "onShelves", method = RequestMethod.POST)
    Result onShelves(@RequestParam("id") Integer id, @RequestParam("storeId") Integer storeId);

    /***
     * 商品下架
     * @param id
     * @param storeId
     * @return
     */
    @RequestMapping(value = "offShelves", method = RequestMethod.POST)
    Result offShelves(@RequestParam("id") Integer id, @RequestParam("storeId") Integer storeId);
}
