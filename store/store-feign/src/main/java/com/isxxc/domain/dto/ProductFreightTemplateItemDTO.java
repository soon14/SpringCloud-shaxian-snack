package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ProductFreightTemplateItemDO;

/**
 * <p>
 * 运费模版项
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-12
 */
public class ProductFreightTemplateItemDTO extends ProductFreightTemplateItemDO {

    /***
     * 省名称
     */
    private String provinceName;

    /***
     * 市名称
     */
    private String cityName;

    /***
     * 区名称
     */
    private String areaName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
