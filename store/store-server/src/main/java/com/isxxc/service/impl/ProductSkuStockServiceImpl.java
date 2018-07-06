package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ProductSkuStockDAO;
import com.isxxc.domain.entity.ProductSkuStockDO;
import com.isxxc.domain.entity.ProductSkuStockLogDO;
import com.isxxc.service.ProductSkuStockLogService;
import com.isxxc.service.ProductSkuStockService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * SKU商品库存 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-01-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSkuStockServiceImpl extends ServiceImpl<ProductSkuStockDAO, ProductSkuStockDO> implements ProductSkuStockService {

    @Resource
    private ProductSkuStockDAO productSkuStockDAO;

    @Resource
    private ProductSkuStockLogService productSkuStockLogService;

    @Override
    public Result upadteNum(Integer stockId, Integer num, Integer storeId, String remark) {
        ProductSkuStockDO skuStockDO = productSkuStockDAO.selectById(stockId);
        if (!skuStockDO.getStoreId().equals(storeId)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST);
        }
        if (num < 0 && skuStockDO.getNum() < Math.abs(num)) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "库存不能为负数");
        }

        //库存记录
        ProductSkuStockLogDO skuStockLogDO = new ProductSkuStockLogDO();
        skuStockLogDO.setSkuStockId(skuStockDO.getId());
        skuStockLogDO.setNum(num);
        skuStockLogDO.setBoforeNum(skuStockDO.getNum());
        skuStockLogDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        if (StringUtils.isNotBlank(remark)) {
            skuStockLogDO.setRemark(remark);
        }
        productSkuStockLogService.insert(skuStockLogDO);

        //库存更新
        skuStockDO.setNum(skuStockDO.getNum() + num);
        return updateById(skuStockDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public ProductSkuStockDO selectBySkuId(Integer skuId) {
        EntityWrapper<ProductSkuStockDO> stockDOEntityWrapper = new EntityWrapper<>();
        stockDOEntityWrapper.eq("sku_id", skuId);
        stockDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return selectOne(stockDOEntityWrapper);
    }
}
