package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.ProductStateEnum;
import com.isxxc.dao.ProductSkuInfoDAO;
import com.isxxc.domain.dto.ProductSkuInfoDTO;
import com.isxxc.domain.entity.ProductSkuInfoDO;
import com.isxxc.domain.entity.ProductSkuPriceMultiDO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.service.ProductSkuAttrRelationService;
import com.isxxc.service.ProductSkuInfoService;
import com.isxxc.service.ProductSkuPriceMultiService;
import com.isxxc.service.ProductSpuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 商品SKU信息 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuInfoServiceImpl extends ServiceImpl<ProductSkuInfoDAO, ProductSkuInfoDO> implements ProductSkuInfoService {

    @Resource
    private ProductSkuInfoDAO productSkuInfoDAO;

    @Resource
    private ProductSkuAttrRelationService productSkuAttrRelationService;

    @Resource
    private ProductSkuPriceMultiService productSkuPriceMultiService;

    @Resource
    private ProductSpuService productSpuService;

    @Override
    public List<ProductSkuInfoDTO> selectBySpuId(Integer spuId, Integer isDeleted) {
        return productSkuInfoDAO.selectBySpuId(spuId, isDeleted);
    }

    @Override
    public ProductSkuInfoDTO selectDTOById(Integer id) {
        ProductSkuInfoDTO skuInfoDTO = productSkuInfoDAO.selectDTOById(id);
        skuInfoDTO.setAttrInfoList(productSkuAttrRelationService.selectDTOBySkuId(skuInfoDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code));
        //如果为多价格类型，则获取多价格
        if (skuInfoDTO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
            EntityWrapper<ProductSkuPriceMultiDO> skuPriceMultiDOEntityWrapper = new EntityWrapper<>();
            skuPriceMultiDOEntityWrapper.eq("sku_id", skuInfoDTO.getId());
            skuPriceMultiDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
            List<ProductSkuPriceMultiDO> skuPriceMultiDOList = productSkuPriceMultiService.selectList(skuPriceMultiDOEntityWrapper);
            skuInfoDTO.setSkuPriceMultiList(skuPriceMultiDOList);
        }
        return skuInfoDTO;
    }

    @Override
    public Integer updateShelvesBySpuId(Integer spuId, int shelvesCode) {
        return productSkuInfoDAO.updateShelvesBySpuId(spuId, shelvesCode, CommonStateEnum.IsDeleted.NOT_DELETED.code);
    }

    @Override
    public Result onShelvesBySkuId(Integer skuId, Integer storeId) {
        ProductSkuInfoDO skuInfoDO = productSkuInfoDAO.selectById(skuId);
        ProductSpuDO spuDO = productSpuService.selectById(skuInfoDO.getSpuId());
        if (spuDO.getIsDeleted() == CommonStateEnum.IsDeleted.HAVE_DELETED.code || !spuDO.getStoreId().equals(storeId)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前商品已经删除,无法上架");
        }
        if (spuDO.getIsShelves() == ProductStateEnum.IsShelves.OFF.code) {
            spuDO.setIsShelves(ProductStateEnum.IsShelves.ON.code);
            productSpuService.updateById(spuDO);
        }
        skuInfoDO.setIsShelves(ProductStateEnum.IsShelves.ON.code);
        return updateById(skuInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result offShelvesBySkuId(Integer skuId, Integer storeId) {
        ProductSkuInfoDO skuInfoDO = productSkuInfoDAO.selectById(skuId);
        ProductSpuDO spuDO = productSpuService.selectById(skuInfoDO.getSpuId());
        if (!spuDO.getStoreId().equals(storeId)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前商品信息错误,无法上下架");
        }
        skuInfoDO.setIsShelves(ProductStateEnum.IsShelves.OFF.code);
        return updateById(skuInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
