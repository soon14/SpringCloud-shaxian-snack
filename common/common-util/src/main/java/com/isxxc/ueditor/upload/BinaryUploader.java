package com.isxxc.ueditor.upload;

import com.isxxc.ueditor.PathFormat;
import com.isxxc.ueditor.define.AppInfo;
import com.isxxc.ueditor.define.BaseState;
import com.isxxc.ueditor.define.FileType;
import com.isxxc.ueditor.define.State;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("upfile");
        try {
            if (file == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = file.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(file.getOriginalFilename());

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);
            String physicalPath = conf.get("rootPath") + savePath;

            InputStream is = file.getInputStream();
            State storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (IOException e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
