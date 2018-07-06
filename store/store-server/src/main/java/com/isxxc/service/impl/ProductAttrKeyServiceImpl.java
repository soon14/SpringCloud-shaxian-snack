package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ProductAttrKeyDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ProductAttrKeyDO;
import com.isxxc.service.ProductAttrKeyService;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 商品属性名称 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductAttrKeyServiceImpl extends ServiceImpl<ProductAttrKeyDAO, ProductAttrKeyDO> implements ProductAttrKeyService {

    @Resource
    private ProductAttrKeyDAO productAttrKeyDAO;

    @Override
    public Result save(ProductAttrKeyDO productAttrKeyDO) {
        productAttrKeyDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return insert(productAttrKeyDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listPager(Pager pager) {
        EntityWrapper<ProductAttrKeyDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        if (MapUtils.isNotEmpty(pager.getParamMap()) && pager.getParamMap().containsKey("categoryId") &&
                pager.getParamMap().get("categoryId") != null) {
            entityWrapper.eq("category_id", pager.getParamMap().get("categoryId"));
        }
        return ResponseResult.success(selectPage(pager, entityWrapper));
    }
}
