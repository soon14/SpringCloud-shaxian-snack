package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.ShopEnrollDO;

import javax.servlet.http.HttpServletResponse;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
public interface ShopEnrollService extends IService<ShopEnrollDO> {

    /***
     * 查询所有记录
     * @return
     */
    Result findAll();

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    Result findById(Integer id);

    /***
     * 导出Excel
     * @param response
     * @return
     */
    Result exportExcel(HttpServletResponse response);
}
