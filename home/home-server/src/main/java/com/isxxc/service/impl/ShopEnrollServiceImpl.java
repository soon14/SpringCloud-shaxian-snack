package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ShopEnrollDAO;
import com.isxxc.domain.entity.ShopEnrollDO;
import com.isxxc.service.ShopEnrollService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.FileUtils;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopEnrollServiceImpl extends ServiceImpl<ShopEnrollDAO, ShopEnrollDO> implements ShopEnrollService {

    @Resource
    private ShopEnrollDAO shopEnrollDAO;

    @Override
    public Result save(ShopEnrollDO shopEnrollDO) {
        shopEnrollDO.setIsDeleted(CommonStateEnum.IsDisable.NOT_DISABLE.code);
        save(shopEnrollDO);
        if (StringUtils.isNotBlank(shopEnrollDO.getBankCardImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getBankCardImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getBusinessLicenseImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getBusinessLicenseImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getHotMenuImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getHotMenuImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getIdentityCardImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getIdentityCardImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getShopFacadeImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getShopFacadeImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getShopHallImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getShopHallImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getIdentityCardBackImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getIdentityCardBackImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getBankCardBackImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getBankCardBackImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getCashierDeskImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getCashierDeskImg()), CommonFolderConstant.getShopEnrollPath());
        }
        if (StringUtils.isNotBlank(shopEnrollDO.getCateringServiceLicenseImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopEnrollDO.getCateringServiceLicenseImg()), CommonFolderConstant.getShopEnrollPath());
        }
        return ResponseResult.success();
    }
}
