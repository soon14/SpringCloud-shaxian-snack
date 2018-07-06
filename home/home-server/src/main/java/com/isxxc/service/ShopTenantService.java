package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ShopTenantDTO;
import com.isxxc.domain.entity.ShopTenantDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
public interface ShopTenantService extends IService<ShopTenantDO> {

    /***
     *添加承租信息
     * @param shopTenantDO
     * @return
     */
    Result save(ShopTenantDO shopTenantDO);

    /***
     * 更新承租信息
     * @param shopTenantDO
     * @return
     */
    Result updateInfo(ShopTenantDO shopTenantDO);

    /***
     *查询,带列表
     * @param page
     * @param shopTenantDTO
     * @return
     */
    Result listPage(Page page, ShopTenantDTO shopTenantDTO);

    /***
     * 日志查询
     * @param tenantId
     * @return
     */
    Result getAuditLogByTenantId(Integer tenantId);

    /***
     * 根据会员查询信息列表
     * @param page
     * @param shopTenantDTO
     * @return
     */
    Result listPageByUserId(Page page, ShopTenantDTO shopTenantDTO);

    /***
     * 取消发布
     * @param id
     * @return
     */
    Result cancelPublist(Integer id, Integer userId);

    /***
     * 发布
     * @param id
     * @return
     */
    Result publist(Integer id, Integer userId);
}
