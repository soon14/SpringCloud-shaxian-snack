package com.isxxc.web;


import com.isxxc.service.feign.common.UploadFileService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import cc.likq.result.Result;

/**
 * 文件上传
 * <p> 前端控制器 </p>
 *
 * @author likq
 * @since 2017-11-28
 */
@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {

    @Resource
    private UploadFileService uploadFileService;

    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    public Result uploadImage(MultipartFile file) {
        return uploadFileService.uploadFile(file);
    }
}
