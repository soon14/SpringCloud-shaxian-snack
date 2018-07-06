package com.isxxc.client;

import com.isxxc.domain.entity.UserRoleRelationDO;

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
@RequestMapping("/userRoleRelationClient")
public interface UserRoleRelationClient {

    /***
     * 会员与角色关联
     * @param userId
     * @param roleCodes
     * @return
     */
    @RequestMapping(value = "userRoleRelation", method = RequestMethod.POST)
    Result userRoleRelation(@RequestParam("userId") Integer userId, @RequestParam("roleCodes") String roleCodes);


    /***
     * 会员与角色关联
     * @param userRoleRelationDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result save(UserRoleRelationDO userRoleRelationDO);
}
