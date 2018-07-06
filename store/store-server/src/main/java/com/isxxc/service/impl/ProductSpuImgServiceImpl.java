package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductSpuImgDAO;
import com.isxxc.domain.dto.ProductSpuImgDTO;
import com.isxxc.domain.entity.ProductSpuImgDO;
import com.isxxc.service.ProductSpuImgService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * 商品图片 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpuImgServiceImpl extends ServiceImpl<ProductSpuImgDAO, ProductSpuImgDO> implements ProductSpuImgService {

    @Resource
    private ProductSpuImgDAO productSpuImgDAO;

    @Override
    public List<ProductSpuImgDTO> selectDTOBySpuId(Integer spuId, Integer isDeleted) {
        return productSpuImgDAO.selectDTOBySpuId(spuId, isDeleted);
    }

    @Override
    public String selectMainBySpuID(Integer spuId) {
        return productSpuImgDAO.selectMainBySpuID(spuId);
    }
}
