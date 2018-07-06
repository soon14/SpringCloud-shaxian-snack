package com.isxxc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.InformationInfoDTO;
import com.isxxc.domain.entity.InformationInfoDO;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likq
 * @since 2017-12-04
 */
public interface InformationInfoService extends IService<InformationInfoDO> {

    /***
     * 保存
     * @param informationInfoDTO
     * @return
     */
    Result save(InformationInfoDTO informationInfoDTO);

    /***
     *根据ID查询所有信息
     * @param id
     * @return
     */
    Result getInfoById(Integer id);

    /***
     * 更新
     * @param informationInfoDTO
     * @return
     */
    Result updateInfo(InformationInfoDTO informationInfoDTO);

    /***
     * 查询列表,带分页
     * @param page
     * @param informationInfoDO
     * @return
     */
    Result listPage(Page page, InformationInfoDO informationInfoDO);

    /***
     * 发布
     * @param id
     * @return
     */
    Result publish(Integer id);

    /***
     * 取消发布
     * @param id
     * @return
     */
    Result cancelPublish(Integer id);
}
