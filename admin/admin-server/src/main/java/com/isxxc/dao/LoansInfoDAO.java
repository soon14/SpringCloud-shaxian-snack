package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.dto.LoansInfoDTO;
import com.isxxc.domain.entity.LoansInfoDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@Repository
public interface LoansInfoDAO extends BaseMapper<LoansInfoDO> {

    /***
     * 查询列表
     * @param page
     * @param loansInfoDTO
     * @return
     */
    List<LoansInfoDTO> listPage(Page page, @Param("loansInfoDTO") LoansInfoDTO loansInfoDTO);

    /***
     * 根据ID查询详情
     * @param id
     * @return
     */
    LoansInfoDTO getInfoById(Integer id);
}