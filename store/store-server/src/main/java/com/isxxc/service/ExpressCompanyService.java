package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.ExpressCompanyDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-06
 */
public interface ExpressCompanyService extends IService<ExpressCompanyDO> {

    /***
     * 查询快递
     * @param pager
     * @return
     */
    Result listPage(Pager pager);
}
