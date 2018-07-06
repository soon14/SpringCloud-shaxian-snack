package cc.likq.base.domain.dto;

import java.util.List;

import cc.likq.base.domain.entity.SystemFunctionDO;
import cc.likq.util.MyBeanUtils;

public class SystemFunctionDTO extends SystemFunctionDO {
    /***
     * 当前菜单下的子菜单
     */
    private List<SystemFunctionDTO> childList;

    public SystemFunctionDTO() {
    }

    public SystemFunctionDTO(SystemFunctionDO systemFunctionDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemFunctionDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SystemFunctionDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<SystemFunctionDTO> childList) {
        this.childList = childList;
    }
}
