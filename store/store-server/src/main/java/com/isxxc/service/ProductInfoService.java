package com.isxxc.service;

import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;

import cc.likq.result.Result;

/**
 * 商品服务器
 *
 * @author 泥水佬
 * @date 2018/1/14
 */
public interface ProductInfoService {

    /***
     * 添加商品
     * @param productInfoDTO
     * @return
     */
    Result save(ProductInfoDTO productInfoDTO);

    /***
     * 更新用户信息
     * @param productInfoDTO
     * @return
     */
    Result updateInfo(ProductInfoDTO productInfoDTO);

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

    /***
     * 根据商品ID及商店ID查询商品
     * @param id
     * @param storeId
     * @return
     */
    Result getInfoByIdAndStoreId(Integer id, Integer storeId);

    /***
     * 商品上架
     * @param id
     * @param storeId
     * @return
     */
    Result onShelves(Integer id, Integer storeId);

    /***
     * 商品下架
     * @param id
     * @param storeId
     * @return
     */
    Result offShelves(Integer id, Integer storeId);
}
