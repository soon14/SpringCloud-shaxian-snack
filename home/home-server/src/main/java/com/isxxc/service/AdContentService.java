package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.AdContentDTO;
import com.isxxc.domain.entity.AdContentDO;

import cc.likq.result.Result;

/**
 * <p>
 * 广告内容 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
public interface AdContentService extends IService<AdContentDO> {

    /***
     * 添加广告内容
     * @param adContentDTO
     * @return
     */
    Result save(AdContentDTO adContentDTO);

    /***
     * 查询内容列表
     * @param page
     * @return
     */
    Result listPage(Page page);

    /***
     * 更新内容
     * @param adContentDTO
     * @return
     */
    Result updateInfo(AdContentDTO adContentDTO);

    /***
     * 删除内容
     * @param id
     * @return
     */
    Result delById(Integer id);

    /***
     * 根据Code查询结果列表
     * @param code
     * @return
     */
    Result listByCode(String code);

    /***
     * 发布广告
     * @param id
     * @return
     */
    Result publish(Integer id);

    /***
     * 取消广告发布
     * @return
     */
    Result cancelPublish(Integer id);
}
