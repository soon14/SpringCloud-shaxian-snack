package com.isxxc.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import cc.likq.result.Result;

/**
 * 文件上传
 *
 * @author 泥水佬
 * @date 2017/12/30
 */
@RequestMapping("uploadFileClient")
public interface UploadFileClient {

    /***
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result uploadFile(@RequestPart(value = "file") MultipartFile file);
}
