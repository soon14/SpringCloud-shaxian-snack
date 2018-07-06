package com.isxxc.client;


import com.isxxc.domain.dto.Pager;
import com.isxxc.service.ExpressCompanyService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * <p>
 * 快递管理 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-1-05
 */
@RestController
public class ExpressCompanyClientImpl implements ExpressCompanyClient {

    @Resource
    private ExpressCompanyService expressCompanyService;

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return expressCompanyService.listPage(pager);
    }
}
