package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ProductFreightTemplateItemDTO;
import com.isxxc.domain.entity.ProductFreightTemplateItemDO;

import java.util.List;

/**
 * <p>
 * 运费模版项 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
public interface ProductFreightTemplateItemService extends IService<ProductFreightTemplateItemDO> {

    /***
     * 根据运费模版查询列表
     * @param freightTemplateId
     * @return
     */
    List<ProductFreightTemplateItemDTO> selectByFreightTemplateId(Integer freightTemplateId);
}
