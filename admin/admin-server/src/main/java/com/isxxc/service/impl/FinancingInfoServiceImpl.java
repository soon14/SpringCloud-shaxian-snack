package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.FinancingInfoDAO;
import com.isxxc.dao.FinancingInfoImgDAO;
import com.isxxc.domain.dto.FinancingInfoDTO;
import com.isxxc.domain.dto.FinancingInfoImgDTO;
import com.isxxc.domain.entity.FinancingInfoDO;
import com.isxxc.domain.entity.FinancingInfoImgDO;
import com.isxxc.service.FinancingInfoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class FinancingInfoServiceImpl extends ServiceImpl<FinancingInfoDAO, FinancingInfoDO> implements FinancingInfoService {

    @Resource
    private FinancingInfoImgDAO financingInfoImgDAO;

    @Override
    public Result save(FinancingInfoDTO financingInfoDTO) {
        financingInfoDTO.setUserId(0);
        financingInfoDTO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        financingInfoDTO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        financingInfoDTO.setPublishTime(new Date());
        financingInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        financingInfoDTO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        financingInfoDTO.setGmtModified(new Date());
        financingInfoDTO.setGmtCreate(new Date());
        insert(financingInfoDTO);
        financingInfoDTO.getImgList().forEach(img -> {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(img.getName()), CommonFolderConstant.getFinancingPath());
            img.setFinancingInfoId(financingInfoDTO.getId());
            img.setGmtCreate(new Date());
            financingInfoImgDAO.insert(img);
        });
        return ResponseResult.successMsg("提交成功,请耐心等待审核");
    }

    @Override
    public Result listPage(Page page, FinancingInfoDTO financingInfoDTO) {
        EntityWrapper<FinancingInfoDO> entityWrapper = new EntityWrapper<>();
        if (financingInfoDTO.getAuditState() != null) {
            entityWrapper.eq("audit_state", financingInfoDTO.getAuditState());
        }
        if (financingInfoDTO.getPuslishState() != null) {
            entityWrapper.eq("puslish_state", financingInfoDTO.getPuslishState());
        }
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        entityWrapper.orderBy("publish_time", false);
        if (financingInfoDTO.getIsRecommend() != null) {
            entityWrapper.eq("is_recommend", financingInfoDTO.getIsRecommend());
        }
        if (financingInfoDTO.getAmountMin() != null && financingInfoDTO.getAmountMax() != null) {
            entityWrapper.between("amount", financingInfoDTO.getAmountMin(), financingInfoDTO.getAmountMax());
        }
        if (financingInfoDTO.getCategory() != null) {
            entityWrapper.eq("category", financingInfoDTO.getCategory());
        }
        page = selectPage(page, entityWrapper);
        List<FinancingInfoDO> financingInfoDOList = page.getRecords();
        List<FinancingInfoDTO> financingInfoDTOList = new ArrayList<>(financingInfoDOList.size());
        financingInfoDOList.forEach(financingInfoDO -> {
            FinancingInfoDTO financingInfoDTODB = new FinancingInfoDTO(financingInfoDO);
            List<FinancingInfoImgDO> financingInfoImgDOList = financingInfoImgDAO.selectByFinancingInfoId(financingInfoDO.getId());
            List<FinancingInfoImgDTO> financingInfoImgDTOList = new ArrayList<>(financingInfoImgDOList.size());
            financingInfoImgDOList.forEach(img -> financingInfoImgDTOList.add(new FinancingInfoImgDTO(img)));
            financingInfoDTODB.setImgList(financingInfoImgDTOList);
            financingInfoDTOList.add(financingInfoDTODB);
        });
        page.setRecords(financingInfoDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result updateInfo(FinancingInfoDTO financingInfoDTO) {
        financingInfoDTO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        financingInfoDTO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        financingInfoDTO.setPublishTime(new Date());
        financingInfoDTO.setGmtModified(new Date());
        updateById(financingInfoDTO);
        List<FinancingInfoImgDO> financingInfoImgDOList = financingInfoImgDAO.selectByFinancingInfoId(financingInfoDTO.getId());
        financingInfoDTO.getImgList().forEach(img -> {
            if (financingInfoImgDOList.contains(img)) {
                financingInfoImgDOList.remove(img);
            } else {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(img.getName()), CommonFolderConstant.getFinancingPath());
                img.setFinancingInfoId(financingInfoDTO.getId());
                img.setGmtCreate(new Date());
                financingInfoImgDAO.insert(img);
            }
        });
        financingInfoImgDOList.forEach(img -> {
            financingInfoImgDAO.deleteById(img.getId());
            FileUtils.delete(CommonFolderConstant.getFinancingPath() + img.getName());
        });
        return ResponseResult.successMsg("更新成功,请耐心等待审核");
    }

    @Override
    public Result getInfoById(Integer id) {
        FinancingInfoDO financingInfoDO = selectById(id);
        FinancingInfoDTO financingInfoDTODB = new FinancingInfoDTO(financingInfoDO);
        List<FinancingInfoImgDO> financingInfoImgDOList = financingInfoImgDAO.selectByFinancingInfoId(financingInfoDO.getId());
        List<FinancingInfoImgDTO> financingInfoImgDTOList = new ArrayList<>(financingInfoImgDOList.size());
        financingInfoImgDOList.forEach(img -> financingInfoImgDTOList.add(new FinancingInfoImgDTO(img)));
        financingInfoDTODB.setImgList(financingInfoImgDTOList);
        return ResponseResult.success(financingInfoDTODB);
    }

    @Override
    public Result updateRecommend(Integer id) {
        FinancingInfoDO financingInfoDO = selectById(id);
        if (financingInfoDO.getIsRecommend() == CommonStateEnum.IsRecommend.NO.code) {
            financingInfoDO.setIsRecommend(CommonStateEnum.IsRecommend.YES.code);
            financingInfoDO.setRecommendTime(new Date());
        } else {
            financingInfoDO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        }
        return updateById(financingInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result delById(Integer id) {
        FinancingInfoDO financingInfoDO = selectById(id);
        if (financingInfoDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "ID错误");
        }
        if (financingInfoDO.getUserId() != 0) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "禁止删除会员信息");
        }
        financingInfoDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        financingInfoDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(financingInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
