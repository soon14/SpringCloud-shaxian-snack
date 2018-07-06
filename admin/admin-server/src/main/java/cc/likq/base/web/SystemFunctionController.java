package cc.likq.base.web;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.base.domain.entity.SystemFunctionDO;
import cc.likq.base.service.SystemFunctionService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/systemFunction")
public class SystemFunctionController {

    @Resource
    private SystemFunctionService systemFunctionService;

    /***
     *添加菜单
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(SystemFunctionDO systemFunctionDO) {
        if (systemFunctionDO.getType() == null) {
            return ResponseResult.paramNotNull("类型不能为空");
        }
        if (systemFunctionDO.getParentId() == null) {
            return ResponseResult.paramNotNull("父ID不能为空");
        }
        if (StringUtils.isBlank(systemFunctionDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        if (StringUtils.isBlank(systemFunctionDO.getCode())) {
            return ResponseResult.paramNotNull("Code唯一标识不能为空");
        }
        if (systemFunctionDO.getLevel() == null) {
            return ResponseResult.paramNotNull("Level层级不能为空");
        }
        if (StringUtils.isBlank(systemFunctionDO.getFunctionUrl())) {
            return ResponseResult.paramNotNull("功能Url不能为空");
        }
        if (StringUtils.isBlank(systemFunctionDO.getRouteUrl())) {
            return ResponseResult.paramNotNull("前端路由不能为空");
        }
        return systemFunctionService.save(systemFunctionDO);
    }

    /***
     * 删除菜单
     */
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public Result remove(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemFunctionService.remove(id);
    }

    /***
     * 更新菜单信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(SystemFunctionDO systemFunctionDO) {
        if (systemFunctionDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemFunctionService.updateInfo(systemFunctionDO);
    }

    @RequestMapping(value = "listTree", method = RequestMethod.GET)
    public Result listTree() {
        return systemFunctionService.listTree();
    }

    /**
     * 通过adminId得到用户权限
     * @param adminId
     * @param token
     * @return
     */
    @RequestMapping(value = "getFunction", method = RequestMethod.GET)
    public Result getFunction(String adminId, String token) {
        return systemFunctionService.getFunction(adminId, token);
    }
}
