package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.entity.UserAccountDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 手机注册用户统计 Mapper 接口
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-18
 */
@Repository
public interface UserAccountCountDAO extends BaseMapper<UserAccountDO> {

    /***
     * 手机注册用户统计
     * @param authAccountDO
     * @return
     */
    Integer countUser(UserAccountDO authAccountDO);

    /***
     * 查询手机注册用户列表 -- 时间段,自带分页
     * @param pager
     * @return
     */
    List<UserAccountOtherDTO> listPage(Pager pager, @Param("userAccountR") Map<String, Object> paramMap);

}

