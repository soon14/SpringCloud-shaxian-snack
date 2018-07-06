package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserShopProfilDAO;
import com.isxxc.dao.UserShopProfilImgDAO;
import com.isxxc.domain.dto.UserShopProfilDTO;
import com.isxxc.domain.dto.UserShopProfilImgDTO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.domain.entity.UserShopProfilImgDO;
import com.isxxc.service.UserShopProfilService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class UserShopProfilServiceImpl extends ServiceImpl<UserShopProfilDAO, UserShopProfilDO> implements UserShopProfilService {

    @Resource
    private UserShopProfilDAO userShopProfilDAO;

    @Resource
    private UserShopProfilImgDAO userShopProfilImgDAO;

    @Override
    public Result save(UserShopProfilDO userShopProfilDO) {
//        UserShopProfilDO userShopProfilDODB = userShopProfilDAO.selectByUserId(userShopProfilDO.getUserId());
//        if (userShopProfilDODB != null) {
//            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经提交过信息,请不要重新提交");
//        }
        userShopProfilDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        userShopProfilDO.setIsComplete(CommonStateEnum.IsComplete.WANE.code);
        if (insert(userShopProfilDO)) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
            return ResponseResult.successMsg("提交成功,请等待审核");
        } else {
            return ResponseResult.serverError();
        }
    }

    @Override
    public Result updateInfo(UserShopProfilDO userShopProfilDO) {
        UserShopProfilDO userShopProfilDODB = selectById(userShopProfilDO.getId());
        if (userShopProfilDODB == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案信息有误");
        }
        if (!userShopProfilDO.getUserId().equals(userShopProfilDO.getUserId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "档案信息有误,请重新登录再试");
        }
        if (userShopProfilDODB.getAuditState() == CommonStateEnum.AuditState.PASSED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已审核档案信息不可修改");
        }
        if (!userShopProfilDO.getIdentityCardFrontImg().equals(userShopProfilDO.getIdentityCardFrontImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardFrontImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证正面不存在,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardFrontImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userShopProfilDO.getIdentityCardBackImg().equals(userShopProfilDO.getIdentityCardBackImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardBackImg())).exists()) {
                return ResponseResult.paramNotNull("个人身份证反面不存在,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilDO.getIdentityCardBackImg()), CommonFolderConstant.getUserProfilPath());
        }
        if (!userShopProfilDO.getBusinessLicenseImg().equals(userShopProfilDO.getBusinessLicenseImg())) {
            if (!new File(CommonFolderConstant.getImageTempPath(userShopProfilDO.getBusinessLicenseImg())).exists()) {
                return ResponseResult.paramNotNull("企业/个体工商户营业执照不存在,请重新上传");
            }
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilDO.getBusinessLicenseImg()), CommonFolderConstant.getUserProfilPath());
        }
        try {
            MyBeanUtils.copyBeanNotNull2Bean(userShopProfilDO, userShopProfilDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(userShopProfilDODB) ? ResponseResult.successMsg("更新成功") : ResponseResult.serverError();
    }

    @Override
    public Result getInfoByUserId(Integer userId) {
        UserShopProfilDO userShopProfilDO = userShopProfilDAO.selectByUserId(userId);
        if (userShopProfilDO == null) {
            return ResponseResult.successMsg("未进行登记");
        }
        List<UserShopProfilImgDO> userShopProfilImgDOList = userShopProfilImgDAO.selectByShopProfilId(userShopProfilDO.getId());
        List<UserShopProfilImgDTO> userShopProfilImgDTOList = new ArrayList<>(userShopProfilImgDOList.size());
        userShopProfilImgDOList.forEach(userShopProfilImgDO -> userShopProfilImgDTOList.add(new UserShopProfilImgDTO(userShopProfilImgDO)));
        UserShopProfilDTO userShopProfilDTO = new UserShopProfilDTO(userShopProfilDO);
        userShopProfilDTO.setImgList(userShopProfilImgDTOList);
        return ResponseResult.success(userShopProfilDTO);
    }

    @Override
    public Result listPage(Page<UserShopProfilDTO> page, UserShopProfilDO userShopProfilDO) {
        EntityWrapper<UserShopProfilDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("audit_state", CommonStateEnum.AuditState.PASSED.code);
        entityWrapper.eq("is_complete", CommonStateEnum.IsComplete.FULL.code);
        entityWrapper.orderBy("gmt_create", false);
        if (userShopProfilDO.getProvinceCode() != null) {
            entityWrapper.eq("province_code", userShopProfilDO.getProvinceCode());
        }
        if (userShopProfilDO.getCityCode() != null) {
            entityWrapper.eq("city_code", userShopProfilDO.getCityCode());
        }
        if (userShopProfilDO.getAreaCode() != null) {
            entityWrapper.eq("area_code", userShopProfilDO.getAreaCode());
        }
        List<UserShopProfilDO> userShopProfilDOList = userShopProfilDAO.selectPage(page, entityWrapper);
        List<UserShopProfilDTO> userShopProfilDTOList = new ArrayList<>(userShopProfilDOList.size());
        userShopProfilDOList.forEach(userShopProfilDODB -> {
            List<UserShopProfilImgDO> userShopProfilImgDOList = userShopProfilImgDAO.selectByShopProfilId(userShopProfilDODB.getId());
            List<UserShopProfilImgDTO> userShopProfilImgDTOList = new ArrayList<>(userShopProfilImgDOList.size());
            userShopProfilImgDOList.forEach(userShopProfilImgDO -> userShopProfilImgDTOList.add(new UserShopProfilImgDTO(userShopProfilImgDO)));
            UserShopProfilDTO userShopProfilDTO = new UserShopProfilDTO(userShopProfilDODB);
            userShopProfilDTO.setImgList(userShopProfilImgDTOList);
            userShopProfilDTOList.add(userShopProfilDTO);
        });
        page.setRecords(userShopProfilDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result completeInfo(UserShopProfilDTO userShopProfilDTO) {
        UserShopProfilDO userShopProfilDODB = userShopProfilDAO.selectByUserId(userShopProfilDTO.getUserId());
        userShopProfilDTO.setId(userShopProfilDODB.getId());
        userShopProfilDTO.setIsComplete(CommonStateEnum.IsComplete.FULL.code);
        userShopProfilDTO.setGmtModified(new Date());
        updateById(userShopProfilDTO);
        List<UserShopProfilImgDO> userShopProfilImgDOList = userShopProfilImgDAO.selectByShopProfilId(userShopProfilDODB.getId());
        List<UserShopProfilImgDTO> userShopProfilImgDTOList = new ArrayList<>(userShopProfilDTO.getImgList());
        if (userShopProfilImgDOList != null && !userShopProfilImgDOList.isEmpty() && userShopProfilDTO.getImgList() != null && !userShopProfilDTO.getImgList().isEmpty()) {
            userShopProfilImgDTOList.removeAll(userShopProfilImgDOList);
        }
        userShopProfilImgDTOList.forEach(userShopProfilImgDTO -> {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(userShopProfilImgDTO.getName()), CommonFolderConstant.getUserProfilPath());
            userShopProfilImgDTO.setShopProfilId(userShopProfilDTO.getId());
            userShopProfilImgDTO.setGmtCreate(new Date());
            userShopProfilImgDTO.setGmtModified(new Date());
            userShopProfilImgDAO.insert(userShopProfilImgDTO);
        });
        if (userShopProfilImgDOList != null && !userShopProfilImgDOList.isEmpty()) {
            userShopProfilImgDOList.removeAll(userShopProfilDTO.getImgList());
            userShopProfilImgDOList.forEach(userShopProfilImgDO -> {
                userShopProfilImgDAO.deleteById(userShopProfilImgDO.getId());
                FileUtils.delete(CommonFolderConstant.getUserProfilPath() + userShopProfilImgDO.getName());
            });
        }
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        UserShopProfilDO userShopProfilDO = userShopProfilDAO.selectById(id);
        if (userShopProfilDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "ID有误,信息不存在");
        }
        List<UserShopProfilImgDO> userShopProfilImgDOList = userShopProfilImgDAO.selectByShopProfilId(userShopProfilDO.getId());
        List<UserShopProfilImgDTO> userShopProfilImgDTOList = new ArrayList<>(userShopProfilImgDOList.size());
        userShopProfilImgDOList.forEach(userShopProfilImgDO -> userShopProfilImgDTOList.add(new UserShopProfilImgDTO(userShopProfilImgDO)));
        UserShopProfilDTO userShopProfilDTO = new UserShopProfilDTO(userShopProfilDO);
        userShopProfilDTO.setImgList(userShopProfilImgDTOList);
        return ResponseResult.success(userShopProfilDTO);
    }
}
