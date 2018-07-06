package com.isxxc.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.ShopTransferDTO;
import com.isxxc.domain.entity.ShopTransferAuditLogDO;
import com.isxxc.service.ShopTransferAuditLogService;
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
 * 旺铺转让管理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/shopTransfer")
public class ShopTransferController {

    @Resource
    private ShopTransferService shopTransferService;

    @Resource
    private ShopTransferAuditLogService shopTransferAuditLogService;

    /***
     * 转租/求租信息发布
     * @param shopTransferDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ShopTransferDTO shopTransferDTO) {
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
     * 信息更新
     * @param shopTransferDTO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(@RequestBody ShopTransferDTO shopTransferDTO) {
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
        return shopTransferAuditLogService.getAuditLogByTransferId(transferId);
    }

    /***
     *审核
     * @param shopTransferAuditLogDO
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(ShopTransferAuditLogDO shopTransferAuditLogDO) {
        if (shopTransferAuditLogDO.getShopTransferId() == null) {
            return ResponseResult.paramNotNull("店铺转让/求租ID不能为空");
        }
        if (shopTransferAuditLogDO.getAuditState() == null) {
            return ResponseResult.paramNotNull("审核状态不能为空");
        }
        return shopTransferAuditLogService.audit(shopTransferAuditLogDO);
    }

    /***
     *更新推荐状态
     * @param id
     * @return
     */
    @RequestMapping(value = "updateRecommend", method = RequestMethod.POST)
    public Result updateRecommend(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTransferService.updateRecommend(id);
    }

    /***
     * 删除信息
     * @return
     */
    @RequestMapping(value = "delById", method = RequestMethod.POST)
    public Result delById(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTransferService.delById(id);
    }
}
