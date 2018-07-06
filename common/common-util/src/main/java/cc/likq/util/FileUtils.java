package cc.likq.util;

import java.io.File;
import java.net.URL;

/**
 * @author likq
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    public static boolean cutFileStr(String source, String target) {
        File sourceFile = new File(source);
        try {
            moveFileToDirectory(sourceFile, new File(target), true);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean copyFileToDirectoryStr(String source, String target) {
        File sourceFile = new File(source);
        try {
            copyFileToDirectory(sourceFile, new File(target));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String copyFileByUrl(String httpUrl, String dirPath) throws Exception {
        String fileName = httpUrl.substring(httpUrl.lastIndexOf("/") + 1);
        URL url = new URL(httpUrl);
        copyURLToFile(url, new File(dirPath + fileName));
        return fileName;
    }

    /***
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean delete(String filePath) {
        return new File(filePath).delete();
    }

    /***
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean exists(String filePath) {
        return new File(filePath).exists();
    }
}
