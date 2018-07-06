package com.isxxc.client;

import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.InformationTypeDO;
import com.isxxc.service.InformationTypeService;

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
public class InformationTypeClientImpl implements InformationTypeClient {

    @Resource
    private InformationTypeService informationTypeService;

    @Override
    public Result save(@RequestBody InformationTypeDO informationTypeDO) {
        return informationTypeService.save(informationTypeDO);
    }

    @Override
    public Result delById(Integer id) {
        return informationTypeService.delById(id);
    }

    @Override
    public Result updateInfo(@RequestBody InformationTypeDO informationTypeDO) {
        return informationTypeService.updateInfo(informationTypeDO);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return informationTypeService.listPage(pager);
    }

    @Override
    public Result list(Integer category) {
        return informationTypeService.list(category);
    }
}
