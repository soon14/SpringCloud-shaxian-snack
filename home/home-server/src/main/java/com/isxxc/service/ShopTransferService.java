package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.ShopTransferDTO;
import com.isxxc.domain.entity.ShopTransferDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
public interface ShopTransferService extends IService<ShopTransferDO> {

    /***
     * 添加转租/求租信息
     * @param shopTransferDTO
     * @return
     */
    Result save(ShopTransferDTO shopTransferDTO);

    /***
     * 查询列表,带分页
     * @param page
     * @param shopTransferDTO
     * @return
     */
    Result listPage(Page page, ShopTransferDTO shopTransferDTO);

    /***
     * 信息更新
     * @param shopTransferDTO
     * @return
     */
    Result updateInfo(ShopTransferDTO shopTransferDTO);

    /***
     * 根据ID查询信息
     * @param id
     * @return
     */
    Result getInfoById(Integer id);

    /***
     * 获取审核日志
     * @param transferId
     * @return
     */
    Result getAuditLogByTransferId(Integer transferId);

    /***
     * 根据用户ID查询列表
     * @param page
     * @param shopTransferDTO
     * @return
     */
    Result listPageByUserId(Page page, ShopTransferDTO shopTransferDTO);

    /***
     * 取消发布
     * @param id
     * @return
     */
    Result cancelPublist(Integer id, Integer userId);

    /***
     * 发布上线
     * @param id
     * @return
     */
    Result publist(Integer id, Integer userId);

    /***
     * 猜你喜欢
     * @param size
     * @return
     */
    Result mayLike(Integer size);
}
