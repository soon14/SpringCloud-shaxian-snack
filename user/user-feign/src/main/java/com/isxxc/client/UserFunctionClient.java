package com.isxxc.client;

import com.isxxc.domain.entity.UserFunctionDO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.likq.result.Result;

/**
 * <p>
 * 会员菜单管理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-21
 */
@RequestMapping("/userFunctionClient")
public interface UserFunctionClient {

    /***
     * 添加功能菜单
     * @param userFunctionDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    Result save(UserFunctionDO userFunctionDO);

    /***
     * 查询功能菜单，构建树
     * @return
     */
    @RequestMapping(value = "functionTree", method = RequestMethod.GET)
    Result functionTree();

    /***
     * 更新菜单信息
     * @param userFunctionDO
     * @return
     */
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    Result updateInfo(UserFunctionDO userFunctionDO);
}
