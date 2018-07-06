package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.entity.UserFunctionDO;

import java.util.List;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
public interface UserFunctionService extends IService<UserFunctionDO> {

    /***
     * 获取排除鉴权的URL
     * @return
     */
    List<String> getIgnoreUrl();
}
