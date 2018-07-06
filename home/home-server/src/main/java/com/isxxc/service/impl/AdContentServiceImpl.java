package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.AdContentDAO;
import com.isxxc.dao.AdContentImgDAO;
import com.isxxc.domain.dto.AdContentDTO;
import com.isxxc.domain.dto.AdContentImgDTO;
import com.isxxc.domain.entity.AdContentDO;
import com.isxxc.service.AdContentService;

import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.FileUtils;

/**
 * <p>
 * 广告内容 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdContentServiceImpl extends ServiceImpl<AdContentDAO, AdContentDO> implements AdContentService {

    @Resource
    private AdContentImgDAO adContentImgDAO;

    @Resource
    private AdContentDAO adContentDAO;

    @Override
    public Result save(AdContentDTO adContentDTO) {
        adContentDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        adContentDTO.setPublishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        adContentDTO.setGmtModified(new Date());
        adContentDTO.setGmtCreate(new Date());
        insert(adContentDTO);
        adContentDTO.getImgList().forEach(adContentImgDTO -> {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(adContentImgDTO.getName()), CommonFolderConstant.getAdPath());
            adContentImgDTO.setAdContentId(adContentDTO.getId());
            adContentImgDAO.insert(adContentImgDTO);
        });
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Page page) {
        EntityWrapper<AdContentDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        page = selectPage(page, entityWrapper);
        List<AdContentDO> adContentDOList = page.getRecords();
        List<AdContentDTO> adContentDTOList = new ArrayList<>(adContentDOList.size());
        adContentDOList.forEach(adContentDO -> {
            AdContentDTO adContentDTO = new AdContentDTO(adContentDO);
            List<AdContentImgDTO> adContentImgDTOList = adContentImgDAO.selectByAdContentId(adContentDO.getId());
            adContentDTO.setImgList(adContentImgDTOList);
            adContentDTOList.add(adContentDTO);
        });
        page.setRecords(adContentDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result updateInfo(AdContentDTO adContentDTO) {
        adContentDTO.setGmtModified(new Date());
        updateById(adContentDTO);
        List<AdContentImgDTO> adContentImgDTODBList = adContentImgDAO.selectByAdContentId(adContentDTO.getId());
        ListUtils.subtract(adContentDTO.getImgList(), adContentImgDTODBList).forEach(adContentImgDTO -> {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(adContentImgDTO.getName()), CommonFolderConstant.getAdPath());
            adContentImgDTO.setAdContentId(adContentDTO.getId());
            adContentImgDAO.insert(adContentImgDTO);
        });
        ListUtils.subtract(adContentImgDTODBList, adContentDTO.getImgList()).forEach(adContentImgDTO -> {
            adContentImgDAO.deleteById(adContentImgDTO.getId());
            FileUtils.delete(CommonFolderConstant.getAdPath() + adContentImgDTO.getName());
        });
        return ResponseResult.success();
    }

    @Override
    public Result delById(Integer id) {
        AdContentDO adContentDO = selectById(id);
        adContentDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(adContentDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listByCode(String code) {
        List<AdContentDTO> adContentDTOList = adContentDAO.listByCode(code, CommonStateEnum.PublishState.PUBLISHED.code, CommonStateEnum.IsDeleted.NOT_DELETED.code);
        adContentDTOList.forEach(adContentDTO -> {
            List<AdContentImgDTO> adContentImgDTOList = adContentImgDAO.selectByAdContentId(adContentDTO.getId());
            adContentDTO.setImgList(adContentImgDTOList);
        });
        return ResponseResult.success(adContentDTOList);
    }

    @Override
    public Result publish(Integer id) {
        AdContentDO adContentDO = selectById(id);
        adContentDO.setPublishState(CommonStateEnum.PublishState.PUBLISHED.code);
        adContentDO.setPublishTime(new Date());
        return updateById(adContentDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result cancelPublish(Integer id) {
        AdContentDO adContentDO = selectById(id);
        adContentDO.setPublishState(CommonStateEnum.PublishState.OFFLINE.code);
        return updateById(adContentDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
