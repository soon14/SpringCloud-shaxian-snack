package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.ProductStateEnum;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductAttrInfoDTO;
import com.isxxc.domain.dto.ProductBaseInfoDTO;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.domain.dto.ProductSkuInfoDTO;
import com.isxxc.domain.dto.ProductSpuImgDTO;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.ProductAttrKeyDO;
import com.isxxc.domain.entity.ProductAttrValueDO;
import com.isxxc.domain.entity.ProductFreightTemplateDO;
import com.isxxc.domain.entity.ProductSkuAttrRelationDO;
import com.isxxc.domain.entity.ProductSkuInfoDO;
import com.isxxc.domain.entity.ProductSkuPriceMultiDO;
import com.isxxc.domain.entity.ProductSkuStockDO;
import com.isxxc.domain.entity.ProductSkuStockLogDO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.service.ProductAttrKeyService;
import com.isxxc.service.ProductAttrValueService;
import com.isxxc.service.ProductCategoryService;
import com.isxxc.service.ProductFreightTemplateService;
import com.isxxc.service.ProductInfoService;
import com.isxxc.service.ProductSalesVolumeService;
import com.isxxc.service.ProductSkuAttrRelationService;
import com.isxxc.service.ProductSkuInfoService;
import com.isxxc.service.ProductSkuPriceMultiService;
import com.isxxc.service.ProductSkuStockLogService;
import com.isxxc.service.ProductSkuStockService;
import com.isxxc.service.ProductSpuImgService;
import com.isxxc.service.ProductSpuService;
import com.isxxc.service.UserStoreProfilService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;

/**
 * 商品服务器
 *
 * @author 泥水佬
 * @date 2018/1/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductSpuService productSpuService;

    @Resource
    private ProductSkuInfoService productSkuInfoService;

    @Resource
    private ProductSkuPriceMultiService productSkuPriceMultiService;

    @Resource
    private ProductSpuImgService productSpuImgService;

    @Resource
    private ProductAttrKeyService productAttrKeyService;

    @Resource
    private ProductAttrValueService productAttrValueService;

    @Resource
    private ProductSkuAttrRelationService productSkuAttrRelationService;

    @Resource
    private ProductSkuStockService productSkuStockService;

    @Resource
    private ProductSkuStockLogService productSkuStockLogService;

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private UserStoreProfilService userStoreProfilService;

    @Resource
    private ProductSalesVolumeService productSalesVolumeService;

    @Resource
    private ProductFreightTemplateService productFreightTemplateService;

    @Override
    public Result save(ProductInfoDTO productInfoDTO) {

        //持久化SPU信息
        productInfoDTO.setIsShelves(ProductStateEnum.IsShelves.OFF.code);
        //默认审核通过,预留
        productInfoDTO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        productInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        productInfoDTO.setGmtModified(new Date());
        productInfoDTO.setGmtCreate(new Date());
        productSpuService.insert(productInfoDTO);


        //持久化SPU图片
        for (ProductSpuImgDTO productSpuImgDTO : productInfoDTO.getImgList()) {
            //判断图片文件是否存在
            if (!FileUtils.exists(CommonFolderConstant.getImageTempPath(productSpuImgDTO.getName()))) {
                //事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseResult.failResult(ResultCodeEnum.NO_FOUND, "图片已经失效,请重新上传", new HashMap<String, Object>(1) {{
                    put("key", productSpuImgDTO.getName());
                }});
            }
            //迁移图片文件到相关文件夹
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(productSpuImgDTO.getName()), CommonFolderConstant.getProductSpuImgPath(productInfoDTO.getId()));

            //持久化
            productSpuImgDTO.setSpuId(productInfoDTO.getId());
            productSpuImgDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSpuImgDTO.setGmtModified(new Date());
            productSpuImgDTO.setGmtCreate(new Date());
            productSpuImgService.insert(productSpuImgDTO);
        }

        //持久化SKU信息
        Long showPrice = 0L;
        for (ProductSkuInfoDTO productSkuInfoDTO : productInfoDTO.getSkuInfoList()) {
            //持久化SKU
            productSkuInfoDTO.setSpuId(productInfoDTO.getId());
            productSkuInfoDTO.setIsShelves(ProductStateEnum.IsShelves.OFF.code);
            productSkuInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSkuInfoDTO.setGmtModified(new Date());
            productSkuInfoDTO.setGmtCreate(new Date());
            productSkuInfoService.insert(productSkuInfoDTO);

            //持久化SKU属性
            for (ProductAttrInfoDTO productAttrInfoDTO : productSkuInfoDTO.getAttrInfoList()) {
                //判断属性名称是存在
                EntityWrapper<ProductAttrKeyDO> attrKeyWrapper = new EntityWrapper<>();
                attrKeyWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
                attrKeyWrapper.eq("id", productAttrInfoDTO.getAttrKeyId());
                ProductAttrKeyDO productAttrKeyDO = productAttrKeyService.selectOne(attrKeyWrapper);
                if (productAttrKeyDO == null) {
                    //事务回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "选择的属性名称不存在,请重新选择");
                }

                //处理SKU属性值信息
                ProductAttrValueDO productAttrValueDO = productAttrValueService.selectByName(productAttrInfoDTO.getAttrValueName().trim());
                if (productAttrValueDO == null) {
                    productAttrValueDO = new ProductAttrValueDO();
                    productAttrValueDO.setAttrKeyId(productAttrKeyDO.getId());
                    productAttrValueDO.setName(productAttrInfoDTO.getAttrValueName().trim());
                    productAttrValueDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                    productAttrValueDO.setGmtModified(new Date());
                    productAttrValueDO.setGmtCreate(new Date());
                    productAttrValueService.insert(productAttrValueDO);
                }

                //持久化SKU与属性关联
                ProductSkuAttrRelationDO productSkuAttrRelationDO = new ProductSkuAttrRelationDO();
                productSkuAttrRelationDO.setSkuId(productSkuInfoDTO.getId());
                productSkuAttrRelationDO.setAttrKeyId(productAttrKeyDO.getId());
                productSkuAttrRelationDO.setAttrValueId(productAttrValueDO.getId());
                productSkuAttrRelationDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                productSkuAttrRelationDO.setGmtModified(new Date());
                productSkuAttrRelationDO.setGmtCreate(new Date());
                productSkuAttrRelationService.insert(productSkuAttrRelationDO);
            }

            //如果为多价格定义,即持久化
            if (productSkuInfoDTO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                for (ProductSkuPriceMultiDO productSkuPriceMultiDO : productSkuInfoDTO.getSkuPriceMultiList()) {
                    productSkuPriceMultiDO.setSkuId(productSkuInfoDTO.getId());
                    productSkuPriceMultiDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                    productSkuPriceMultiDO.setGmtModified(new Date());
                    productSkuPriceMultiDO.setGmtCreate(new Date());
                    productSkuPriceMultiService.insert(productSkuPriceMultiDO);
                    //展示价,最小值为展示价
                    showPrice = (showPrice.equals(0L) || showPrice > productSkuPriceMultiDO.getSalePrice()) ? productSkuPriceMultiDO.getSalePrice() : showPrice;
                }
            } else {
                //展示价,最小值为展示价
                showPrice = (showPrice.equals(0L) || showPrice > productSkuInfoDTO.getSalePrice()) ? productSkuInfoDTO.getSalePrice() : showPrice;
            }

            //持久化SKU库存
            ProductSkuStockDO productSkuStockDO = new ProductSkuStockDO();
            productSkuStockDO.setStoreId(productInfoDTO.getStoreId());
            productSkuStockDO.setSkuId(productSkuInfoDTO.getId());
            productSkuStockDO.setNum(productSkuInfoDTO.getStockNum());
            productSkuStockDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSkuStockDO.setGmtModified(new Date());
            productSkuStockDO.setGmtCreate(new Date());
            productSkuStockService.insert(productSkuStockDO);

            //持久化库存日志
            ProductSkuStockLogDO productSkuStockLogDO = new ProductSkuStockLogDO();
            productSkuStockLogDO.setSkuStockId(productSkuStockDO.getId());
            productSkuStockLogDO.setNum(productSkuStockDO.getNum());
            productSkuStockLogDO.setBoforeNum(0);
            productSkuStockLogDO.setRemark("库存初始化");
            productSkuStockLogDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSkuStockLogDO.setGmtModified(new Date());
            productSkuStockLogDO.setGmtCreate(new Date());
            productSkuStockLogService.insert(productSkuStockLogDO);
        }

        //处理商品内容详情
        Result result = contentInfo(productInfoDTO);
        if (result.getCode() != ResponseResult.success().getCode()) {
            return result;
        }

        productInfoDTO.setShowPrice(showPrice);
        productSpuService.updateById(productInfoDTO);

        return ResponseResult.success();
    }

    @Override
    public Result updateInfo(ProductInfoDTO productInfoDTO) {
        ProductSpuDO productSpuDO = productSpuService.selectById(productInfoDTO.getId());
        if (productSpuDO == null) {
            return ResponseResult.paramNotNull("商品不存在");
        }
        if (productSpuDO.getIsShelves() == ProductStateEnum.IsShelves.ON.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "上架中商品不可更新信息,请下架后再试");
        }

        //持久化SPU信息
        productInfoDTO.setIsShelves(ProductStateEnum.IsShelves.OFF.code);
        //默认审核通过,预留
        productInfoDTO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        productInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        productInfoDTO.setGmtModified(new Date());
        productSpuService.updateById(productInfoDTO);

        //持久化SPU图片
        List<ProductSpuImgDTO> productSpuImgDTODBList = productSpuImgService.selectDTOBySpuId(productSpuDO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<ProductSpuImgDTO> productSpuImgDTOList = productInfoDTO.getImgList();
        //处理重复图片数据
        if (CollectionUtils.isNotEmpty(productSpuImgDTODBList)) {
            List<ProductSpuImgDTO> intersectionSpuImgList = ListUtils.intersection(productSpuImgDTODBList, productSpuImgDTOList);
            productSpuImgDTODBList = ListUtils.removeAll(productSpuImgDTODBList, intersectionSpuImgList);
            productSpuImgDTOList = ListUtils.removeAll(productSpuImgDTOList, intersectionSpuImgList);
        }
        //剪切照片到商品文件夹
        for (ProductSpuImgDTO productSpuImgDTO : productSpuImgDTOList) {
            //判断图片文件是否存在
            if (!FileUtils.exists(CommonFolderConstant.getImageTempPath(productSpuImgDTO.getName()))) {
                //事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseResult.failResult(ResultCodeEnum.NO_FOUND, "图片已经失效,请重新上传", new HashMap<String, Object>(1) {{
                    put("key", productSpuImgDTO.getName());
                }});
            }
            //迁移图片文件到相关文件夹
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(productSpuImgDTO.getName()), CommonFolderConstant.getProductSpuImgPath(productInfoDTO.getId()));
            //持久化
            productSpuImgDTO.setSpuId(productInfoDTO.getId());
            productSpuImgDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSpuImgDTO.setGmtModified(new Date());
            productSpuImgDTO.setGmtCreate(new Date());
            productSpuImgService.insert(productSpuImgDTO);
        }

        //删除无用图片
        productSpuImgDTODBList.forEach(productSpuImgDTO -> {
            productSpuImgDTO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
            productSpuImgService.updateById(productSpuImgDTO);
        });

        //持久化SKU信息
        //数据库原有信息
        List<ProductSkuInfoDTO> skuInfoDTODBList = productSkuInfoService.selectBySpuId(productSpuDO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
        //新提交信息
        List<ProductSkuInfoDTO> skuInfoDTOList = productInfoDTO.getSkuInfoList();
        //取原有数据差集数据,并删除
        ListUtils.removeAll(skuInfoDTODBList, skuInfoDTOList).forEach(productSkuInfoDTO -> {
            //删除SKU信息
            productSkuInfoDTO.setGmtModified(new Date());
            productSkuInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
            productSkuInfoService.updateById(productSkuInfoDTO);

            //删除SKU属性关联
            List<ProductAttrInfoDTO> attrInfoDTODBList = productSkuAttrRelationService.selectDTOBySkuId(productSkuInfoDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
            attrInfoDTODBList.forEach(productAttrInfoDTO -> {
                productAttrInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
                productAttrInfoDTO.setGmtModified(new Date());
                productSkuAttrRelationService.updateById(productAttrInfoDTO);
            });

            //删除SKU库存
            EntityWrapper<ProductSkuStockDO> skuStockDOEntityWrapper = new EntityWrapper<>();
            skuStockDOEntityWrapper.eq("sku_id", productSkuInfoDTO.getId());
            skuStockDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
            ProductSkuStockDO skuStockDO = productSkuStockService.selectOne(skuStockDOEntityWrapper);
            skuStockDO.setGmtModified(new Date());
            skuStockDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
            productSkuStockService.updateById(skuStockDO);
        });

        //展示价格
        Long showPrice = 0L;
        for (ProductSkuInfoDTO skuInfoDTO : skuInfoDTOList) {
            //查询原的SKU信息
            ProductSkuInfoDO skuInfoDODB = skuInfoDTO.getId() == null ? null : productSkuInfoService.selectById(skuInfoDTO.getId());

            //持久化SKU
            skuInfoDTO.setIsShelves(ProductStateEnum.IsShelves.OFF.code);
            skuInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            skuInfoDTO.setGmtModified(new Date());
            if (skuInfoDODB == null) {
                skuInfoDTO.setGmtCreate(new Date());
                skuInfoDTO.setSpuId(productInfoDTO.getId());
                productSkuInfoService.insert(skuInfoDTO);
            } else {
                productSkuInfoService.updateById(skuInfoDTO);
            }

            //持久化SKU属性
            //新提交信息
            List<ProductAttrInfoDTO> attrInfoDTOList = skuInfoDTO.getAttrInfoList();
            if (skuInfoDODB != null) {
                //数据库原有信息
                List<ProductAttrInfoDTO> attrInfoDTODBList = productSkuAttrRelationService.selectDTOBySkuId(skuInfoDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
                //取原有数据差集数据,并删除
                ListUtils.removeAll(attrInfoDTODBList, attrInfoDTOList).forEach(productAttrInfoDTO -> {
                    productAttrInfoDTO.setGmtModified(new Date());
                    productAttrInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
                    productSkuAttrRelationService.updateById(productAttrInfoDTO);
                });
            }

            //更新或添加SKU属性
            for (ProductAttrInfoDTO attrInfoDTO : attrInfoDTOList) {
                //校验是否已经存在的关联
                if (skuInfoDODB != null && attrInfoDTO.getId() != null) {
                    //查询已存在的属性关联信息
                    ProductAttrInfoDTO attrInfoDTODB = productSkuAttrRelationService.selectDTOById(attrInfoDTO.getId());
                    if (attrInfoDTODB != null) {
                        if (attrInfoDTODB.getAttrKeyName().equals(attrInfoDTO.getAttrKeyName().trim()) &&
                                attrInfoDTODB.getAttrValueName().equals(attrInfoDTO.getAttrValueName().trim())) {
                            //如果属性相等则跳过
                            continue;
                        } else {
                            //否则删除原有关联,下面创建新的关联,为避免订单SKU属性关联显示不正确等,故不修改原有关联SKU信息,采用伪删除
                            attrInfoDTODB.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
                            attrInfoDTODB.setGmtModified(new Date());
                            productSkuAttrRelationService.updateById(attrInfoDTODB);
                        }
                    }
                }

                //判断属性名称是存在
                EntityWrapper<ProductAttrKeyDO> attrKeyWrapper = new EntityWrapper<>();
                attrKeyWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
                attrKeyWrapper.eq("id", attrInfoDTO.getAttrKeyId());
                ProductAttrKeyDO attrKeyDO = productAttrKeyService.selectOne(attrKeyWrapper);
                if (attrKeyDO == null) {
                    //事务回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "选择的属性名称不存在,请重新选择");
                }

                //处理SKU属性值信息
                ProductAttrValueDO attrValueDO = productAttrValueService.selectByName(attrInfoDTO.getAttrValueName().trim());
                if (attrValueDO == null) {
                    attrValueDO = new ProductAttrValueDO();
                    attrValueDO.setAttrKeyId(attrKeyDO.getId());
                    attrValueDO.setName(attrInfoDTO.getAttrValueName().trim());
                    attrValueDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                    attrValueDO.setGmtModified(new Date());
                    attrValueDO.setGmtCreate(new Date());
                    productAttrValueService.insert(attrValueDO);
                }

                //持久化SKU与属性关联
                ProductSkuAttrRelationDO skuAttrRelationDO = new ProductSkuAttrRelationDO();
                skuAttrRelationDO.setSkuId(skuInfoDTO.getId());
                skuAttrRelationDO.setAttrKeyId(attrKeyDO.getId());
                skuAttrRelationDO.setAttrValueId(attrValueDO.getId());
                skuAttrRelationDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                skuAttrRelationDO.setGmtModified(new Date());
                skuAttrRelationDO.setGmtCreate(new Date());
                productSkuAttrRelationService.insert(skuAttrRelationDO);
            }

            //如果为多价格定义,即持久化
            if (skuInfoDTO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                List<ProductSkuPriceMultiDO> skuPriceMultiDOList = skuInfoDTO.getSkuPriceMultiList();
                //校验原有价格定义
                EntityWrapper<ProductSkuPriceMultiDO> skuPriceMultiDOEntityWrapper = new EntityWrapper<>();
                skuPriceMultiDOEntityWrapper.eq("sku_id", skuInfoDODB.getId());
                skuPriceMultiDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
                List<ProductSkuPriceMultiDO> skuPriceMultiDODBList;
                if (skuInfoDODB != null && skuInfoDODB.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                    //获取原有多价格定义数据
                    skuPriceMultiDODBList = productSkuPriceMultiService.selectList(skuPriceMultiDOEntityWrapper);
                    //删除无用的价格定义数据
                    ListUtils.removeAll(skuPriceMultiDODBList, skuPriceMultiDOList).forEach(productSkuPriceMultiDO -> {
                        productSkuPriceMultiDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
                        productSkuPriceMultiDO.setGmtModified(new Date());
                        productSkuPriceMultiService.updateById(productSkuPriceMultiDO);
                    });
                }

                //添加或更新价格定义
                skuPriceMultiDOList.forEach(productSkuPriceMultiDO -> {
                    productSkuPriceMultiDO.setSkuId(skuInfoDTO.getId());
                    productSkuPriceMultiDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                    productSkuPriceMultiDO.setGmtModified(new Date());
                    if (skuInfoDODB == null) {
                        productSkuPriceMultiDO.setGmtCreate(new Date());
                    }
                    productSkuPriceMultiService.insertOrUpdate(productSkuPriceMultiDO);
                });
                //展示价格校对
                skuPriceMultiDODBList = productSkuPriceMultiService.selectList(skuPriceMultiDOEntityWrapper);
                for (ProductSkuPriceMultiDO skuPriceMultiDO : skuPriceMultiDODBList) {
                    showPrice = (showPrice.equals(0L) || showPrice > skuPriceMultiDO.getSalePrice()) ? skuPriceMultiDO.getSalePrice() : showPrice;
                }
            } else {
                showPrice = (showPrice.equals(0L) || showPrice > skuInfoDTO.getSalePrice()) ? skuInfoDTO.getSalePrice() : showPrice;

            }

            //持久化SKU库存
            ProductSkuStockLogDO productSkuStockLogDO = new ProductSkuStockLogDO();
            if (skuInfoDODB != null) {
                //已有的库存信息更新
                EntityWrapper<ProductSkuStockDO> skuStockDOEntityWrapper = new EntityWrapper<>();
                skuStockDOEntityWrapper.eq("sku_id", skuInfoDTO.getId());
                skuStockDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
                ProductSkuStockDO productSkuStockDO = productSkuStockService.selectOne(skuStockDOEntityWrapper);
                productSkuStockLogDO.setBoforeNum(productSkuStockDO.getNum());
                productSkuStockDO.setNum(skuInfoDTO.getStockNum());
                productSkuStockDO.setGmtModified(new Date());
                productSkuStockService.updateById(productSkuStockDO);

                //持久化库存日志
                productSkuStockLogDO.setNum(skuInfoDTO.getStockNum());
                productSkuStockLogDO.setRemark("商品更新");
            } else {
                //新增加库存信息
                ProductSkuStockDO productSkuStockDO = new ProductSkuStockDO();
                productSkuStockDO.setSkuId(skuInfoDTO.getId());
                productSkuStockDO.setNum(skuInfoDTO.getStockNum());
                productSkuStockDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                productSkuStockDO.setGmtModified(new Date());
                productSkuStockDO.setGmtCreate(new Date());
                productSkuStockService.insert(productSkuStockDO);

                //持久化库存日志
                productSkuStockLogDO.setSkuStockId(productSkuStockDO.getId());
                productSkuStockLogDO.setNum(productSkuStockDO.getNum());
                productSkuStockLogDO.setBoforeNum(0);
                productSkuStockLogDO.setRemark("库存初始化");
            }
            productSkuStockLogDO.setGmtCreate(new Date());
            productSkuStockLogDO.setGmtModified(new Date());
            productSkuStockLogDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSkuStockLogService.insert(productSkuStockLogDO);
        }

        //处理商品内容详情
        Result result = contentInfo(productInfoDTO);
        if (result.getCode() != ResponseResult.success().getCode()) {
            return result;
        }

        productInfoDTO.setShowPrice(showPrice);
        productSpuService.updateById(productInfoDTO);

        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        //获取SPU信息
        ProductInfoDTO productInfoDTO = productSpuService.selectDTOById(id);
        if (productInfoDTO == null) {
            return ResponseResult.paramNotNull("商品不存在");
        }

        //获取SKU详情
        getSKUInfo(productInfoDTO);

        //获取详情内容
        String spuDescWebPath = CommonFolderConstant.getProductSpuDescWebPath(productInfoDTO.getId(), productInfoDTO.getId().toString()) + ".html";
        productInfoDTO.setContentUrl(spuDescWebPath);

        return ResponseResult.success(productInfoDTO);
    }

    @Override
    public Result listPage(Pager pager) {
        Integer storeId = (Integer) pager.getParamMap().get("storeId");
        ProductInfoDTO spu = new ProductInfoDTO();
        spu.setStoreId(storeId);
        spu.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<ProductInfoDTO> productInfoDTOList = productSpuService.selectDTOList(pager, spu);
        //获取SKU详情
        productInfoDTOList.forEach(this::getSKUInfo);
        pager.setRecords(productInfoDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public Result search(ProductSearchDTO productSearchDTO) {
        //整合所有子分类ID
        if (productSearchDTO.getCategoryId() != null) {
            List<Integer> categoryIdList = productCategoryService.getChildId(productSearchDTO.getCategoryId());
            categoryIdList.add(productSearchDTO.getCategoryId());
            productSearchDTO.setCategoryIdList(categoryIdList);
        }

        //整合市区域所有门店ID
        if (productSearchDTO.getProvinceCode() != null || productSearchDTO.getCityCode() != null || productSearchDTO.getAreaCode() != null) {
            List<Integer> storeIdList = userStoreProfilService.selectIdByDistrict(productSearchDTO.getProvinceCode(), productSearchDTO.getCityCode(), productSearchDTO.getAreaCode());
            if (CollectionUtils.isEmpty(storeIdList)) {
                return ResponseResult.successMsg("所在区域暂时无商品");
            } else {
                productSearchDTO.setStoreIdList(storeIdList);
            }
        }

        //查询当前商店商品
        if (productSearchDTO.getStoreId() != null) {
            if (CollectionUtils.isNotEmpty(productSearchDTO.getStoreIdList())) {
                productSearchDTO.getStoreIdList().add(productSearchDTO.getStoreId());
            } else {
                productSearchDTO.setStoreIdList(new ArrayList<Integer>() {{
                    add(productSearchDTO.getStoreId());
                }});
            }
        }

        //筛选查询商品
        Pager pager = productSearchDTO.getPager();
        List<ProductSpuDO> spuDOList = productSpuService.selectDOList(pager, productSearchDTO, ProductStateEnum.IsShelves.ON.code, CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<ProductBaseInfoDTO> productBaseInfoDTOList = new ArrayList<>(spuDOList.size());
        spuDOList.forEach(productSpuDO -> {
            ProductBaseInfoDTO baseInfoDTO = new ProductBaseInfoDTO();
            baseInfoDTO.setSpuId(productSpuDO.getId());
            baseInfoDTO.setTitle(productSpuDO.getTitle());
            baseInfoDTO.setPrice(productSpuDO.getShowPrice());
            //查询总销量
            baseInfoDTO.setSalesVolume(productSalesVolumeService.selectTotalSalesVolumeBySpuId(productSpuDO.getId()));
            //查询商品主图
            String imgName = productSpuImgService.selectMainBySpuID(productSpuDO.getId());
            baseInfoDTO.setMainImgUrl(CommonFolderConstant.getProductSpuImgWebPath(productSpuDO.getId(), imgName));
            //查询商店信息
            UserStoreProfilDTO storeProfilDTO = userStoreProfilService.selectDTOById(productSpuDO.getStoreId());
            baseInfoDTO.setStoreId(storeProfilDTO.getId());
            baseInfoDTO.setStoreName(storeProfilDTO.getCompanyName());
            baseInfoDTO.setProvinceName(storeProfilDTO.getProvinceName());
            baseInfoDTO.setCityName(storeProfilDTO.getCityName());
            baseInfoDTO.setAreaName(storeProfilDTO.getAreaName());
            productBaseInfoDTOList.add(baseInfoDTO);
        });
        pager.setRecords(productBaseInfoDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoByIdAndStoreId(Integer id, Integer storeId) {
        //获取SPU信息
        ProductInfoDTO productInfoDTO = productSpuService.selectDTOById(id);
        if (productInfoDTO == null || !storeId.equals(productInfoDTO.getStoreId())) {
            return ResponseResult.paramNotNull("商品不存在");
        }

        //获取SKU详情
        getSKUInfo(productInfoDTO);

        //获取详情内容
        String spuInfoHtmlPath = CommonFolderConstant.getProductSpuDescPath(productInfoDTO.getId()) + productInfoDTO.getId() + ".html";
        String spuInfoHtml = "";
        try {
            spuInfoHtml = FileUtils.readFileToString(new File(spuInfoHtmlPath), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String spuInfoBodyHtml = "";
        if (StringUtils.isNotBlank(spuInfoHtml)) {
            spuInfoBodyHtml = Jsoup.parse(spuInfoHtml).body().html();
        }
        productInfoDTO.setContent(spuInfoBodyHtml);

        return ResponseResult.success(productInfoDTO);
    }

    @Override
    public Result onShelves(Integer id, Integer storeId) {
        Integer num = productSpuService.updateShelves(id, storeId, ProductStateEnum.IsShelves.ON.code);
        if (num > 0) {
            productSkuInfoService.updateShelvesBySpuId(id, ProductStateEnum.IsShelves.ON.code);
        }
        return ResponseResult.success();
    }

    @Override
    public Result offShelves(Integer id, Integer storeId) {
        Integer num = productSpuService.updateShelves(id, storeId, ProductStateEnum.IsShelves.OFF.code);
        if (num > 0) {
            productSkuInfoService.updateShelvesBySpuId(id, ProductStateEnum.IsShelves.OFF.code);
        }
        return ResponseResult.success();
    }

    /***
     * 获取SPU详情信息
     * @param productInfoDTO
     */
    private void getSKUInfo(ProductInfoDTO productInfoDTO) {
        //获取SKU信息
        List<ProductSkuInfoDTO> skuInfoDTOList = productSkuInfoService.selectBySpuId(productInfoDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
        skuInfoDTOList.forEach(productSkuInfoDTO -> {
            //获取SKU属性
            List<ProductAttrInfoDTO> attrInfoDTOList = productSkuAttrRelationService.selectDTOBySkuId(productSkuInfoDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
            productSkuInfoDTO.setAttrInfoList(attrInfoDTOList);

            //获取SKU库存
            EntityWrapper<ProductSkuStockDO> skuStockDOEntityWrapper = new EntityWrapper<>();
            skuStockDOEntityWrapper.eq("sku_id", productSkuInfoDTO.getId());
            skuStockDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
            ProductSkuStockDO skuStockDO = productSkuStockService.selectOne(skuStockDOEntityWrapper);
            productSkuInfoDTO.setStockId(skuStockDO.getId());
            productSkuInfoDTO.setStockNum(skuStockDO.getNum());

            //如果为多价格类型，则获取多价格
            if (productSkuInfoDTO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                EntityWrapper<ProductSkuPriceMultiDO> skuPriceMultiDOEntityWrapper = new EntityWrapper<>();
                skuPriceMultiDOEntityWrapper.eq("sku_id", productSkuInfoDTO.getId());
                skuPriceMultiDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
                List<ProductSkuPriceMultiDO> skuPriceMultiDOList = productSkuPriceMultiService.selectList(skuPriceMultiDOEntityWrapper);
                productSkuInfoDTO.setSkuPriceMultiList(skuPriceMultiDOList);
            }
        });
        productInfoDTO.setSkuInfoList(skuInfoDTOList);

        //查询总销量
        productInfoDTO.setSalesVolume(productSalesVolumeService.selectTotalSalesVolumeBySpuId(productInfoDTO.getId()));

        //获取运费
        ProductFreightTemplateDO freightTemplateDO = productFreightTemplateService.selectById(productInfoDTO.getFreightTemplateId());
        productInfoDTO.setFreightPrice(freightTemplateDO.getPrice());

        //获取图片集
        List<ProductSpuImgDTO> imgList = productSpuImgService.selectDTOBySpuId(productInfoDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
        productInfoDTO.setImgList(imgList);
    }

    /***
     * 处理商品详情内容
     * @param productInfoDTO
     * @return
     */
    private final static String script = "<script type=\"text/javascript\">var message=document.body.scrollHeight;window.parent.postMessage(message,\"*\");</script>";

    private Result contentInfo(ProductInfoDTO productInfoDTO) {
        String contentHtml = productInfoDTO.getContent();
        //获取所有Img标签
        List<String> imgTags = Jsoup.parse(contentHtml).select("img[src]").eachAttr("src");
        //资源文件夹
        String sourceDirPath = CommonFolderConstant.getProductSpuDescPath(productInfoDTO.getId());
        File sourceDirPathFile = new File(sourceDirPath);

        //本地图片集,排除已经存在的图片使用
        List<String> fileNameList = null;
        if (sourceDirPathFile.exists()) {
            Collection<File> files = FileUtils.listFiles(sourceDirPathFile, new String[]{"jpg", "JPG", "jpeg", "JPEG", "png", "PNG"}, false);
            fileNameList = files.stream().map(File::getName).collect(Collectors.toList());
        }

        //剪切图片到相关商品文件夹
        for (String imgUrl : imgTags) {
            //判断图片是否已经存在文件夹中
            if (fileNameList != null) {
                String tempName = imgUrl.substring(imgUrl.lastIndexOf("/" + 1), imgUrl.length());
                if (fileNameList.contains(tempName)) {
                    fileNameList.remove(tempName);
                    continue;
                }
            }

            //进行图片剪切
            String fileName;
            try {
                fileName = FileUtils.copyFileByUrl(imgUrl, sourceDirPath);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseResult.serverError();
            }

            contentHtml = contentHtml.replaceAll(imgUrl, CommonFolderConstant.getProductSpuDescWebPath(productInfoDTO.getId(), fileName));
        }
        try {
            FileUtils.writeStringToFile(new File(sourceDirPath + productInfoDTO.getId() + ".html"), spuTemplate(productInfoDTO.getName(), contentHtml), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.serverError();
        }

        //删除无用图片
        if (fileNameList != null) {
            fileNameList.forEach(fileName -> FileUtils.delete(sourceDirPath + fileName));
        }

        return ResponseResult.success();
    }

    private String spuTemplate(String spuName, String content) {
        return "<!DOCTYPE html><html lang=\"zh\">" +
                "<head><meta charset=\"UTF-8\"><title>" + spuName + "</title></head><body>" + content +
                "<script type=\"text/javascript\">window.onload=function(){var message=document.body.scrollHeight;window.parent.postMessage(message,\"*\");}" +
                "</script></body></html>";
    }
}
