package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.UserAccountDO;

import org.apache.commons.lang3.StringUtils;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/22
 */
public class UserAccountDTO extends UserAccountDO {

    /***
     * 验证码
     */
    private String verifyCode;

    private String avaterWebPath;

    public UserAccountDTO() {
    }

    public UserAccountDTO(UserAccountDO userAccountDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userAccountDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(userAccountDO.getAvaterPath())) {
            this.avaterWebPath = CommonFolderConstant.getAvaterWebPath(userAccountDO.getAvaterPath());
        }
    }

    public String getAvaterWebPath() {
        return avaterWebPath;
    }

    public void setAvaterWebPath(String avaterWebPath) {
        this.avaterWebPath = avaterWebPath;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
