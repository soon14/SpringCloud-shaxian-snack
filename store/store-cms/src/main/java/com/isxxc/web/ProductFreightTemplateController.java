package com.isxxc.web;


import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductFreightTemplateDTO;
import com.isxxc.domain.dto.ProductFreightTemplateItemDTO;
import com.isxxc.service.feign.store.ProductFreightTemplateServer;

import org.apache.commons.collections4.CollectionUtils;
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
 * 运费模版 前端控制器
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
@RestController
@RequestMapping("productFreightTemplate")
public class ProductFreightTemplateController {

    @Resource
    private ProductFreightTemplateServer productFreightTemplateServer;

    /***
     * 添加模版
     * @param productFreightTemplateDTO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ProductFreightTemplateDTO productFreightTemplateDTO, Integer storeId) {
        productFreightTemplateDTO.setStoreId(storeId);
        if (StringUtils.isBlank(productFreightTemplateDTO.getName())) {
            return ResponseResult.paramNotNull("模版名称不能为空");
        }
        if (productFreightTemplateDTO.getPrice() == null || productFreightTemplateDTO.getPrice() < 0) {
            return ResponseResult.paramNotNull("默认运费不能为空或小于0");
        }
        if (productFreightTemplateDTO.getType() == null) {
            return ResponseResult.paramNotNull("请选择模版类型");
        } else if (productFreightTemplateDTO.getType() == 1 && CollectionUtils.isNotEmpty(productFreightTemplateDTO.getFreightTemplateItemList())) {
            for (ProductFreightTemplateItemDTO freightTemplateItemDTO : productFreightTemplateDTO.getFreightTemplateItemList()) {
                if (freightTemplateItemDTO.getProvinceCode() == null) {
                    return ResponseResult.paramNotNull("请选择省");
                }
                if (freightTemplateItemDTO.getPrice() == null || freightTemplateItemDTO.getPrice() < 0) {
                    return ResponseResult.paramNotNull("运费不能为空或小于0");
                }
            }
        }
        return productFreightTemplateServer.save(productFreightTemplateDTO);
    }

    /***
     * 查询列表,带分页
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Pager pager, Integer storeId) {
        pager.putParam("storeId", storeId);
        return productFreightTemplateServer.listPage(pager);
    }

    /***
     * 根据ID及店铺ID查询详情
     * @return
     */
    @RequestMapping(value = "getInfoByIdAndStoreId", method = RequestMethod.GET)
    public Result getInfoByIdAndStoreId(Integer id, Integer storeId) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return productFreightTemplateServer.getInfoByIdAndStoreId(id, storeId);
    }

    /***
     * 删除模版
     * @param id
     * @param storeId
     * @return
     */
    @RequestMapping(value = "delByIdAndStoreId", method = RequestMethod.POST)
    public Result delByIdAndStoreId(Integer id, Integer storeId) {
        return productFreightTemplateServer.delByIdAndStoreId(id, storeId);
    }
}
