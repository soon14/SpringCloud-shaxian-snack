package com.isxxc.web;


import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.TimeUtils;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.domain.entity.UserShopProfilImgDO;
import com.isxxc.service.UserShopCountService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 门店数统计 前端控制器
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-22
 */
@RestController
@RequestMapping("/usershopcount")
public class UserShopCountController {

    @Resource
    private UserShopCountService userShopCountService;

    /***
     * 门店数统计
     * @param userShopProfilDO
     * @return
     */
    @RequestMapping(value = "countUserShop", method = RequestMethod.POST)
    public Result countUserShop(@RequestBody UserShopProfilDO userShopProfilDO) {
        return userShopCountService.countUserShop(userShopProfilDO);
    }

}
