package com.isxxc.client;

import com.isxxc.constant.CommonFolderConstant;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.RandomUtils;
import cc.likq.util.TimeUtils;

/**
 * @author 泥水佬
 * @date 2018/1/8
 */
@RestController
public class UploadFileClientImpl implements UploadFileClient {

    @Override
    public Result uploadFile(MultipartFile file) {
        String fileName;
        if (file != null && !file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                fileName = TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED_SSS) + RandomUtils.generateString(5) + suffix;
                File filePath = new File(CommonFolderConstant.getImageTempPath(fileName));
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(filePath));
                buffStream.write(file.getBytes());
                buffStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseResult.serverError();
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
