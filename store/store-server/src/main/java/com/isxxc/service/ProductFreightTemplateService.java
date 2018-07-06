package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductFreightTemplateDTO;
import com.isxxc.domain.entity.ProductFreightTemplateDO;

import cc.likq.result.Result;

/**
 * <p>
 * 运费模版 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-01-12
 */
public interface ProductFreightTemplateService extends IService<ProductFreightTemplateDO> {

    /***
     * 添加运费模版
     * @param productFreightTemplateDTO
     * @return
     */
    Result save(ProductFreightTemplateDTO productFreightTemplateDTO);

    /***
     * 查询列表,带分页
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 根据ID及店铺ID查询详情
     * @param id
     * @param storeId
     * @return
     */
    Result getInfoByIdAndStoreId(Integer id, Integer storeId);

    /***
     * 删除运费模版
     * @param id
     * @param storeId
     * @return
     */
    Result delByIdAndStoreId(Integer id, Integer storeId);
}
