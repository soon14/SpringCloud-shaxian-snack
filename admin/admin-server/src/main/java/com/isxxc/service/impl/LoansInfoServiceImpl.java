package com.isxxc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.LoansInfoDAO;
import com.isxxc.domain.dto.LoansInfoDTO;
import com.isxxc.domain.entity.LoansInfoDO;
import com.isxxc.service.LoansInfoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoansInfoServiceImpl extends ServiceImpl<LoansInfoDAO, LoansInfoDO> implements LoansInfoService {

    @Resource
    private LoansInfoDAO loansInfoDAO;

    @Override
    public Result save(LoansInfoDO loansInfoDO) {
        loansInfoDO.setUserId(0);
        loansInfoDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        loansInfoDO.setPuslishState(CommonStateEnum.AuditState.UNREVIEWED.code);
        loansInfoDO.setPublishTime(new Date());
        loansInfoDO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        loansInfoDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(loansInfoDO.getAdImg()), CommonFolderConstant.getLoansInfoPath());
        return insert(loansInfoDO) ? ResponseResult.successMsg("添加成功,请耐心等待审核!") : ResponseResult.serverError();
    }

    @Override
    public Result listPage(Page page, LoansInfoDTO loansInfoDTO) {
        loansInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        List<LoansInfoDTO> loansInfoDTOList = loansInfoDAO.listPage(page, loansInfoDTO);
        loansInfoDTOList.forEach(loansInfoDTODB -> loansInfoDTODB.setAdImgWebPath(loansInfoDTODB.getAdImg()));
        page.setRecords(loansInfoDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result getInfoById(Integer id) {
        LoansInfoDTO loansInfoDTO = loansInfoDAO.getInfoById(id);
        loansInfoDTO.setAdImgWebPath(CommonFolderConstant.getLoansInfoWebPath(loansInfoDTO.getAdImg()));
        return ResponseResult.success(loansInfoDTO);
    }

    @Override
    public Result updateInfo(LoansInfoDO loansInfoDO) {
        loansInfoDO.setPuslishState(CommonStateEnum.AuditState.UNREVIEWED.code);
        loansInfoDO.setPublishTime(new Date());
        loansInfoDO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        LoansInfoDO loansInfoDODB = selectById(loansInfoDO.getId());
        if (!loansInfoDODB.getAdImg().equals(loansInfoDO.getAdImg())) {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(loansInfoDO.getAdImg()), CommonFolderConstant.getLoansInfoPath());
        }
        return updateById(loansInfoDO) ? ResponseResult.successMsg("更新成功,请耐心等待审核") : ResponseResult.serverError();
    }

    @Override
    public Result updateRecommend(Integer id) {
        LoansInfoDO loansInfoDO = selectById(id);
        if (loansInfoDO.getIsRecommend() == CommonStateEnum.IsRecommend.NO.code) {
            loansInfoDO.setIsRecommend(CommonStateEnum.IsRecommend.YES.code);
            loansInfoDO.setRecommendTime(new Date());
        } else {
            loansInfoDO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        }
        return updateById(loansInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result delById(Integer id) {
        LoansInfoDO loansInfoDO = selectById(id);
        if (loansInfoDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "ID错误");
        }
        if (loansInfoDO.getUserId() != 0) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "禁止删除会员信息");
        }
        loansInfoDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        loansInfoDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(loansInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
