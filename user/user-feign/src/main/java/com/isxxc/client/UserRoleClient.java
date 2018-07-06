package com.isxxc.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.isxxc.domain.entity.UserRoleDO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-20
 */
@RequestMapping("/userRoleClient")
public interface UserRoleClient {

    /***
     *添加角色
     * @param userRoleDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(UserRoleDO userRoleDO);

    /***
     * 更新角色信息
     * @param userRoleDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result updateInfo(UserRoleDO userRoleDO);

    /***
     * 查询列表
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Page<UserRoleDO> page);

    /***
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "deleted", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result deleted(@RequestParam("id") Integer id);
}
