package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.LoansInfoDAO;
import com.isxxc.domain.dto.LoansInfoDTO;
import com.isxxc.domain.entity.LoansInfoDO;
import com.isxxc.service.LoansInfoService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
        loansInfoDTO.setUserId(null);
        loansInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        loansInfoDTO.setAuditState(CommonStateEnum.AuditState.PASSED.code);
        loansInfoDTO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
        return baseListPage(page, loansInfoDTO);
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
    public Result listPageByUserId(Page page, LoansInfoDTO loansInfoDTO) {
        loansInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        return baseListPage(page, loansInfoDTO);
    }

    @Override
    public Result cancelPublist(Integer id, Integer userId) {
        EntityWrapper<LoansInfoDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        LoansInfoDO loansInfoDO = selectOne(entityWrapper);
        loansInfoDO.setPuslishState(CommonStateEnum.PublishState.OFFLINE.code);
        return updateById(loansInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result publist(Integer id, Integer userId) {
        EntityWrapper<LoansInfoDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        LoansInfoDO loansInfoDO = selectOne(entityWrapper);
        if (CommonStateEnum.AuditState.PASSED.code != loansInfoDO.getAuditState()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "信息审核中,请耐心等待,审核后自动发布");
        }
        loansInfoDO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
        loansInfoDO.setPublishTime(new Date());
        return updateById(loansInfoDO) ? ResponseResult.success("发布成功") : ResponseResult.serverError();
    }

    @Override
    public Result mayLike(Integer size) {
        List<Integer> idList = loansInfoDAO.selectIds(CommonStateEnum.IsDeleted.NOT_DELETED.code, CommonStateEnum.PublishState.PUBLISHED.code);
        if (CollectionUtils.isEmpty(idList)) {
            return ResponseResult.success();
        }
        Collections.shuffle(idList);
        idList = idList.subList(0, size >= idList.size() ? idList.size() : size);
        List<LoansInfoDO> loansInfoDOList = selectBatchIds(idList);
        List<LoansInfoDTO> loansInfoDTOList = new ArrayList<>(loansInfoDOList.size());
        loansInfoDOList.forEach(loansInfoDODB -> {
            LoansInfoDTO loansInfoDTO = new LoansInfoDTO(loansInfoDODB);
            if (StringUtils.isNotBlank(loansInfoDTO.getAdImg())) {
                loansInfoDTO.setAdImgWebPath(CommonFolderConstant.getLoansInfoWebPath(loansInfoDTO.getAdImg()));
            }
            loansInfoDTOList.add(loansInfoDTO);
        });
        return ResponseResult.success(loansInfoDTOList);
    }

    private Result baseListPage(Page page, LoansInfoDTO loansInfoDTO) {
        List<LoansInfoDTO> loansInfoDTOList = loansInfoDAO.listPage(page, loansInfoDTO);
        loansInfoDTOList.forEach(loansInfoDTODB -> {
            if (StringUtils.isNotBlank(loansInfoDTODB.getAdImg())) {
                loansInfoDTODB.setAdImgWebPath(CommonFolderConstant.getLoansInfoWebPath(loansInfoDTODB.getAdImg()));
            }
        });
        page.setRecords(loansInfoDTOList);
        return ResponseResult.success(page);
    }
}
