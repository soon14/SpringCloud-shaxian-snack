package com.isxxc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductSpuDAO;
import com.isxxc.domain.dto.ProductInfoDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.service.ProductSpuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 商品SPU信息 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuDAO, ProductSpuDO> implements ProductSpuService {

    @Resource
    private ProductSpuDAO productSpuDAO;

    @Override
    public ProductInfoDTO selectDTOById(Integer id) {
        return productSpuDAO.selectDTOById(id);
    }

    @Override
    public List<ProductInfoDTO> selectDTOList(Page page, ProductInfoDTO productInfoDTO) {
        return productSpuDAO.selectDTOList(page, productInfoDTO);
    }

    @Override
    public List<ProductSpuDO> selectDOList(Page page, ProductSearchDTO productSearchDTO, Integer isShelves, Integer isDeleted) {
        return productSpuDAO.selectDOList(page, productSearchDTO, isShelves, isDeleted);
    }

    @Override
    public Integer updateShelves(Integer id, Integer storeId, int shelvesCode) {
        return productSpuDAO.updateShelves(id, storeId, shelvesCode);
    }
}
