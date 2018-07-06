package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ProductBrandDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductBrandDTO;
import com.isxxc.domain.entity.ProductBrandDO;
import com.isxxc.service.ProductBrandService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.FileUtils;

/**
 * <p>
 * 商品品牌 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandDAO, ProductBrandDO> implements ProductBrandService {

    @Override
    public Result save(ProductBrandDO productBrand) {
        if (StringUtils.isNotBlank(productBrand.getLogo())) {
            if (FileUtils.exists(CommonFolderConstant.getImageTempPath(productBrand.getLogo()))) {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(productBrand.getLogo()), CommonFolderConstant.getProductBrandPath());
            } else {
                return ResponseResult.paramNotNull("Logo图片已经失效,请重新上传");
            }
        }
        productBrand.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return insert(productBrand) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(ProductBrandDO productBrand) {
        ProductBrandDO productBrandDB = selectById(productBrand.getId());
        if (StringUtils.isNotBlank(productBrand.getLogo()) && !productBrand.getLogo().equals(productBrandDB.getLogo())) {
            if (new File(CommonFolderConstant.getImageTempPath(productBrand.getLogo())).exists()) {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(productBrand.getLogo()), CommonFolderConstant.getProductBrandPath());
            } else {
                return ResponseResult.paramNotNull("Logo图片已经失效,请重新上传");
            }
        } else if (StringUtils.isNotBlank(productBrandDB.getLogo()) && StringUtils.isBlank(productBrand.getLogo())) {
            FileUtils.delete(CommonFolderConstant.getProductBrandPath() + productBrandDB.getLogo());
        }
        return updateById(productBrand) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Pager page) {
        EntityWrapper<ProductBrandDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_id", page.getParamMap().get("storeId"));
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        page = (Pager) selectPage(page, entityWrapper);
        List<ProductBrandDO> productBrandDOList = page.getRecords();
        List<ProductBrandDTO> productBrandDTOList = new ArrayList<>(productBrandDOList.size());
        productBrandDOList.forEach(productBrandDODB -> productBrandDTOList.add(new ProductBrandDTO(productBrandDODB)));
        page.setRecords(productBrandDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result list(ProductBrandDO productBrand) {
        EntityWrapper<ProductBrandDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("store_id", productBrand.getStoreId());
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<ProductBrandDO> productBrandDOList = selectList(entityWrapper);
        List<ProductBrandDTO> productBrandDTOList = new ArrayList<>(productBrandDOList.size());
        productBrandDOList.forEach(productBrandDODB -> productBrandDTOList.add(new ProductBrandDTO(productBrandDODB)));
        return ResponseResult.success(productBrandDTOList);
    }

    @Override
    public Result delById(Integer id) {
        ProductBrandDO productBrandDO = selectById(id);
        productBrandDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(productBrandDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
