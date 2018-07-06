package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.AdContentDTO;
import com.isxxc.service.AdContentService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * 广告内容管理
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
@RestController
public class AdContentClientImpl implements AdContentClient {

    @Resource
    private AdContentService adContentService;

    @Override
    public Result save(@RequestBody AdContentDTO adContentDTO) {
        return adContentService.save(adContentDTO);
    }

    @Override
    public Result listPage(@RequestBody Page page) {
        return adContentService.listPage(page);
    }

    @Override
    public Result updateInfo(@RequestBody AdContentDTO adContentDTO) {
        return adContentService.updateInfo(adContentDTO);
    }

    @Override
    public Result delById(Integer id) {
        return adContentService.delById(id);
    }

    @Override
    public Result publish(Integer id) {
        return adContentService.publish(id);
    }

    @Override
    public Result cancelPublish(Integer id) {
        return adContentService.cancelPublish(id);
    }
}
