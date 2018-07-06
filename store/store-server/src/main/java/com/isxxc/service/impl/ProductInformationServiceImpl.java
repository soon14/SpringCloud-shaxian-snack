package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.FileUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.ProductStateEnum;
import com.isxxc.domain.dto.*;
import com.isxxc.domain.entity.ProductFreightTemplateDO;
import com.isxxc.domain.entity.ProductSkuPriceMultiDO;
import com.isxxc.domain.entity.ProductSkuStockDO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品详细信息 服务实现类
 *
 * @author yk
 * @date 2018/05/17
 * */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInformationServiceImpl implements ProductInformationService {

    @Resource
    private ProductSpuInfoService productSpuInfoService;

    @Resource
    private ProductSkuInfoService productSkuInfoService;

    @Resource
    private ProductSkuPriceMultiService productSkuPriceMultiService;

    @Resource
    private ProductSpuImgService productSpuImgService;


    @Resource
    private ProductSkuAttrRelationService productSkuAttrRelationService;

    @Resource
    private ProductSkuStockService productSkuStockService;

    @Resource
    private ProductSalesVolumeService productSalesVolumeService;

    @Resource
    private ProductFreightTemplateService productFreightTemplateService;

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private UserStoreProfilService userStoreProfilService;


    @Override
    public Result getInfoById(Integer id) {
        //获取SPU信息
        ProductInformationDTO productInformationDTO = productSpuInfoService.selectDTOById(id);
        if (productInformationDTO == null) {
            return ResponseResult.paramNotNull("商品不存在");
        }

        //获取SKU详情
        getSKUInfo(productInformationDTO);

        //获取详情内容
        String spuDescWebPath = CommonFolderConstant.getProductSpuDescWebPath(productInformationDTO.getId(), productInformationDTO.getId().toString()) + ".html";
        productInformationDTO.setContentUrl(spuDescWebPath);

        return ResponseResult.success(productInformationDTO);
    }

    @Override
    public Result listPage(Pager pager) {
        Integer storeId = (Integer) pager.getParamMap().get("storeId");
        ProductInformationDTO spu = new ProductInformationDTO();
        spu.setStoreId(storeId);
        spu.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<ProductInformationDTO> productInformationDTOList = productSpuInfoService.selectDTOList(pager, spu);
        //获取SKU详情
        productInformationDTOList.forEach(this::getSKUInfo);
        pager.setRecords(productInformationDTOList);
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
        List<ProductSpuDO> spuDOList = productSpuInfoService.selectDOList(pager, productSearchDTO, ProductStateEnum.IsShelves.ON.code, CommonStateEnum.IsDeleted.NOT_DELETED.code);
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



    /***
     * 获取SPU详情信息
     * @param productInformationDTO
     */
    private void getSKUInfo(ProductInformationDTO productInformationDTO) {
        //获取SKU信息
        List<ProductSkuInfoDTO> skuInfoDTOList = productSkuInfoService.selectBySpuId(productInformationDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
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
        productInformationDTO.setSkuInfoList(skuInfoDTOList);

        //查询总销量
        productInformationDTO.setSalesVolume(productSalesVolumeService.selectTotalSalesVolumeBySpuId(productInformationDTO.getId()));

        //获取运费
        ProductFreightTemplateDO freightTemplateDO = productFreightTemplateService.selectById(productInformationDTO.getFreightTemplateId());
        productInformationDTO.setFreightPrice(freightTemplateDO.getPrice());

        //获取图片集
        List<ProductSpuImgDTO> imgList = productSpuImgService.selectDTOBySpuId(productInformationDTO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
        productInformationDTO.setImgList(imgList);
    }

    /***
     * 处理商品详情内容
     * @param productInformationDTO
     * @return
     */
    private final static String script = "<script type=\"text/javascript\">var message=document.body.scrollHeight;window.parent.postMessage(message,\"*\");</script>";

    private Result contentInfo(ProductInformationDTO productInformationDTO) {
        String contentHtml = productInformationDTO.getContent();
        //获取所有Img标签
        List<String> imgTags = Jsoup.parse(contentHtml).select("img[src]").eachAttr("src");
        //资源文件夹
        String sourceDirPath = CommonFolderConstant.getProductSpuDescPath(productInformationDTO.getId());
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

            contentHtml = contentHtml.replaceAll(imgUrl, CommonFolderConstant.getProductSpuDescWebPath(productInformationDTO.getId(), fileName));
        }
        try {
            FileUtils.writeStringToFile(new File(sourceDirPath + productInformationDTO.getId() + ".html"), spuTemplate(productInformationDTO.getName(), contentHtml), "UTF-8");
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
