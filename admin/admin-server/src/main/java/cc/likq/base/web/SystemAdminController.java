package cc.likq.base.web;


import com.baomidou.mybatisplus.plugins.Page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.likq.base.domain.dto.SystemAdminDTO;
import cc.likq.base.domain.entity.SystemAdminDO;
import cc.likq.base.service.SystemAdminService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.NetworkUtils;

/**
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {

    @Resource
    private SystemAdminService systemAdminService;

    /***
     * 添加管理员
     * 密码,需要先md5加密
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(SystemAdminDO systemAdminDO) {
        if (StringUtils.isBlank(systemAdminDO.getAccount())) {
            return ResponseResult.paramNotNull("帐号不能为空");
        }
        if (StringUtils.isBlank(systemAdminDO.getPassword())) {
            return ResponseResult.paramNotNull("密码不能为空");
        }
        if (StringUtils.isBlank(systemAdminDO.getName())) {
            return ResponseResult.paramNotNull("姓名不能为空");
        }
        if (StringUtils.isBlank(systemAdminDO.getPosition())) {
            return ResponseResult.paramNotNull("职位不能为空");
        }
        if (systemAdminDO.getDepartmentId() == null) {
            return ResponseResult.paramNotNull("请选择部门");
        }
        if (StringUtils.isBlank(systemAdminDO.getMobileNo())) {
            return ResponseResult.paramNotNull("号码不能为空");
        }
        return systemAdminService.save(systemAdminDO);
    }

    /***
     * 禁用管理员
     */
    @RequestMapping(value = "disable", method = RequestMethod.POST)
    public Result disable(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemAdminService.disable(id);
    }

    /***
     * 取消禁用管理员
     */
    @RequestMapping(value = "cancelDisable", method = RequestMethod.POST)
    public Result cancelDisable(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemAdminService.cancelDisable(id);
    }

    /***
     * 更新管理管理员信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(SystemAdminDO systemAdminDO) {
        if (systemAdminDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemAdminService.updateInfo(systemAdminDO);
    }

    /***
     * 更新密码
     * @param id 管理员ID
     * @param password 旧密码,前端需要加密
     * @param newPassword 新密码
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public Result updatePassword(Integer id, String password, String newPassword) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseResult.paramNotNull("原密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            return ResponseResult.paramNotNull("新密码不能为空");
        }
        return systemAdminService.updatePassword(id, password, newPassword);
    }

    /***
     * 登录
     * @param account 帐号
     * @param password 密码，前端需要进度md5加密
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Result login(String account, String password, HttpServletRequest request) {
        if (StringUtils.isBlank(account)) {
            return ResponseResult.paramNotNull("帐号不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseResult.paramNotNull("密码不能为空");
        }
        String ip = NetworkUtils.getIpAddress(request);
        return systemAdminService.login(account, password, ip);
    }

    /***
     * 注销登录
     * @param adminId 会员ID
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Result logout(Integer adminId) {
        if (adminId == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemAdminService.logout(adminId);
    }

    /***
     * 查询列表
     * @param page
     * @param systemAdminDTO
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page page, SystemAdminDTO systemAdminDTO) {
        return systemAdminService.listPage(page, systemAdminDTO);
    }
}
