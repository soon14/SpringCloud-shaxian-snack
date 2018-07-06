package com.isxxc.web;


import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.TimeUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.*;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.service.ShopTransferCountService;
import com.isxxc.service.UserAccountCountService;
import com.isxxc.service.UserRoleCountService;
import com.isxxc.service.UserShopCountService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>手机注册用户统计 前端控制器</p>
 *
 * @author 吴坚
 * @since 2018-05-18
 */
@RestController
@RequestMapping("/userAccountCount")
public class UserAccountCountController {

    @Resource
    private UserAccountCountService userAccountCountService;
    @Resource
    private UserRoleCountService userRoleCountService;
    @Resource
    private UserShopCountService userShopCountService;
    @Resource
    private ShopTransferCountService shopTransferCountService;

    /***
     * 手机注册用户统计
     * @return
     */
    @RequestMapping(value = "countUser", method = RequestMethod.POST)
    public Result countUser(@RequestBody UserAccountDTO authAccountDTO) {
        return userAccountCountService.countUser(authAccountDTO);
    }

    /***
     * 时间段查询
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Date startTime, Date endTime) {
        if (startTime != null){
            pager.putParam("startTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(startTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        if (endTime != null) {
            pager.putParam("endTime", TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(endTime), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
        }
        List<UserAccountOtherDTO> AccountList = userAccountCountService.listPage(pager).getRecords();
        List<UserAccountOtherDTO> RoleList = userRoleCountService.listPage(pager).getRecords();
        List<UserAccountOtherDTO> ShopList = userShopCountService.listPage(pager).getRecords();
        List<UserAccountOtherDTO> shopTransferList = shopTransferCountService.listPage(pager).getRecords();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("AccountList",AccountList);
        map.put("RoleList",RoleList);
        map.put("ShopList",ShopList);
        map.put("shopTransferList",shopTransferList);
        return ResponseResult.success(map);
    }


    @RequestMapping(value = "countAllUser", method = RequestMethod.POST)
    public Result countAllUser(@RequestBody UserAccountDTO authAccountDTO,UserRoleDTO userRoleDTO,UserShopProfilDO userShopProfilDO,ShopTransferCountDTO shopTransferCountDTO) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("countUser",userAccountCountService.countUser(authAccountDTO).getData());
        map.put("countUserBanking",userRoleCountService.countUserBanking(userRoleDTO).getData());
        map.put("countShop",userRoleCountService.countUserShop(userRoleDTO).getData());
        map.put("countUserStore",userRoleCountService.countUserStore(userRoleDTO).getData());
        map.put("countUserSupporting",userRoleCountService.countUserSupporting(userRoleDTO).getData());
        map.put("countUserShop",userShopCountService.countUserShop(userShopProfilDO).getData());
        map.put("countAttornShop",shopTransferCountService.countAttornShop(shopTransferCountDTO).getData());
        map.put("countRentingShop",shopTransferCountService.countRentingShop(shopTransferCountDTO).getData());
        return ResponseResult.success(map);
    }
}


