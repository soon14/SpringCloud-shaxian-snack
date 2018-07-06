package com.isxxc.service;

import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountDTO;
import com.isxxc.domain.entity.UserAccountDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-18
 */
public interface UserAccountCountService extends IService<UserAccountDO> {

    /***
     * 手机注册用户统计
     * @param authAccountDTO
     * @return
     */
    Result countUser(UserAccountDTO authAccountDTO);


    /***
     * 手机注册用户  -- 时间段查询
     * @param pager
     * @return
     */
    Pager listPage(Pager pager);
}
