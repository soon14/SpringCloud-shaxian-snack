package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserStoreProfilDAO;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.UserStoreProfilDO;
import com.isxxc.service.UserStoreProfilService;

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
public class UserStoreProfilServiceImpl extends ServiceImpl<UserStoreProfilDAO, UserStoreProfilDO> implements UserStoreProfilService {

    @Resource
    private UserStoreProfilDAO userStoreProfilDAO;

    @Override
    public Result save(UserStoreProfilDO storeProfilDO) {
//        UserStoreProfilDO userStoreProfilDODB = userStoreProfilDAO.selectByUserId(storeProfilDO.getUserId());
//        if (userStoreProfilDODB != null) {
//            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经提交过信息,请不要重新提交");
//        }
        storeProfilDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        if (insert(storeProfilDO)) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(storeProfilDO.getIndividualBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(storeProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(storeProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(storeProfilDO.getBankImg()), CommonFolderConstant.getUserProfilPath());
            if (StringUtils.isNotBlank(storeProfilDO.getBusinessLicenseImg()) && new File(CommonFolderConstant.getImageTempPath(storeProfilDO.getBusinessLicenseImg())).exists()) {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(storeProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            }
            return ResponseResult.successMsg("提交成功,请等待审核");
        } else {
            return ResponseResult.serverError();
        }
    }

    @Override
    public Result updateInfo(UserStoreProfilDO userStoreProfilDO) {
        UserStoreProfilDO userStoreProfilDODB = selectById(userStoreProfilDO.getId());
        if (userStoreProfilDODB == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案不存在");
        }
        if (!userStoreProfilDO.getUserId().equals(userStoreProfilDODB.getUserId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案信息有误,请重新登录再试");
        }
        if (userStoreProfilDO.getAuditState() == CommonStateEnum.AuditState.PASSED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已审核档案信息不可修改");
        }
        if (!userStoreProfilDO.getIdentityCardFrontImg().equals(userStoreProfilDODB.getIdentityCardFrontImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIdentityCardFrontImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证正面已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userStoreProfilDO.getIdentityCardBackImg().equals(userStoreProfilDODB.getIdentityCardBackImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIdentityCardBackImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证反面已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userStoreProfilDO.getIndividualBusinessLicenseImg().equals(userStoreProfilDODB.getIndividualBusinessLicenseImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIndividualBusinessLicenseImg())).exists()) {
                return ResponseResult.paramNotNull("企业/个体工商户营业执照已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getIndividualBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userStoreProfilDO.getBankImg().equals(userStoreProfilDODB.getBankImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBankImg())).exists()) {
                return ResponseResult.paramNotNull("银行卡照片已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBankImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getBusinessLicenseImg()) &&
                (StringUtils.isBlank(userStoreProfilDODB.getBusinessLicenseImg()) || !userStoreProfilDO.getBusinessLicenseImg().equals(userStoreProfilDODB.getBusinessLicenseImg()))) {
            if (!userStoreProfilDO.getBusinessLicenseImg().equals(userStoreProfilDODB.getBusinessLicenseImg())) {
                if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBusinessLicenseImg())).exists()) {
                    return ResponseResult.paramNotNull("食品经营许可证已失效,请重新上传");
                }
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            }
        } else if (StringUtils.isBlank(userStoreProfilDO.getBusinessLicenseImg())) {
            userStoreProfilDO.setBusinessLicenseImg("");
        }
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userStoreProfilDO, userStoreProfilDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(userStoreProfilDODB) ? ResponseResult.successMsg("更新成功") : ResponseResult.serverError();
    }

    @Override
    public Result getInfoByUserId(Integer userId) {
        UserStoreProfilDO userStoreProfilDO = userStoreProfilDAO.selectByUserId(userId);
        if (userStoreProfilDO == null) {
            return ResponseResult.successMsg("未进行登记");
        }
        return ResponseResult.success(new UserStoreProfilDTO(userStoreProfilDO));
    }

    @Override
    public Result completeInfo(UserStoreProfilDO userStoreProfilDO) {
        UserStoreProfilDO userStoreProfilDODB = userStoreProfilDAO.selectByUserId(userStoreProfilDO.getUserId());
        userStoreProfilDO.setId(userStoreProfilDODB.getId());
        if (StringUtils.isNotBlank(userStoreProfilDO.getLogo()) &&
                (StringUtils.isBlank(userStoreProfilDODB.getLogo()) || !userStoreProfilDO.getLogo().equals(userStoreProfilDODB.getLogo()))) {
            if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getLogo())).exists()) {
                return ResponseResult.paramNotNull("LOGO已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getLogo()), CommonFolderConstant.getUserProfilPath());
        }
        if (StringUtils.isNotBlank(userStoreProfilDO.getBanner()) &&
                (StringUtils.isBlank(userStoreProfilDODB.getBanner()) || !userStoreProfilDO.getBanner().equals(userStoreProfilDODB.getBanner()))) {
            if (!new File(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBanner())).exists()) {
                return ResponseResult.paramNotNull("商家头图已失效,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userStoreProfilDO.getBanner()), CommonFolderConstant.getUserProfilPath());
        }
        return updateById(userStoreProfilDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
