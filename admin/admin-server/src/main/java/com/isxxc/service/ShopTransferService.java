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
 * @author 泥水佬
 * @since 2017-12-08
 */
public interface ShopTransferService extends IService<ShopTransferDO> {

    /***
     *发布转让信息
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
     * 更新推荐状态
     * @param id
     * @return
     */
    Result updateRecommend(Integer id);

    /***
     * 删除信息
     * @param id
     * @return
     */
    Result delById(Integer id);
}
