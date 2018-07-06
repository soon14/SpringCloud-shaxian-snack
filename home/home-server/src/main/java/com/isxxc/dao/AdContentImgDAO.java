package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.AdContentImgDTO;
import com.isxxc.domain.entity.AdContentImgDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 广告内容图片 Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@Repository
public interface AdContentImgDAO extends BaseMapper<AdContentImgDO> {

    /***
     * 根据内容ID查询图片
     * @param id
     * @return
     */
    List<AdContentImgDTO> selectByAdContentId(Integer id);
}