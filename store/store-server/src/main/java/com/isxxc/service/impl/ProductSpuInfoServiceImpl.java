package com.isxxc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ProductSpuInfoDAO;
import com.isxxc.domain.dto.ProductInformationDTO;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.service.ProductSpuInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品SPU信息 服务实现类
 * </p>
 *
 * @author yk
 * @since 2018-05-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpuInfoServiceImpl extends ServiceImpl<ProductSpuInfoDAO, ProductSpuDO> implements ProductSpuInfoService {

    @Resource
    private ProductSpuInfoDAO productSpuInfoDAO;

    @Override
    public ProductInformationDTO selectDTOById(Integer id) {
        return productSpuInfoDAO.selectDTOById(id);
    }

    @Override
    public List<ProductInformationDTO> selectDTOList(Page page, ProductInformationDTO productInformationDTO) { return productSpuInfoDAO.selectDTOList(page,productInformationDTO);
    }

    @Override
    public List<ProductSpuDO> selectDOList(Page page, ProductSearchDTO productSearchDTO, Integer isShelves, Integer isDeleted) { return productSpuInfoDAO.selectDOList(page, productSearchDTO, isShelves, isDeleted);
    }


}
