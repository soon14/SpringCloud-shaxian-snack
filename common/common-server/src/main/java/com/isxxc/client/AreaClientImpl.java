package com.isxxc.client;

import com.isxxc.service.AreaService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * 省市区管理
 *
 * @author 泥水佬
 * @date 2018/1/8
 */
@RestController
public class AreaClientImpl implements AreaClient {

    @Resource
    private AreaService areaService;

    @Override
    public Result provinceAll() {
        return areaService.provinceAll();
    }

    @Override
    public Result selectByParentCode(Integer parentCode) {
        return areaService.selectByParentCode(parentCode);
    }
}
