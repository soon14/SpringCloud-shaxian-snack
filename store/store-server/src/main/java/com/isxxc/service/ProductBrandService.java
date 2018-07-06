package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductBrandDO;

import cc.likq.result.Result;

/**
 * <p>
 * 商品品牌 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
public interface ProductBrandService extends IService<ProductBrandDO> {

    /***
     * 添加品牌信息
     * @param productBrand
     * @return
     */
    Result save(ProductBrandDO productBrand);

    /***
     * 更新信息
     * @param productBrand
     * @return
     */
    Result updateInfo(ProductBrandDO productBrand);

    /***
     * 查询列表,带分页
     * @param page
     * @return
     */
    Result listPage(Pager page);

    /***
     * 查询列表
     * @param productBrand
     * @return
     */
    Result list(ProductBrandDO productBrand);

    /***
     * 删除
     * @param id
     * @return
     */
    Result delById(Integer id);
}
