package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserSupportingProfilDAO;
import com.isxxc.domain.dto.UserSupportingProfilDTO;
import com.isxxc.domain.entity.UserSupportingProfilDO;
import com.isxxc.service.UserSupportingProfilService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;
import cc.likq.util.MyBeanUtils;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSupportingProfilServiceImpl extends ServiceImpl<UserSupportingProfilDAO, UserSupportingProfilDO> implements UserSupportingProfilService {

    @Resource
    private UserSupportingProfilDAO userSupportingProfilDAO;

    @Override
    public Result save(UserSupportingProfilDO userSupportingProfilDO) {
//        UserSupportingProfilDO userSupportingProfilDODB = userSupportingProfilDAO.selectByUserId(userSupportingProfilDO.getUserId());
//        if (userSupportingProfilDODB != null) {
//            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经提交过信息,请不要重新提交");
//        }
        userSupportingProfilDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        if (insert(userSupportingProfilDO)) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
            return ResponseResult.successMsg("提交成功,请等待审核");
        } else {
            return ResponseResult.serverError();
        }
    }

    @Override
    public Result updateInfo(UserSupportingProfilDO userSupportingProfilDO) {
        UserSupportingProfilDO userSupportingProfilDODB = selectById(userSupportingProfilDO.getId());
        if (userSupportingProfilDODB == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案不存在");
        }
        if (!userSupportingProfilDO.getUserId().equals(userSupportingProfilDODB.getUserId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案信息有误,请重新登录再试");
        }
        if (userSupportingProfilDO.getAuditState() == CommonStateEnum.AuditState.PASSED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已审核档案信息不可修改");
        }
        if (!userSupportingProfilDO.getIdentityCardFrontImg().equals(userSupportingProfilDODB.getIdentityCardFrontImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardFrontImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证正面不存在,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userSupportingProfilDO.getIdentityCardBackImg().equals(userSupportingProfilDODB.getIdentityCardBackImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardBackImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证反面不存在,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userSupportingProfilDO.getBusinessLicenseImg().equals(userSupportingProfilDODB.getBusinessLicenseImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getBusinessLicenseImg())).exists()) {
                return ResponseResult.paramNotNull("企业/个体工商户营业执照不存在,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
        }
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userSupportingProfilDO, userSupportingProfilDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(userSupportingProfilDODB) ? ResponseResult.successMsg("更新成功") : ResponseResult.serverError();
    }

    @Override
    public Result getInfoByUserId(Integer userId) {
        UserSupportingProfilDO userSupportingProfilDO = userSupportingProfilDAO.selectByUserId(userId);
        if (userSupportingProfilDO == null) {
            return ResponseResult.successMsg("未进行登记");
        }
        return ResponseResult.success(new UserSupportingProfilDTO(userSupportingProfilDO));
    }

    @Override
    public Result completeInfo(UserSupportingProfilDO userSupportingProfilDO) {
        UserSupportingProfilDO userSupportingProfilDODB = userSupportingProfilDAO.selectByUserId(userSupportingProfilDO.getUserId());
        userSupportingProfilDO.setId(userSupportingProfilDODB.getId());
        if (StringUtils.isNotBlank(userSupportingProfilDO.getLogo()) &&
                (StringUtils.isBlank(userSupportingProfilDODB.getLogo()) || !userSupportingProfilDO.getLogo().equals(userSupportingProfilDODB.getLogo()))) {
            if (!new File(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getLogo())).exists()) {
                return ResponseResult.paramNotNull("LOGO已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userSupportingProfilDO.getLogo()), CommonFolderConstant.getUserProfilPath());
        }
        return updateById(userSupportingProfilDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
