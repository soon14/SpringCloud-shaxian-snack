package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ShopTenantAuditLogDAO;
import com.isxxc.dao.ShopTenantDAO;
import com.isxxc.domain.dto.ShopTenantDTO;
import com.isxxc.domain.entity.ShopTenantAuditLogDO;
import com.isxxc.domain.entity.ShopTenantDO;
import com.isxxc.service.ShopTenantService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.MyBeanUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTenantServiceImpl extends ServiceImpl<ShopTenantDAO, ShopTenantDO> implements ShopTenantService {

    @Resource
    private ShopTenantAuditLogDAO shopTenantAuditLogDAO;

    @Override
    public Result save(ShopTenantDO shopTenantDO) {
        shopTenantDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        shopTenantDO.setPublishTime(new Date());
        shopTenantDO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        shopTenantDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        shopTenantDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        return insert(shopTenantDO) ? ResponseResult.success("添加成功,请耐心等待审核,审核后自动发布") : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(ShopTenantDO shopTenantDO) {
        shopTenantDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        shopTenantDO.setPublishTime(new Date());
        shopTenantDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        ShopTenantDO shopTransferDODB = selectById(shopTenantDO.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(shopTenantDO, shopTransferDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(shopTenantDO) ? ResponseResult.success("更新成功,请耐心等待审核,审核后自动发布") : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Page page, ShopTenantDTO shopTenantDTO) {
        EntityWrapper<ShopTenantDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        entityWrapper.eq("audit_state", CommonStateEnum.AuditState.PASSED.code);
        entityWrapper.eq("puslish_state", CommonStateEnum.PublishState.PUBLISHED.code);
        return baseListPage(page, shopTenantDTO, entityWrapper);
    }

    @Override
    public Result getAuditLogByTenantId(Integer tenantId) {
        List<ShopTenantAuditLogDO> shopTenantAuditLogDOList = shopTenantAuditLogDAO.selectByTenantId(tenantId);
        return ResponseResult.success(shopTenantAuditLogDOList);
    }

    @Override
    public Result listPageByUserId(Page page, ShopTenantDTO shopTenantDTO) {
        EntityWrapper<ShopTenantDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        entityWrapper.eq("user_id", shopTenantDTO.getUserId());
        if (shopTenantDTO.getPuslishState() != null) {
            entityWrapper.eq("puslish_state", shopTenantDTO.getPuslishState());
        }
        if (shopTenantDTO.getAuditState() != null) {
            entityWrapper.eq("audit_state", shopTenantDTO.getAuditState());
        }
        return baseListPage(page, shopTenantDTO, entityWrapper);
    }

    @Override
    public Result cancelPublist(Integer id, Integer userId) {
        EntityWrapper<ShopTenantDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        ShopTenantDO shopTenantDO = selectOne(entityWrapper);
        shopTenantDO.setPuslishState(CommonStateEnum.PublishState.OFFLINE.code);
        return updateById(shopTenantDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result publist(Integer id, Integer userId) {
        EntityWrapper<ShopTenantDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        ShopTenantDO shopTenantDO = selectOne(entityWrapper);
        if (CommonStateEnum.AuditState.PASSED.code != shopTenantDO.getAuditState()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "信息审核中,请耐心等待,审核后自动发布");
        }
        shopTenantDO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
        shopTenantDO.setPublishTime(new Date());
        return updateById(shopTenantDO) ? ResponseResult.success("发布成功") : ResponseResult.serverError();
    }

    private Result baseListPage(Page page, ShopTenantDTO shopTenantDTO, EntityWrapper<ShopTenantDO> entityWrapper) {
        if (shopTenantDTO.getRentAmountMin() != null && shopTenantDTO.getRentAmountMax() != null) {
            entityWrapper.ge("rent_amount_min", shopTenantDTO.getRentAmountMin())
                    .le("rent_amount_max", shopTenantDTO.getRentAmountMax());
        }
        if (shopTenantDTO.getTransferAmountMin() != null && shopTenantDTO.getTransferAmountMax() != null) {
            entityWrapper.ge("transfer_amount_min", shopTenantDTO.getTransferAmountMin())
                    .le("transfer_amount_max", shopTenantDTO.getTransferAmountMax());
        }
        if (shopTenantDTO.getAcreageMin() != null && shopTenantDTO.getAcreageMax() != null) {
            entityWrapper.ge("acreage_min", shopTenantDTO.getAcreageMin())
                    .le("acreage_max", shopTenantDTO.getAcreageMax());
        }
        if (shopTenantDTO.getProvinceCode() != null) {
            entityWrapper.eq("province_code", shopTenantDTO.getProvinceCode());
        }
        if (shopTenantDTO.getCityCode() != null) {
            entityWrapper.eq("city_code", shopTenantDTO.getCityCode());
        }
        if (shopTenantDTO.getAreaCode() != null) {
            entityWrapper.eq("area_code", shopTenantDTO.getAreaCode());
        }
        if (shopTenantDTO.getIsRecommend() != null) {
            entityWrapper.eq("is_recommend", shopTenantDTO.getIsRecommend());
            entityWrapper.orderBy("recommend_time", shopTenantDTO.getSortAsc() == 1);
        } else if (shopTenantDTO.getSortType() != null && shopTenantDTO.getSortType() == 0) {
            //面积排序
            entityWrapper.orderBy("acreage_min", shopTenantDTO.getSortAsc() == 0);
        } else if (shopTenantDTO.getSortType() != null && shopTenantDTO.getSortType() == 1) {
            //月租金排序
            entityWrapper.orderBy("rent_amount_min", shopTenantDTO.getSortAsc() == 0);
        } else {
            //发布时间排序
            entityWrapper.orderBy("publish_time", shopTenantDTO.getSortAsc() == 1);
        }
        page = selectPage(page, entityWrapper);
        return ResponseResult.success(page);
    }
}
