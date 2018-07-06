package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.AdSpaceDO;

import cc.likq.result.Result;

/**
 * <p>
 * 广告位置标识 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
public interface AdSpaceService extends IService<AdSpaceDO> {

    /***
     * 添加广告位
     * @param adSpaceDO
     * @return
     */
    Result save(AdSpaceDO adSpaceDO);

    /***
     * 查询广告位
     * @param page
     * @return
     */
    Result listPage(Page page);

    /***
     * 删除广告位
     * @param id
     * @return
     */
    Result delById(Integer id);

    /***
     * 更新内容
     * @param adSpaceDO
     * @return
     */
    Result updateInfo(AdSpaceDO adSpaceDO);

    /***
     * 查询列表
     * @return
     */
    Result list();
}
