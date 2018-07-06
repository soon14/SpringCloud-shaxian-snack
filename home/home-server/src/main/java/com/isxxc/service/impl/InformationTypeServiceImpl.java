package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.InformationTypeDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.InformationTypeDO;
import com.isxxc.service.InformationTypeService;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InformationTypeServiceImpl extends ServiceImpl<InformationTypeDAO, InformationTypeDO> implements InformationTypeService {

    @Override
    public Result save(InformationTypeDO informationTypeDO) {
        informationTypeDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return insert(informationTypeDO) ? ResponseResult.successMsg("添加成功") : ResponseResult.serverError();
    }

    @Override
    public Result delById(Integer id) {
        InformationTypeDO informationTypeDO = selectById(id);
        informationTypeDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(informationTypeDO) ? ResponseResult.successMsg("删除成功") : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(InformationTypeDO informationTypeDO) {
        return updateById(informationTypeDO) ? ResponseResult.successMsg("更新成功") : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Pager pager) {
        EntityWrapper<InformationTypeDO> entityWrapper = new EntityWrapper<>();
        if (MapUtils.isNotEmpty(pager.getParamMap()) && pager.getParamMap().containsKey("category") && pager.getParamMap().get("category") != null) {
            entityWrapper.eq("category", pager.getParamMap().get("category"));
        }
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        pager = (Pager) selectPage(pager, entityWrapper);
        return ResponseResult.success(pager);
    }

    @Override
    public Result list(Integer category) {
        EntityWrapper<InformationTypeDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        if (category != null) {
            entityWrapper.eq("category", category);
        }
        List<InformationTypeDO> informationTypeDOList = selectList(entityWrapper);
        return ResponseResult.success(informationTypeDOList);
    }
}
