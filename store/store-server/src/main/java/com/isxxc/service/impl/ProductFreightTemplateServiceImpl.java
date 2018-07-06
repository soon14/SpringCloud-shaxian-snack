package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.ProductStateEnum;
import com.isxxc.dao.ProductFreightTemplateDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductFreightTemplateDTO;
import com.isxxc.domain.dto.ProductFreightTemplateItemDTO;
import com.isxxc.domain.entity.ProductFreightTemplateDO;
import com.isxxc.service.ProductFreightTemplateItemService;
import com.isxxc.service.ProductFreightTemplateService;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 运费模版 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductFreightTemplateServiceImpl extends ServiceImpl<ProductFreightTemplateDAO, ProductFreightTemplateDO> implements ProductFreightTemplateService {

    @Resource
    private ProductFreightTemplateItemService productFreightTemplateItemService;

    @Override
    public Result save(ProductFreightTemplateDTO productFreightTemplateDTO) {
        productFreightTemplateDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        productFreightTemplateDTO.setGmtModified(new Date());
        productFreightTemplateDTO.setGmtCreate(new Date());
        insert(productFreightTemplateDTO);
        if (CollectionUtils.isNotEmpty(productFreightTemplateDTO.getFreightTemplateItemList())) {
            productFreightTemplateDTO.getFreightTemplateItemList().forEach(productFreightTemplateItemDTO -> {
                productFreightTemplateItemDTO.setFreightTemplateId(productFreightTemplateDTO.getId());
                productFreightTemplateItemDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                productFreightTemplateItemDTO.setGmtModified(new Date());
                productFreightTemplateItemDTO.setGmtCreate(new Date());
                productFreightTemplateItemService.insert(productFreightTemplateItemDTO);
            });
        }
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Pager pager) {
        EntityWrapper<ProductFreightTemplateDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_id", pager.getParamMap().get("storeId"));
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        pager = (Pager) selectPage(pager, entityWrapper);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoByIdAndStoreId(Integer id, Integer storeId) {
        EntityWrapper<ProductFreightTemplateDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_id", storeId);
        entityWrapper.eq("id", id);
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        ProductFreightTemplateDO freightTemplateDO = selectOne(entityWrapper);
        if (freightTemplateDO == null) {
            return ResponseResult.success();
        }
        ProductFreightTemplateDTO productFreightTemplateDTO = new ProductFreightTemplateDTO(freightTemplateDO);
        if (productFreightTemplateDTO.getType() == ProductStateEnum.FreightTemplate.CUSTOM.type) {
            List<ProductFreightTemplateItemDTO> freightTemplateItemDOList = productFreightTemplateItemService.selectByFreightTemplateId(productFreightTemplateDTO.getId());
            if (CollectionUtils.isNotEmpty(freightTemplateItemDOList)) {
                productFreightTemplateDTO.setFreightTemplateItemList(freightTemplateItemDOList);
            }
        }
        return ResponseResult.success(productFreightTemplateDTO);
    }

    @Override
    public Result delByIdAndStoreId(Integer id, Integer storeId) {
        EntityWrapper<ProductFreightTemplateDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_id", storeId);
        entityWrapper.eq("id", id);
        ProductFreightTemplateDO freightTemplateDO = selectOne(entityWrapper);
        if (freightTemplateDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "运费模版不存在");
        }
        freightTemplateDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        freightTemplateDO.setGmtModified(new Date());
        return updateById(freightTemplateDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
