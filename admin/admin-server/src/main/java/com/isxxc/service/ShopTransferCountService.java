package com.isxxc.service;

import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ShopTransferCountDTO;
import com.isxxc.domain.dto.ShopTransferDTO;
import com.isxxc.domain.entity.ShopTransferDO;



/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-22
 */
public interface ShopTransferCountService extends IService<ShopTransferDO> {
    /***
     * 盘店寻租-旺铺转让  统计
     * @param shopTransferCountDTO
     * @return
     */
    Result countAttornShop(ShopTransferCountDTO shopTransferCountDTO);
    /***
     * 盘店寻租-求租旺铺  统计
     * @param shopTransferCountDTO
     * @return
     */
    Result countRentingShop(ShopTransferCountDTO shopTransferCountDTO);
    /***
     * 盘店寻租  -- 时间段查询
     * @param pager
     * @return
     */
    Pager listPage(Pager pager);
}
