package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.AreaDO;

import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
public interface AreaService extends IService<AreaDO> {

    /***
     * 获取所有省
     * @return
     */
    Result provinceAll();

    /***
     * 根据父code查询
     * @param parentCode
     * @return
     */
    Result selectByParentCode(Integer parentCode);
}
