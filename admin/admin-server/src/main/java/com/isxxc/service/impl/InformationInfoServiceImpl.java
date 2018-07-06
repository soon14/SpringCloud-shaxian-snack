package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.InformationTemplate;
import com.isxxc.dao.InformationImgDAO;
import com.isxxc.dao.InformationInfoDAO;
import com.isxxc.domain.dto.InformationImgDTO;
import com.isxxc.domain.dto.InformationInfoDTO;
import com.isxxc.domain.entity.InformationImgDO;
import com.isxxc.domain.entity.InformationInfoDO;
import com.isxxc.service.InformationInfoService;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;
import cc.likq.util.MyBeanUtils;
import cc.likq.util.RandomUtils;
import cc.likq.util.TimeUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InformationInfoServiceImpl extends ServiceImpl<InformationInfoDAO, InformationInfoDO> implements InformationInfoService {

    @Resource
    private InformationImgDAO informationImgDAO;

    @Resource
    private InformationInfoDAO informationInfoDAO;

    @Resource
    private ResourceLoader resourceLoader;

    @Override
    public Result save(InformationInfoDTO informationInfoDTO) {
        //创建存放文件名称
        String dirName = TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED_SSS) + RandomUtils.generateNumberString(5);
        combineHtml(informationInfoDTO, dirName);
        informationInfoDTO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
        informationInfoDTO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        informationInfoDTO.setSourceName(dirName);
        informationInfoDTO.setGmtCreate(new Date());
        informationInfoDTO.setGmtModified(new Date());
        insert(informationInfoDTO);
        String coverImgFolder = CommonFolderConstant.getInformationCoverImgPath(dirName);
        informationInfoDTO.getCoverImgList().forEach(coverImg -> {
            FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(coverImg.getName()), coverImgFolder);
            coverImg.setInfoId(informationInfoDTO.getId());
            coverImg.setGmtCreate(new Date());
            coverImg.setGmtModified(new Date());
            informationImgDAO.insert(coverImg);
        });
        return ResponseResult.success();
    }

    @Override
    public Result getInfoById(Integer id) {
        InformationInfoDO informationInfoDO = selectById(id);
        Document dom = null;
        try {
            dom = Jsoup.parse(new File(CommonFolderConstant.getInformationPagePath(informationInfoDO.getSourceName()) + "index.html"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element contentEle = dom.getElementById("content");
        //获取所有Img标签
        contentEle.getElementsByTag("img").forEach(element -> element.attr("src", CommonFolderConstant.getInformationWebPath(informationInfoDO.getSourceName(), element.attr("src"))));
        //获取所有Video标签
        contentEle.getElementsByTag("video").forEach(element -> element.attr("src", CommonFolderConstant.getInformationWebPath(informationInfoDO.getSourceName(), element.attr("src"))));

        //获取所有主图
        List<InformationImgDO> informationImgDOList = informationImgDAO.selectByInfoId(informationInfoDO.getId());
        List<InformationImgDTO> informationImgDTOList = new ArrayList<>(informationImgDOList.size());
        informationImgDOList.forEach(informationImgDO -> informationImgDTOList.add(new InformationImgDTO(informationImgDO, informationInfoDO.getSourceName())));
        InformationInfoDTO informationInfoDTO = new InformationInfoDTO(informationInfoDO);
        informationInfoDTO.setCoverImgList(informationImgDTOList);
        informationInfoDTO.setContent(contentEle.html());
        return ResponseResult.success(informationInfoDTO);
    }

    @Override
    public Result updateInfo(InformationInfoDTO informationInfoDTO) {
        InformationInfoDTO informationInfoDTODB = new InformationInfoDTO(selectById(informationInfoDTO.getId()));
        if (informationInfoDTODB.getPuslishState() != CommonStateEnum.PublishState.UNPUBLISHED.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已发布,不能修改");
        }
        combineHtml(informationInfoDTO, informationInfoDTODB.getSourceName());
        informationInfoDTO.setGmtModified(new Date());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(informationInfoDTO, informationInfoDTODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        informationInfoDTODB.setGmtModified(new Date());
        updateById(informationInfoDTODB);
        String coverImgFolder = CommonFolderConstant.getInformationCoverImgPath(informationInfoDTODB.getSourceName());
        List<InformationImgDO> informationImgDOList = informationImgDAO.selectByInfoId(informationInfoDTO.getId());
        informationInfoDTO.getCoverImgList().forEach(coverImg -> {
            if (informationImgDOList.contains(coverImg)) {
                informationImgDOList.remove(coverImg);
            } else {
                FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(coverImg.getName()), coverImgFolder);
                coverImg.setInfoId(informationInfoDTO.getId());
                coverImg.setGmtCreate(new Date());
                coverImg.setGmtModified(new Date());
                informationImgDAO.insert(coverImg);
            }
        });
        informationImgDOList.forEach(informationImgDO -> {
            informationImgDAO.deleteById(informationImgDO.getId());
            FileUtils.delete(coverImgFolder + informationImgDO.getName());
        });
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Page page, InformationInfoDO informationInfoDO) {
        EntityWrapper<InformationInfoDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        page = selectPage(page, entityWrapper);
        List<InformationInfoDTO> informationInfoDTOList = new ArrayList<>(page.getRecords().size());
        //资源路径封装
        List<InformationInfoDO> informationInfoDODBList = page.getRecords();
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
        page.setRecords(informationInfoDTOList);
        return ResponseResult.success(page);
    }

    @Override
    public Result publish(Integer id) {
        InformationInfoDO informationInfoDO = selectById(id);
        String pagePath = CommonFolderConstant.getInformationPagePath(informationInfoDO.getSourceName()) + "index.html";
        Document dom = null;
        try {
            dom = Jsoup.parse(new File(pagePath), "UTF-8");
            dom.getElementById("pubDate").html(TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.SHORT_DATE_PATTERN_LINE));
            FileUtils.writeStringToFile(new File(pagePath), dom.outerHtml(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        informationInfoDO.setPublishTime(new Date());
        informationInfoDO.setPuslishState(CommonStateEnum.PublishState.PUBLISHED.code);
        updateById(informationInfoDO);
        return ResponseResult.success();
    }

    @Override
    public Result cancelPublish(Integer id) {
        InformationInfoDO informationInfoDO = selectById(id);
        informationInfoDO.setPuslishState(CommonStateEnum.PublishState.UNPUBLISHED.code);
        updateById(informationInfoDO);
        return ResponseResult.success();
    }


    /***
     *整合页面
     * @param informationInfoDTO
     * @return
     */
    public boolean combineHtml(InformationInfoDTO informationInfoDTO, String dirName) {
        Document dom = Jsoup.parse(informationInfoDTO.getContent());
        //获取所有Img标签
        List<String> imgTags = dom.select("img[src]").eachAttr("src");
        //获取所有Video标签
        List<String> videoTags = dom.select("video[src]").eachAttr("src");
        //图片文件夹
        String imgDirPath = CommonFolderConstant.getInformationImgPath(dirName);
        //视频文件夹
        String videoDirPath = CommonFolderConstant.getInformationVideoPath(dirName);
        //路径替换Map
        Map<String, String> replaceUrlMap = new HashMap<>(imgTags.size() + videoTags.size());
        imgTags.forEach(imgUrl -> {
            String fileName = null;
            try {
                fileName = FileUtils.copyFileByUrl(imgUrl, imgDirPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            replaceUrlMap.put(imgUrl, CommonFolderConstant.getImgWebRelativePath(dirName, fileName));
        });
        videoTags.forEach(videoUrl -> {
            String fileName = null;
            try {
                fileName = FileUtils.copyFileByUrl(videoUrl, videoDirPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            replaceUrlMap.put(videoUrl, CommonFolderConstant.geVideoWebRelativePath(dirName, fileName));
        });
        replaceUrlMap.forEach((k, v) -> informationInfoDTO.setContent(informationInfoDTO.getContent().replaceAll(k, v)));
        String indexHtml = InformationTemplate.getIndexHtmlStr();
        dom = Jsoup.parse(indexHtml);
        if (StringUtils.isNotBlank(informationInfoDTO.getTitle())) {
            dom.getElementById("title").html(informationInfoDTO.getTitle());
        }
        if (StringUtils.isNotBlank(informationInfoDTO.getAuthor())) {
            dom.getElementById("author").html("作者:" + informationInfoDTO.getAuthor());
        }
        if (informationInfoDTO.getCategory() == 1) {
            dom.getElementById("author").remove();
        }
        dom.getElementById("content").html(informationInfoDTO.getContent());
        dom.getElementById("sourceName").val(dirName);
        try {
            String dirPath = CommonFolderConstant.getInformationPagePath(dirName);
            Path paths = Paths.get(dirPath);
            if (!Files.exists(paths)) {
                Files.createDirectory(paths);
            }
            FileUtils.writeStringToFile(new File(CommonFolderConstant.getInformationPagePath(dirName) + "index.html"), dom.outerHtml(), "UTF-8");
            FileUtils.writeStringToFile(new File(CommonFolderConstant.getInformationPagePath(dirName) + "index.css"), InformationTemplate.getIndexCssStr(), "UTF-8");
            FileUtils.writeStringToFile(new File(CommonFolderConstant.getInformationPagePath(dirName) + "index.js"), InformationTemplate.getIndexJsStr(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
