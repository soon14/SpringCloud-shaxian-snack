package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.AdSpaceDO;
import com.isxxc.service.AdSpaceService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * 广告位管理
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
@RestController
public class AdSpaceClientImpl implements AdSpaceClient {
    @Resource
    private AdSpaceService adSpaceService;

    @Override
    public Result save(@RequestBody AdSpaceDO adSpaceDO) {
        return adSpaceService.save(adSpaceDO);
    }

    @Override
    public Result listPage(@RequestBody Page page) {
        return adSpaceService.listPage(page);
    }

    @Override
    public Result list() {
        return adSpaceService.list();
    }

    @Override
    public Result delById(Integer id) {
        return adSpaceService.delById(id);
    }

    @Override
    public Result updateInfo(@RequestBody AdSpaceDO adSpaceDO) {
        return adSpaceService.updateInfo(adSpaceDO);
    }
}
