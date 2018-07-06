package com.isxxc.web;


import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.TimeUtils;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ShopTransferCountDTO;
import com.isxxc.service.ShopTransferCountService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>盘店寻租-旺铺转让/求租旺铺统计 前端控制器</p>
 *
 * @author 吴坚
 * @since 2018-05-22
 */
@RestController
@RequestMapping("/shopTransferCount")
public class ShopTransferCountController {

    @Resource
    private ShopTransferCountService shopTransferCountService;

    /***
     * 盘店寻租-旺铺转让  统计
     * @return
     */
    @RequestMapping(value = "countAttornShop", method = RequestMethod.POST)
    public Result countAttornShop(@RequestBody ShopTransferCountDTO shopTransferCountDTO) {
        return shopTransferCountService.countAttornShop(shopTransferCountDTO);
    }
    /***
     * 盘店寻租-求租旺铺  统计
     * @return
     */
    @RequestMapping(value = "countRentingShop", method = RequestMethod.POST)
    public Result countRentingShop(@RequestBody ShopTransferCountDTO shopTransferCountDTO) {
        return shopTransferCountService.countRentingShop(shopTransferCountDTO);
    }

}
