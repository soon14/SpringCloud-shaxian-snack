package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserBankingProfilDAO;
import com.isxxc.domain.dto.UserBankingProfilDTO;
import com.isxxc.domain.entity.UserBankingProfilDO;
import com.isxxc.service.UserBankingProfilService;

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
public class UserBankingProfilServiceImpl extends ServiceImpl<UserBankingProfilDAO, UserBankingProfilDO> implements UserBankingProfilService {

    @Resource
    private UserBankingProfilDAO userBankingProfilDAO;

    @Override
    public Result save(UserBankingProfilDO userBankingProfilDO) {
//        UserBankingProfilDO userBankingProfilDODB = userBankingProfilDAO.selectByUserId(userBankingProfilDO.getUserId());
//        if (userBankingProfilDODB != null) {
//            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经提交过信息,请不要重新提交");
//        }
        userBankingProfilDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        if (insertOrUpdate(userBankingProfilDO)) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
            if (StringUtils.isNotBlank(userBankingProfilDO.getBusinessLicenseImg()) && new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getBusinessLicenseImg())).exists()) {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            }
            if (StringUtils.isNotBlank(userBankingProfilDO.getAgencyCertificateImg()) && new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getAgencyCertificateImg())).exists()) {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getAgencyCertificateImg()), CommonFolderConstant.getUserProfilPath());
            }
            return ResponseResult.successMsg("提交成功,请等待审核");
        } else {
            return ResponseResult.serverError();
        }
    }

    @Override
    public Result updateInfo(UserBankingProfilDO userBankingProfilDO) {
        UserBankingProfilDO userBankingProfilDODB = selectById(userBankingProfilDO.getId());
        if (userBankingProfilDODB == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案不存在");
        }
        if (!userBankingProfilDO.getUserId().equals(userBankingProfilDODB.getUserId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案信息有误,请重新登录再试");
        }
        if (userBankingProfilDO.getAuditState() == CommonStateEnum.AuditState.PASSED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已审核档案信息不可修改");
        }
        if (!userBankingProfilDO.getIdentityCardFrontImg().equals(userBankingProfilDODB.getIdentityCardFrontImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardFrontImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证正面已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userBankingProfilDO.getIdentityCardBackImg().equals(userBankingProfilDODB.getIdentityCardBackImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardBackImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证反面已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getBusinessLicenseImg()) &&
                (StringUtils.isBlank(userBankingProfilDODB.getBusinessLicenseImg()) || !userBankingProfilDO.getBusinessLicenseImg().equals(userBankingProfilDODB.getBusinessLicenseImg()))) {
            if (!userBankingProfilDO.getBusinessLicenseImg().equals(userBankingProfilDODB.getBusinessLicenseImg())) {
                if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getBusinessLicenseImg())).exists()) {
                    return ResponseResult.paramNotNull("经营许可证照片已失效,请重新上传");
                }
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            }
        } else if (StringUtils.isBlank(userBankingProfilDO.getBusinessLicenseImg())) {
            userBankingProfilDO.setBusinessLicenseImg("");
        }
        if (StringUtils.isNotBlank(userBankingProfilDO.getAgencyCertificateImg()) &&
                (StringUtils.isBlank(userBankingProfilDODB.getAgencyCertificateImg()) || !userBankingProfilDO.getAgencyCertificateImg().equals(userBankingProfilDODB.getAgencyCertificateImg()))) {
            if (!userBankingProfilDO.getAgencyCertificateImg().equals(userBankingProfilDODB.getAgencyCertificateImg())) {
                if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getAgencyCertificateImg())).exists()) {
                    return ResponseResult.paramNotNull("经营许可证照片已失效,请重新上传");
                }
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getAgencyCertificateImg()), CommonFolderConstant.getUserProfilPath());
            }
        } else if (StringUtils.isBlank(userBankingProfilDO.getAgencyCertificateImg())) {
            userBankingProfilDO.setAgencyCertificateImg("");
        }
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userBankingProfilDO, userBankingProfilDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(userBankingProfilDODB) ? ResponseResult.successMsg("更新成功") : ResponseResult.serverError();
    }

    @Override
    public Result getInfoByUserId(Integer userId) {
        UserBankingProfilDO userBankingProfilDO = userBankingProfilDAO.selectByUserId(userId);
        if (userBankingProfilDO == null) {
            return ResponseResult.successMsg("未进行登记");
        }
        return ResponseResult.success(new UserBankingProfilDTO(userBankingProfilDO));
    }

    @Override
    public Result completeInfo(UserBankingProfilDO userBankingProfilDO) {
        UserBankingProfilDO userBankingProfilDODB = userBankingProfilDAO.selectByUserId(userBankingProfilDO.getUserId());
        userBankingProfilDO.setId(userBankingProfilDODB.getId());
        if (StringUtils.isNotBlank(userBankingProfilDO.getLogo()) &&
                (StringUtils.isBlank(userBankingProfilDODB.getLogo()) || !userBankingProfilDO.getLogo().equals(userBankingProfilDODB.getLogo()))) {
            if (!new File(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getLogo())).exists()) {
                return ResponseResult.paramNotNull("LOGO已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userBankingProfilDO.getLogo()), CommonFolderConstant.getUserProfilPath());
        }
        return updateById(userBankingProfilDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
