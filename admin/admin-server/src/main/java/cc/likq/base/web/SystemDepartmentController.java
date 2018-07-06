package cc.likq.base.web;


import com.baomidou.mybatisplus.plugins.Page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.base.domain.entity.SystemDepartmentDO;
import cc.likq.base.service.SystemDepartmentService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/systemDepartment")
public class SystemDepartmentController {

    @Resource
    private SystemDepartmentService systemDepartmentService;

    /**
     * 添加部门
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(SystemDepartmentDO systemDepartmentDO) {
        if (StringUtils.isBlank(systemDepartmentDO.getName())) {
            return ResponseResult.paramNotNull("部门名称不能为空");
        }
        if (systemDepartmentDO.getParentId() == null) {
            return ResponseResult.paramNotNull("父部门不能为空");
        }
        return systemDepartmentService.save(systemDepartmentDO);
    }

    /***
     * 删除部门
     */
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public Result remove(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemDepartmentService.remove(id);
    }

    /***
     * 查询部门，带分页
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Page<SystemDepartmentDO> page, String name, Integer parentId) {
        return systemDepartmentService.listPage(page, name, parentId);
    }

    /***
     * 查询部门
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list(String name, Integer parentId) {
        return systemDepartmentService.list(name, parentId);
    }

    /***
     * 更新部门
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(SystemDepartmentDO systemDepartmentDO) {
        if (systemDepartmentDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemDepartmentService.updateInfo(systemDepartmentDO);
    }
}
