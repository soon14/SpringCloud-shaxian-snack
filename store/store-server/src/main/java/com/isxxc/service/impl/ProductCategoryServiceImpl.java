package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ProductCategoryDAO;
import com.isxxc.domain.dto.ProductCategoryDTO;
import com.isxxc.domain.entity.ProductCategoryDO;
import com.isxxc.service.ProductCategoryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDAO, ProductCategoryDO> implements ProductCategoryService {

    @Resource
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public Result save(ProductCategoryDO productCategoryDO) {
        productCategoryDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return insert(productCategoryDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listTree(ProductCategoryDTO productCategoryDTO) {
        productCategoryDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<ProductCategoryDTO> productCategoryDTOList = productCategoryDAO.selectDTOList(productCategoryDTO);
        List<ProductCategoryDTO> parentProductCategoryDTOList = productCategoryDTOList.stream().filter(productCategoryDTODB -> productCategoryDTODB.getParentId() == 0).collect(Collectors.toList());
        parentProductCategoryDTOList.forEach(parentProductCategoryDTODB -> buildTree(parentProductCategoryDTODB, productCategoryDTOList));
        Collections.sort(parentProductCategoryDTOList, new Comparator<ProductCategoryDTO>() {
            @Override
            public int compare(ProductCategoryDTO o1, ProductCategoryDTO o2) {
                int i = o1.getSort() - o2.getSort();
                if(i == 0){
                    return o1.getId() - o2.getId();
                }
                return i;
            }
        });

        return ResponseResult.success(parentProductCategoryDTOList);
    }

    @Override
    public Result updateInfo(ProductCategoryDO productCategoryDO) {
        return updateById(productCategoryDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public List<Integer> getChildId(Integer categoryId) {
        List<Integer> childIdList = new ArrayList<>();
        buildChildId(childIdList, categoryId);
        return childIdList;
    }

    private void buildChildId(List<Integer> categoryIdList, Integer parentId) {
        List<Integer> childIdList = productCategoryDAO.selectChildId(parentId);
        childIdList.forEach(childId -> {
            buildChildId(categoryIdList, childId);
            categoryIdList.add(childId);
        });
    }

    /***
     * 构建树
     * @param productCategoryDTO
     * @param productCategoryDTOList
     */
    private void buildTree(ProductCategoryDTO productCategoryDTO, List<ProductCategoryDTO> productCategoryDTOList) {
        productCategoryDTOList.forEach(productCategoryDTODB -> {
            if (productCategoryDTO.getId().equals(productCategoryDTODB.getParentId())) {
                if (productCategoryDTO.getChildList() == null) {
                    productCategoryDTO.setChildList(new ArrayList<ProductCategoryDTO>() {{
                        add(productCategoryDTODB);
                    }});
                } else {
                    productCategoryDTO.getChildList().add(productCategoryDTODB);
                }
                buildTree(productCategoryDTODB, productCategoryDTOList);
            }
        });
    }
}
