package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.ShopTransferDTO;
import com.isxxc.service.ShopTransferService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 店铺转让/求租ID 前端控制器
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
@RestController
@RequestMapping("/shopTransfer")
public class ShopTransferController {

    @Resource
    private ShopTransferService shopTransferService;

    /***
     * 转租/求租信息发布
     * @param shopTransferDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ShopTransferDTO shopTransferDTO, Integer userId) {
        shopTransferDTO.setUserId(userId);
        if (StringUtils.isBlank(shopTransferDTO.getTitle())) {
            return ResponseResult.paramNotNull("标题不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getSubtitle())) {
            return ResponseResult.paramNotNull("简介不能为空");
        }
        if (shopTransferDTO.getRentAmount() == null || shopTransferDTO.getRentAmount() < 0) {
            return ResponseResult.paramNotNull("月租金不能为空或小于0");
        }
        if (shopTransferDTO.getAcreage() == null || shopTransferDTO.getAcreage() <= 0) {
            return ResponseResult.paramNotNull("面积不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (shopTransferDTO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (shopTransferDTO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (shopTransferDTO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(shopTransferDTO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getDescription())) {
            return ResponseResult.paramNotNull("描述不能为空");
        }
        if (shopTransferDTO.getLongitude() == null || shopTransferDTO.getLatitude() == null) {
            return ResponseResult.paramNotNull("请在地图上进行定位");
        }
        return shopTransferService.save(shopTransferDTO);
    }

    /***
     * 查询列表，带分页
     * @param page
     * @param shopTransferDTO
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page, ShopTransferDTO shopTransferDTO) {
        return shopTransferService.listPage(page, shopTransferDTO);
    }

    /***
     * 查询会员信息列表
     * @param page
     * @param shopTransferDTO
     * @return
     */
    @RequestMapping(value = "listPageByUserId", method = RequestMethod.GET)
    public Result listPageByUserId(Page page, ShopTransferDTO shopTransferDTO) {
        return shopTransferService.listPageByUserId(page, shopTransferDTO);
    }

    /***
     * 信息更新
     * @param shopTransferDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(@RequestBody ShopTransferDTO shopTransferDTO, Integer userId) {
        shopTransferDTO.setUserId(userId);
        if (shopTransferDTO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getTitle())) {
            return ResponseResult.paramNotNull("标题不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getSubtitle())) {
            return ResponseResult.paramNotNull("简介不能为空");
        }
        if (shopTransferDTO.getRentAmount() == null || shopTransferDTO.getRentAmount() < 0) {
            return ResponseResult.paramNotNull("月租金不能为空或小于等0");
        }
        if (shopTransferDTO.getAcreage() == null || shopTransferDTO.getAcreage() <= 0) {
            return ResponseResult.paramNotNull("面积不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getAddress())) {
            return ResponseResult.paramNotNull("地址不能为空");
        }
        if (StringUtils.isBlank(shopTransferDTO.getDescription())) {
            return ResponseResult.paramNotNull("描述不能为空");
        }
        if (shopTransferDTO.getLongitude() == null || shopTransferDTO.getLatitude() == null) {
            return ResponseResult.paramNotNull("请在地图上进行定位");
        }
        return shopTransferService.updateInfo(shopTransferDTO);
    }

    /***
     * 根据ID查询详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTransferService.getInfoById(id);
    }

    /***
     *查询日志
     * @param transferId
     * @return
     */
    @RequestMapping(value = "getAuditLogByTransferId", method = RequestMethod.GET)
    public Result getAuditLogByTransferId(Integer transferId) {
        if (transferId == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTransferService.getAuditLogByTransferId(transferId);
    }

    /***
     * 取消发布
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelPublist", method = RequestMethod.POST)
    public Result cancelPublist(Integer id, Integer userId) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTransferService.cancelPublist(id, userId);
    }

    /***
     * 发布上线
     * @param id
     * @return
     */
    @RequestMapping(value = "publist", method = RequestMethod.POST)
    public Result publist(Integer id, Integer userId) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTransferService.publist(id, userId);
    }

    /***
     * 猜你喜欢
     * @param size
     * @return
     */
    @RequestMapping(value = "mayLike", method = RequestMethod.GET)
    public Result mayLike(Integer size) {
        if (size == null || size <= 0) {
            return ResponseResult.paramNotNull("获取数据不能为空或小于等于0");
        }
        return shopTransferService.mayLike(size);
    }
}
