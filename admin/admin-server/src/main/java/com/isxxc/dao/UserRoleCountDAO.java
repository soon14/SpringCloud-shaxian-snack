package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.entity.UserRoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户角色统计 Mapper 接口
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-21
 */
@Repository
public interface UserRoleCountDAO extends BaseMapper<UserRoleDO> {

    /***
     * 用户角色 -- 门店商户  统计
     * @param userRoleDO
     * @return
     */
    Integer countUserShop(UserRoleDO userRoleDO);
    /***
     * 用户角色 -- 原料供应商  统计
     * @param userRoleDO
     * @return
     */
    Integer countUserStore(UserRoleDO userRoleDO);
    /***
     * 用户角色 -- 配套服务/文化服务  统计
     * @param userRoleDO
     * @return
     */
    Integer countUserSupporting(UserRoleDO userRoleDO);
    /***
     * 用户角色 -- 金融服务  统计
     * @param userRoleDO
     * @return
     */
    Integer countUserBanking(UserRoleDO userRoleDO);
    /***
     * 查询用户角色列表 -- 时间段,自带分页
     * @param pager
     * @return
     */
    List<UserAccountOtherDTO> listPage(Pager pager, @Param("userAccountR") Map<String, Object> paramMap);

}
