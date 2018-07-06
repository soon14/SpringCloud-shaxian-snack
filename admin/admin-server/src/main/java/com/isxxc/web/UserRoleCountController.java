package com.isxxc.web;


        import cc.likq.result.ResponseResult;
        import cc.likq.result.Result;
        import cc.likq.util.TimeUtils;
        import com.isxxc.domain.dto.Pager;
        import com.isxxc.domain.dto.UserRoleDTO;
        import com.isxxc.domain.entity.UserRoleDO;
        import com.isxxc.service.UserRoleCountService;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;

        import javax.annotation.Resource;
        import java.util.Date;

/**
 * <p>
 * 四大用户角色统计管理 前端控制器
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-21
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleCountController {

    @Resource
    private UserRoleCountService userRoleCountService;

    /***
     * 用户角色 -- 门店商户  统计
     * @return
     */
    @RequestMapping(value = "countUserShop", method = RequestMethod.POST)
    public Result countUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        return userRoleCountService.countUserShop(userRoleDTO);
    }
    /***
     * 用户角色 -- 原料供应商  统计
     * @return
     */
    @RequestMapping(value = "countUserStore", method = RequestMethod.POST)
    public Result countUserStore(@RequestBody UserRoleDTO userRoleDTO) {
        return userRoleCountService.countUserStore(userRoleDTO);
    }
    /***
     * 用户角色 -- 配套服务/文化服务  统计
     * @return
     */
    @RequestMapping(value = "countUserSupporting", method = RequestMethod.POST)
    public Result countUserSupporting(@RequestBody UserRoleDTO userRoleDTO) {
        return userRoleCountService.countUserSupporting(userRoleDTO);
    }
    /***
     * 用户角色 -- 金融服务  统计
     * @return
     */
    @RequestMapping(value = "countUserBanking", method = RequestMethod.POST)
    public Result countUserBanking(@RequestBody UserRoleDTO userRoleDTO) {
        return userRoleCountService.countUserBanking(userRoleDTO);
    }

}
