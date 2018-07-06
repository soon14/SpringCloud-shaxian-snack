package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.ShopTransferDAO;
import com.isxxc.dao.ShopTransferImgDAO;
import com.isxxc.domain.dto.ShopTransferDTO;
import com.isxxc.domain.dto.ShopTransferImgDTO;
import com.isxxc.domain.entity.ShopTransferDO;
import com.isxxc.domain.entity.ShopTransferImgDO;
import com.isxxc.service.ShopTransferService;

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
 * @since 2017-12-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTransferServiceImpl extends ServiceImpl<ShopTransferDAO, ShopTransferDO> implements ShopTransferService {

    @Resource
    private ShopTransferImgDAO shopTransferImgDAO;

    @Override
    public Result save(ShopTransferDTO shopTransferDTO) {
        shopTransferDTO.setUserId(0);
        shopTransferDTO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        shopTransferDTO.setPublishTime(new Date());
        shopTransferDTO.setAuditState(CommonStateEnum.AuditState.UNREVIEWED.code);
        shopTransferDTO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        shopTransferDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        shopTransferDTO.setGmtCreate(new Date());
        shopTransferDTO.setGmtModified(new Date());
        if (shopTransferDTO.getTransferAmount() == null) {
            shopTransferDTO.setTransferAmount(0);
        }
        insert(shopTransferDTO);
        shopTransferDTO.getImgList().forEach(shopTransferImgDTO -> {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopTransferImgDTO.getName()), CommonFolderConstant.getShopTransferPath());
            shopTransferImgDTO.setGmtCreate(new Date());
            shopTransferImgDTO.setTransferId(shopTransferDTO.getId());
            shopTransferImgDAO.insert(shopTransferImgDTO);
        });
        return ResponseResult.success("添加成功,请耐心等待,审核后自动发布");
    }

    @Override
    public Result listPage(Page page, ShopTransferDTO shopTransferDTO) {
        EntityWrapper<ShopTransferDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        if (shopTransferDTO.getPuslishState() != null) {
            entityWrapper.eq("puslish_state", shopTransferDTO.getPuslishState());
        }
        if (shopTransferDTO.getAuditState() != null) {
            entityWrapper.eq("audit_state", shopTransferDTO.getAuditState());
        }
        if (shopTransferDTO.getRentAmountStart() != null && shopTransferDTO.getRentAmountEnd() != null) {
            entityWrapper.between("rent_amount", shopTransferDTO.getRentAmountStart(), shopTransferDTO.getRentAmountEnd());
        }
        if (shopTransferDTO.getTransferAmountStart() != null && shopTransferDTO.getTransferAmountEnd() != null) {
            entityWrapper.between("transfer_amount", shopTransferDTO.getTransferAmountStart(), shopTransferDTO.getTransferAmountEnd());
        }
        if (shopTransferDTO.getAcreageEnd() != null && shopTransferDTO.getAcreageEnd() != null) {
            entityWrapper.between("acreage", shopTransferDTO.getAcreageStart(), shopTransferDTO.getAcreageEnd());
        }
        if (shopTransferDTO.getIdentity() != null) {
            entityWrapper.eq("identity", shopTransferDTO.getIdentity());
        }
        if (shopTransferDTO.getProvinceCode() != null) {
            entityWrapper.eq("province_code", shopTransferDTO.getProvinceCode());
        }
        if (shopTransferDTO.getCityCode() != null) {
            entityWrapper.eq("city_code", shopTransferDTO.getCityCode());
        }
        if (shopTransferDTO.getAreaCode() != null) {
            entityWrapper.eq("area_code", shopTransferDTO.getAreaCode());
        }
        if (shopTransferDTO.getIsRecommend() != null) {
            entityWrapper.eq("is_recommend", shopTransferDTO.getIsRecommend());
            entityWrapper.orderBy("recommend_time", shopTransferDTO.getSortAsc() == 1);
        } else if (shopTransferDTO.getSortType() != null && shopTransferDTO.getSortType() == 0) {
            //面积排序
            entityWrapper.orderBy("acreage", shopTransferDTO.getSortAsc() == 0);
        } else if (shopTransferDTO.getSortType() != null && shopTransferDTO.getSortType() == 1) {
            //月租金排序
            entityWrapper.orderBy("rent_amount", shopTransferDTO.getSortAsc() == 0);
        } else {
            //发布时间排序
            entityWrapper.orderBy("publish_time", shopTransferDTO.getSortAsc() == 1);
        }
        page = selectPage(page, entityWrapper);
        List<ShopTransferDTO> shopTransferDTOList = new ArrayList<>(page.getRecords().size());
        page.getRecords().forEach(shopTransferDO -> {
            ShopTransferDTO shopTransferDTODB = new ShopTransferDTO((ShopTransferDO) shopTransferDO);
            List<ShopTransferImgDO> shopTransferImgDOList = shopTransferImgDAO.selectByTransferId(shopTransferDTODB.getId());
            List<ShopTransferImgDTO> shopTransferImgDTOList = new ArrayList<>(shopTransferImgDOList.size());
            shopTransferImgDOList.forEach(shopTransferImgDO -> shopTransferImgDTOList.add(new ShopTransferImgDTO(shopTransferImgDO)));
            shopTransferDTODB.setImgList(shopTransferImgDTOList);
            shopTransferDTOList.add(shopTransferDTODB);
        });
        page.setRecords(shopTransferDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result updateInfo(ShopTransferDTO shopTransferDTO) {
        ShopTransferDO shopTransferDODB = selectById(shopTransferDTO.getId());
        List<ShopTransferImgDO> shopTransferImgDOList = shopTransferImgDAO.selectByTransferId(shopTransferDODB.getId());
        shopTransferDTO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        shopTransferDTO.setPublishTime(new Date());
        shopTransferDTO.setGmtModified(new Date());
        updateById(shopTransferDTO);
        shopTransferDTO.getImgList().forEach(shopTransferImgDTO -> {
            if (shopTransferImgDOList.contains(shopTransferImgDTO)) {
                shopTransferImgDOList.remove(shopTransferImgDTO);
            } else {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(shopTransferImgDTO.getName()), CommonFolderConstant.getShopTransferPath());
                shopTransferImgDTO.setGmtCreate(new Date());
                shopTransferImgDTO.setTransferId(shopTransferDODB.getId());
                shopTransferImgDAO.insert(shopTransferImgDTO);
            }
        });
        shopTransferImgDOList.forEach(shopTransferImgDO -> {
            shopTransferImgDAO.deleteById(shopTransferImgDO.getId());
            FileUtils.delete(CommonFolderConstant.getShopTransferPath() + shopTransferImgDO.getName());
        });
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        ShopTransferDO shopTransferDO = selectById(id);
        ShopTransferDTO shopTransferDTO = new ShopTransferDTO(shopTransferDO);
        List<ShopTransferImgDO> shopTransferImgDOList = shopTransferImgDAO.selectByTransferId(shopTransferDTO.getId());
        List<ShopTransferImgDTO> shopTransferImgDTOList = new ArrayList<>(shopTransferImgDOList.size());
        shopTransferImgDOList.forEach(shopTransferImgDO -> shopTransferImgDTOList.add(new ShopTransferImgDTO(shopTransferImgDO)));
        shopTransferDTO.setImgList(shopTransferImgDTOList);
        return ResponseResult.success(shopTransferDTO);
    }

    @Override
    public Result updateRecommend(Integer id) {
        ShopTransferDO shopTransferDO = selectById(id);
        if (shopTransferDO.getIsRecommend() == CommonStateEnum.IsRecommend.NO.code) {
            shopTransferDO.setIsRecommend(CommonStateEnum.IsRecommend.YES.code);
            shopTransferDO.setRecommendTime(new Date());
        } else {
            shopTransferDO.setIsRecommend(CommonStateEnum.IsRecommend.NO.code);
        }
        return updateById(shopTransferDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result delById(Integer id) {
        ShopTransferDO shopTransferDO = selectById(id);
        if (shopTransferDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "ID错误");
        }
        if (shopTransferDO.getUserId() != 0) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "禁止删除会员信息");
        }
        shopTransferDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        shopTransferDO.setIsDeleted(CommonStateEnum.IsDeleted.HAVE_DELETED.code);
        return updateById(shopTransferDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

}
