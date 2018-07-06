package com.isxxc.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.ShopTenantDTO;
import com.isxxc.domain.entity.ShopTenantDO;
import com.isxxc.service.ShopTenantService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p>
 * 旺铺承包 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/shopTenant")
public class ShopTenantController {

    @Resource
    private ShopTenantService shopTenantService;

    /***
     * 添加承租信息
     * @param shopTenantDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(ShopTenantDO shopTenantDO) {
        if (StringUtils.isBlank(shopTenantDO.getTitle())) {
            return ResponseResult.paramNotNull("标题不能为空");
        }
        if (StringUtils.isBlank(shopTenantDO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(shopTenantDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (shopTenantDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (shopTenantDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (shopTenantDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(shopTenantDO.getDescription())) {
            return ResponseResult.paramNotNull("描述不能为空");
        }
        if (shopTenantDO.getAcreageMin() == null || shopTenantDO.getAcreageMax() == null) {
            return ResponseResult.paramNotNull("面积范围不能为空");
        }
        if (shopTenantDO.getRentAmountMin() == null || shopTenantDO.getRentAmountMax() == null) {
            return ResponseResult.paramNotNull("租金范围不能为空");
        }
        return shopTenantService.save(shopTenantDO);
    }

    /***
     * 更新承租信息
     * @param shopTenantDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public Result updateInfo(ShopTenantDO shopTenantDO) {
        if (shopTenantDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(shopTenantDO.getTitle())) {
            return ResponseResult.paramNotNull("标题不能为空");
        }
        if (StringUtils.isBlank(shopTenantDO.getContacts())) {
            return ResponseResult.paramNotNull("联系人不能为空");
        }
        if (StringUtils.isBlank(shopTenantDO.getMobileNo())) {
            return ResponseResult.paramNotNull("联系方式不能为空");
        }
        if (shopTenantDO.getProvinceCode() == null) {
            return ResponseResult.paramNotNull("请选择省");
        }
        if (shopTenantDO.getAreaCode() == null) {
            return ResponseResult.paramNotNull("请选择市");
        }
        if (shopTenantDO.getCityCode() == null) {
            return ResponseResult.paramNotNull("请选择区");
        }
        if (StringUtils.isBlank(shopTenantDO.getDescription())) {
            return ResponseResult.paramNotNull("描述不能为空");
        }
        if (shopTenantDO.getAcreageMin() == null || shopTenantDO.getAcreageMax() == null) {
            return ResponseResult.paramNotNull("面积范围不能为空");
        }
        if (shopTenantDO.getRentAmountMin() == null || shopTenantDO.getRentAmountMax() == null) {
            return ResponseResult.paramNotNull("租金范围不能为空");
        }
        return shopTenantService.updateInfo(shopTenantDO);
    }

    /***
     * 查询列表，带分页
     * @param page
     * @param shopTenantDTO
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page, ShopTenantDTO shopTenantDTO) {
        return shopTenantService.listPage(page, shopTenantDTO);
    }

    /***
     * 根据会员查询列表
     * @param page
     * @param shopTenantDTO
     * @return
     */
    @RequestMapping(value = "listPageByUserId", method = RequestMethod.GET)
    public Result listPageByUserId(Page page, ShopTenantDTO shopTenantDTO) {
        return shopTenantService.listPageByUserId(page, shopTenantDTO);
    }

    /***
     *查询日志
     * @param tenantId
     * @return
     */
    @RequestMapping(value = "getAuditLogByTenantId", method = RequestMethod.GET)
    public Result getAuditLogByTenantId(Integer tenantId) {
        if (tenantId == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return shopTenantService.getAuditLogByTenantId(tenantId);
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
        return shopTenantService.cancelPublist(id, userId);
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
        return shopTenantService.publist(id, userId);
    }
}
