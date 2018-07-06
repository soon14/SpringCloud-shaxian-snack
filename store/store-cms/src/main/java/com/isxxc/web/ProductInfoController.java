package com.isxxc.web;


import com.isxxc.constant.ProductStateEnum;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductAttrInfoDTO;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSkuInfoDTO;
import com.isxxc.domain.entity.ProductSkuPriceMultiDO;
import com.isxxc.service.feign.store.ProductInfoService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 产品信息 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;

    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result save(@RequestBody ProductInfoDTO productInfoDTO, Integer storeId) {
        productInfoDTO.setStoreId(storeId);
        Result result = baseVerify(productInfoDTO);
        if (result.getCode() != ResponseResult.success().getCode()) {
            return result;
        }
        if (CollectionUtils.isEmpty(productInfoDTO.getSkuInfoList())) {
            return ResponseResult.paramNotNull("SKU信息不能为空");
        } else {
            for (ProductSkuInfoDTO skuInfoDTO : productInfoDTO.getSkuInfoList()) {
                if (skuInfoDTO.getStockNum() == null || skuInfoDTO.getStockNum() <= 0) {
                    return ResponseResult.paramNotNull("SKU库存不能小于或等于0");
                }
                if (skuInfoDTO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                    if (CollectionUtils.isEmpty(skuInfoDTO.getSkuPriceMultiList())) {
                        return ResponseResult.paramNotNull("商品价格不能为空");
                    }
                    for (int i = 0; i < skuInfoDTO.getSkuPriceMultiList().size(); i++) {
                        ProductSkuPriceMultiDO priceMultiDO = skuInfoDTO.getSkuPriceMultiList().get(i);
                        if (priceMultiDO.getMinNum() == null || priceMultiDO.getMinNum() < 0) {
                            return ResponseResult.paramNotNull("最小购买量不能为空或小于0");
                        }
                        if (priceMultiDO.getMaxNum() == null || priceMultiDO.getMaxNum() < 0) {
                            return ResponseResult.paramNotNull("最大购买量不能为空或小于0");
                        }
                        if (priceMultiDO.getMinNum() > priceMultiDO.getMaxNum()) {
                            return ResponseResult.paramNotNull("最小购买量不能大于最大购买量");
                        }
                        if (priceMultiDO.getSalePrice() == null || priceMultiDO.getSalePrice() < 0) {
                            return ResponseResult.paramNotNull("商品销售价格不能为空或小于0");
                        }
                        if (priceMultiDO.getMarketPrice() == null || priceMultiDO.getMarketPrice() < 0) {
                            return ResponseResult.paramNotNull("商品市场价格不能为空或小于0");
                        }

                        for (int j = i + 1; j < skuInfoDTO.getSkuPriceMultiList().size(); j++) {
                            ProductSkuPriceMultiDO priceMultiDOTemp = skuInfoDTO.getSkuPriceMultiList().get(j);
                            if (!(priceMultiDO.getMaxNum() < priceMultiDOTemp.getMinNum() && priceMultiDO.getMaxNum() < priceMultiDOTemp.getMaxNum() ||
                                    priceMultiDO.getMinNum() > priceMultiDOTemp.getMinNum() && priceMultiDO.getMinNum() > priceMultiDOTemp.getMaxNum())) {
                                return ResponseResult.paramNotNull("购买量: [ " + priceMultiDO.getMinNum() + " ~ " + priceMultiDO.getMaxNum() + " ] 与" +
                                        " [ " + priceMultiDOTemp.getMinNum() + " ~ " + priceMultiDOTemp.getMaxNum() + " ] 已经产生重复");
                            }
                        }
                    }
                } else if (skuInfoDTO.getSalePrice() == null || skuInfoDTO.getSalePrice() < 0) {
                    return ResponseResult.paramNotNull("商品销售价格不能小于0");
                }
                if (CollectionUtils.isEmpty(skuInfoDTO.getAttrInfoList())) {
                    return ResponseResult.paramNotNull("SKU属性不能为空");
                } else {
                    for (ProductAttrInfoDTO attrInfoDTO : skuInfoDTO.getAttrInfoList()) {
                        if (attrInfoDTO.getAttrKeyId() == null) {
                            return ResponseResult.paramNotNull("请选择SKU属性名称");
                        }
                        if (StringUtils.isBlank(attrInfoDTO.getAttrValueName())) {
                            return ResponseResult.paramNotNull("SKU属性值不能为空");
                        }
                    }
                }
            }
        }
        return productInfoService.save(productInfoDTO);
    }

    /***
     * 更新商品信息
     * @param productInfoDTO
     * @param storeId
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result updateInfo(@RequestBody ProductInfoDTO productInfoDTO, Integer storeId) {
        productInfoDTO.setStoreId(storeId);
        if (productInfoDTO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        Result result = baseVerify(productInfoDTO);
        if (result.getCode() != ResponseResult.success().getCode()) {
            return result;
        }
        if (CollectionUtils.isEmpty(productInfoDTO.getSkuInfoList())) {
            return ResponseResult.paramNotNull("SKU信息不能为空");
        } else {
            for (ProductSkuInfoDTO skuInfoDTO : productInfoDTO.getSkuInfoList()) {
                if (skuInfoDTO.getStockNum() == null || skuInfoDTO.getStockNum() <= 0) {
                    return ResponseResult.paramNotNull("SKU库存不能小于或等于0");
                }
                if (skuInfoDTO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                    if (CollectionUtils.isEmpty(skuInfoDTO.getSkuPriceMultiList())) {
                        return ResponseResult.paramNotNull("商品价格不能为空");
                    }
                    for (int i = 0; i < skuInfoDTO.getSkuPriceMultiList().size(); i++) {
                        ProductSkuPriceMultiDO priceMultiDO = skuInfoDTO.getSkuPriceMultiList().get(i);

                        if (priceMultiDO.getMinNum() == null || priceMultiDO.getMinNum() < 0) {
                            return ResponseResult.paramNotNull("最小购买量不能为空或小于0");
                        }
                        if (priceMultiDO.getMaxNum() == null || priceMultiDO.getMaxNum() < 0) {
                            return ResponseResult.paramNotNull("最大购买量不能为空或小于0");
                        }
                        if (priceMultiDO.getMinNum() > priceMultiDO.getMaxNum()) {
                            return ResponseResult.paramNotNull("最小购买量不能大于最大购买量");
                        }
                        if (priceMultiDO.getSalePrice() == null || priceMultiDO.getSalePrice() < 0) {
                            return ResponseResult.paramNotNull("商品销售价格不能为空或小于0");
                        }
                        if (priceMultiDO.getMarketPrice() == null || priceMultiDO.getMarketPrice() < 0) {
                            return ResponseResult.paramNotNull("商品市场价格不能为空或小于0");
                        }

                        for (int j = i + 1; j < skuInfoDTO.getSkuPriceMultiList().size(); j++) {
                            ProductSkuPriceMultiDO priceMultiDOTemp = skuInfoDTO.getSkuPriceMultiList().get(j);
                            if (!(priceMultiDO.getMaxNum() < priceMultiDOTemp.getMinNum() && priceMultiDO.getMaxNum() < priceMultiDOTemp.getMaxNum() ||
                                    priceMultiDO.getMinNum() > priceMultiDOTemp.getMinNum() && priceMultiDO.getMinNum() > priceMultiDOTemp.getMaxNum())) {
                                return ResponseResult.paramNotNull("购买量: [ " + priceMultiDO.getMinNum() + " ~ " + priceMultiDO.getMaxNum() + " ] 与" +
                                        " [ " + priceMultiDOTemp.getMinNum() + " ~ " + priceMultiDOTemp.getMaxNum() + " ] 已经生产重复");
                            }
                        }
                    }
                } else if (skuInfoDTO.getSalePrice() == null || skuInfoDTO.getSalePrice() < 0) {
                    return ResponseResult.paramNotNull("商品价格不能小于0");
                }
                if (CollectionUtils.isEmpty(skuInfoDTO.getAttrInfoList())) {
                    return ResponseResult.paramNotNull("SKU属性不能为空");
                } else {
                    for (ProductAttrInfoDTO attrInfoDTO : skuInfoDTO.getAttrInfoList()) {
                        if (attrInfoDTO.getAttrKeyId() == null) {
                            return ResponseResult.paramNotNull("请选择SKU属性名称");
                        }
                        if (StringUtils.isBlank(attrInfoDTO.getAttrValueName())) {
                            return ResponseResult.paramNotNull("SKU属性值不能为空");
                        }
                    }
                }
            }
        }
        return productInfoService.updateInfo(productInfoDTO);
    }

    /***
     * 获取商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndStoreId", method = RequestMethod.GET)
    public Result getInfoByIdAndStoreId(Integer id, Integer storeId) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return productInfoService.getInfoByIdAndStoreId(id, storeId);
    }

    /***
     * 获取商品列表
     * @param pager
     * @param storeId
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer storeId) {
        if (storeId == null) {
            return ResponseResult.paramNotNull("商店ID不能为空");
        }
        pager.putParam("storeId", storeId);
        return productInfoService.listPage(pager);
    }

    /***
     * 商品上架
     * @param id
     * @return
     */
    @RequestMapping(value = "onShelves", method = RequestMethod.POST)
    public Result onShelves(Integer id, Integer storeId) {
        if (id == null) {
            return ResponseResult.paramNotNull("商品SPUID不能为空");
        }
        return productInfoService.onShelves(id, storeId);
    }

    /***
     * 商品下架
     * @param id
     * @return
     */
    @RequestMapping(value = "offShelves", method = RequestMethod.POST)
    public Result offShelves(Integer id, Integer storeId) {
        if (id == null) {
            return ResponseResult.paramNotNull("商品SPUID不能为空");
        }
        return productInfoService.offShelves(id, storeId);
    }

    private Result baseVerify(ProductInfoDTO productInfoDTO) {
        if (productInfoDTO.getCategoryId() == null) {
            return ResponseResult.paramNotNull("请选择分类");
        }
        if (productInfoDTO.getBrandId() == null) {
            return ResponseResult.paramNotNull("请选择品牌");
        }
        if (StringUtils.isBlank(productInfoDTO.getName())) {
            return ResponseResult.paramNotNull("请输入商品名称");
        }
        if (StringUtils.isBlank(productInfoDTO.getTitle())) {
            return ResponseResult.paramNotNull("请输入商品标题");
        }
        if (StringUtils.isBlank(productInfoDTO.getContent())) {
            return ResponseResult.paramNotNull("请填写商品详情");
        }
        if (productInfoDTO.getFreightTemplateId() == null) {
            return ResponseResult.paramNotNull("请选择运费模版");
        }
        if (CollectionUtils.isEmpty(productInfoDTO.getImgList())) {
            return ResponseResult.paramNotNull("图片不能为空");
        }
        return ResponseResult.success();
    }
}
