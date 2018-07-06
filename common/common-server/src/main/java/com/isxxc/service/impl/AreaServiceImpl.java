package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.AreaDAO;
import com.isxxc.domain.entity.AreaDO;
import com.isxxc.service.AreaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl extends ServiceImpl<AreaDAO, AreaDO> implements AreaService {

    @Resource
    private AreaDAO areaDAO;

    @Override
    public Result provinceAll() {
        List<AreaDO> areaDOList = areaDAO.selectByParentCode(0);
        return ResponseResult.success(areaDOList);
    }

    @Override
    public Result selectByParentCode(Integer parentCode) {
        List<AreaDO> areaDOList = areaDAO.selectByParentCode(parentCode);
        return ResponseResult.success(areaDOList);
    }


}
