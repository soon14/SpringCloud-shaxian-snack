package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.domain.entity.ProductCategoryDO;

import java.util.List;

import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
public interface ProductCategoryService extends IService<ProductCategoryDO> {

    /***
     * 添加分类
     * @param productCategoryDO
     * @return
     */
    Result save(ProductCategoryDO productCategoryDO);

    /***
     * 查询结果,树结构
     * @param productCategoryDTO
     * @return
     */
    Result listTree(ProductCategoryDTO productCategoryDTO);

    /***
     * 更新信息
     * @param productCategoryDO
     * @return
     */
    Result updateInfo(ProductCategoryDO productCategoryDO);

    /***
     * 获取所有子ID
     * @param categoryId
     * @return
     */
    List<Integer> getChildId(Integer categoryId);
}
