package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductFreightTemplateItemDAO;
import com.isxxc.domain.dto.ProductFreightTemplateItemDTO;
import com.isxxc.domain.entity.ProductFreightTemplateItemDO;
import com.isxxc.service.ProductFreightTemplateItemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 运费模版项 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductFreightTemplateItemServiceImpl extends ServiceImpl<ProductFreightTemplateItemDAO, ProductFreightTemplateItemDO> implements ProductFreightTemplateItemService {

    @Resource
    private ProductFreightTemplateItemDAO productFreightTemplateItemDAO;

    @Override
    public List<ProductFreightTemplateItemDTO> selectByFreightTemplateId(Integer freightTemplateId) {
        return productFreightTemplateItemDAO.selectByFreightTemplateId(freightTemplateId);
    }
}
