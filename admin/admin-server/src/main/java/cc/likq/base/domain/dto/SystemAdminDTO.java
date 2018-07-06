package cc.likq.base.domain.dto;

import cc.likq.base.domain.entity.SystemAdminDO;
import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/15
 */
public class SystemAdminDTO extends SystemAdminDO {
    private String departmentName;

    public SystemAdminDTO() {
    }

    public SystemAdminDTO(SystemAdminDO systemAdminDO, String departmentName) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemAdminDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
