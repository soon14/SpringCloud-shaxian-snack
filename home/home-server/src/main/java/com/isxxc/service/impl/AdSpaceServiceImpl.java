package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.AdSpaceDAO;
import com.isxxc.domain.entity.AdSpaceDO;
import com.isxxc.service.AdSpaceService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 广告位置标识 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdSpaceServiceImpl extends ServiceImpl<AdSpaceDAO, AdSpaceDO> implements AdSpaceService {

    @Override
    public Result save(AdSpaceDO adSpaceDO) {
        adSpaceDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return insert(adSpaceDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Page page) {
        EntityWrapper<AdSpaceDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        page = selectPage(page, entityWrapper);
        return ResponseResult.success(page);
    }

    @Override
    public Result delById(Integer id) {
        AdSpaceDO adSpaceDO = selectById(id);
        adSpaceDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(adSpaceDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(AdSpaceDO adSpaceDO) {
        return updateById(adSpaceDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result list() {
        EntityWrapper<AdSpaceDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<AdSpaceDO> adSpaceDOList = selectList(entityWrapper);
        return ResponseResult.success(adSpaceDOList);
    }
}
