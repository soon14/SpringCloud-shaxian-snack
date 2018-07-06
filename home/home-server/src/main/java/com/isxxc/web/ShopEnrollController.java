package com.isxxc.web;


import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.ShopEnrollDO;
import com.isxxc.service.ShopEnrollService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.RandomUtils;
import cc.likq.util.TimeUtils;

/**
 * <p>门店登录 前端控制器
 * 注: 业务取消
 * </p>
 *
 * @author likq
 * @since 2017-11-27
 */
@RestController
@RequestMapping("/shopEnroll")
public class ShopEnrollController {

    @Resource
    private ShopEnrollService shopEnrollService;

    /***
     * 保存登记
     * @param request
     * @param shopEnrollDO
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(HttpServletRequest request, ShopEnrollDO shopEnrollDO) {
        return shopEnrollService.save(shopEnrollDO);
    }

    /***
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Result uploadImage(MultipartFile file) {
        String fileName = null;
        if (file != null && !file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                byte[] bytes = file.getBytes();
                fileName = TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED_SSS) + RandomUtils.generateString(5) + suffix;
                File filePath = new File(CommonFolderConstant.getImageTempPath(fileName));
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(filePath));
                buffStream.write(bytes);
                buffStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ResponseResult.paramNotNull("内容不能为空");
        }
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("key", fileName);
        resultMap.put("url", CommonFolderConstant.getImageTempWebPath(fileName));
        return ResponseResult.success(resultMap);
    }

}
