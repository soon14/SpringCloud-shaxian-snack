package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.InformationImgDAO;
import com.isxxc.dao.InformationInfoDAO;
import com.isxxc.dao.InformationTypeDAO;
import com.isxxc.domain.dto.InformationImgDTO;
import com.isxxc.domain.dto.InformationInfoDTO;
import com.isxxc.domain.entity.InformationImgDO;
import com.isxxc.domain.entity.InformationInfoDO;
import com.isxxc.service.InformationInfoService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.constant.RedisKeyConstant;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import redis.clients.jedis.Jedis;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InformationInfoServiceImpl extends ServiceImpl<InformationInfoDAO, InformationInfoDO> implements InformationInfoService {

    @Resource
    private InformationTypeDAO informationTypeDAO;

    @Resource
    private InformationImgDAO informationImgDAO;

    @Resource
    private InformationInfoDAO informationInfoDAO;

    @Resource
    private RedisClient redisClient;

    @Override
    public Result listPage(Page page, InformationInfoDO informationInfoDO) {
        EntityWrapper<InformationInfoDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        entityWrapper.eq("puslish_state", CommonStateEnum.PublishState.PUBLISHED.code);
        entityWrapper.orderBy("publish_time", false);
        if (informationInfoDO.getTypeId() != null) {
            entityWrapper.eq("type_id", informationInfoDO.getTypeId());
        }
        if (informationInfoDO.getCategory() != null) {
            entityWrapper.eq("category", informationInfoDO.getCategory());
        }
        page = selectPage(page, entityWrapper);
        List<InformationInfoDTO> informationInfoDTOList = new ArrayList<>(page.getRecords().size());
        //资源路径封装
        List<InformationInfoDO> informationInfoDODBList = page.getRecords();
        informationInfoDODBList.forEach(informationInfoDODB -> {
            InformationInfoDTO informationInfoDTO = new InformationInfoDTO(informationInfoDODB);
            if (informationInfoDODB.getTypeId() != null) {
                String typeName = informationTypeDAO.selectById(informationInfoDODB.getTypeId()).getName();
                if (StringUtils.isNotBlank(typeName)) {
                    informationInfoDTO.setTypeName(typeName);
                }
            }
            informationInfoDTO.setPageUrl(CommonFolderConstant.getInformationPageWebPath(informationInfoDODB.getSourceName(), "index.html"));
            //图片资源路径封装
            List<InformationImgDO> informationImgDOList = informationImgDAO.selectByInfoId(informationInfoDODB.getId());
            List<InformationImgDTO> informationImgDTOList = new ArrayList<>(informationImgDOList.size());
            informationImgDOList.forEach(informationImgDO -> informationImgDTOList.add(new InformationImgDTO(informationImgDO, informationInfoDODB.getSourceName())));
            informationInfoDTO.setCoverImgList(informationImgDTOList);
            informationInfoDTOList.add(informationInfoDTO);
        });
        page.setRecords(informationInfoDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result pageviewIncr(String sourceName) {
        String pageviewKey = RedisKeyConstant.getPageviewKey(sourceName);
        Jedis jedis = redisClient.getResource();
        jedis.incr(pageviewKey);
        redisClient.returnBrokenResource(jedis);
        return ResponseResult.success();
    }

    @Override
    public int pageview(String sourceName) {
        String pageview = redisClient.get(RedisKeyConstant.getPageviewKey(sourceName));
        return StringUtils.isNotBlank(pageview) ? Integer.parseInt(pageview) : 1;
    }

    @Override
    public Result mayLike(Integer size, Integer typeId, Integer category) {
        List<Integer> idList = informationInfoDAO.selectIds(CommonStateEnum.IsDeleted.NOT_DELETED.code, CommonStateEnum.PublishState.PUBLISHED.code, typeId, category);
        if (CollectionUtils.isEmpty(idList)) {
            return ResponseResult.success();
        }
        Collections.shuffle(idList);
        idList = idList.subList(0, size >= idList.size() ? idList.size() : size);

        //资源路径封装
        List<InformationInfoDO> informationInfoDODBList = selectBatchIds(idList);
        List<InformationInfoDTO> informationInfoDTOList = new ArrayList<>(informationInfoDODBList.size());
        informationInfoDODBList.forEach(informationInfoDODB -> {
            InformationInfoDTO informationInfoDTO = new InformationInfoDTO(informationInfoDODB);
            informationInfoDTO.setPageUrl(CommonFolderConstant.getInformationPageWebPath(informationInfoDODB.getSourceName(), "index.html"));
            //图片资源路径封装
            List<InformationImgDO> informationImgDOList = informationImgDAO.selectByInfoId(informationInfoDODB.getId());
            List<InformationImgDTO> informationImgDTOList = new ArrayList<>(informationImgDOList.size());
            informationImgDOList.forEach(informationImgDO -> informationImgDTOList.add(new InformationImgDTO(informationImgDO, informationInfoDODB.getSourceName())));
            informationInfoDTO.setCoverImgList(informationImgDTOList);
            informationInfoDTOList.add(informationInfoDTO);
        });
        return ResponseResult.success(informationInfoDTOList);
    }

}
