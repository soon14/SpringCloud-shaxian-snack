package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ShopTransferCountDAO;
import com.isxxc.dao.UserShopCountDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ShopTransferCountDTO;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.entity.ShopTransferDO;
import com.isxxc.domain.entity.UserShopProfilImgDO;
import com.isxxc.service.ShopTransferCountService;
import com.isxxc.service.UserShopCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTransferCountServiceImpl extends ServiceImpl<ShopTransferCountDAO, ShopTransferDO> implements ShopTransferCountService {

    @Resource
    private ShopTransferCountDAO shopTransferCountDAO;

    @Override
    public Result countAttornShop(ShopTransferCountDTO shopTransferCountDTO) {
        Integer integer = shopTransferCountDAO.countAttornShop(shopTransferCountDTO);
            return ResponseResult.success(integer);
    }

    @Override
    public Result countRentingShop(ShopTransferCountDTO shopTransferCountDTO) {
        Integer integer = shopTransferCountDAO.countRentingShop(shopTransferCountDTO);
            return ResponseResult.success(integer);
    }

    @Override
    public Pager listPage(Pager pager) {
        List<UserAccountOtherDTO> userAccountOtherDTOS = shopTransferCountDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(userAccountOtherDTOS);
        return pager;
    }
}

