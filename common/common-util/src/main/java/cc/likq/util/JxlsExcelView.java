package cc.likq.util;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author NanZheng
 * @projectName shaxian
 * @since 2018/5/22
 */
public class JxlsExcelView extends AbstractView {
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";

    private String templatePath;
    private String exportFileName;

    /**
     * @param templatePath   模版相对于当前classpath路径
     * @param exportFileName 导出文件名
     */
    public JxlsExcelView(String templatePath, String exportFileName) {
        this.templatePath = templatePath;
        if (exportFileName != null) {
            try {
                exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        this.exportFileName = exportFileName;
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
//        Context context = new Context(model);
        response.setContentType(getContentType());
        response.setHeader("content-disposition",
                "attachment;filename=" + exportFileName + ".xlsx");
        ServletOutputStream os = response.getOutputStream();
        InputStream is = getClass().getClassLoader().getResourceAsStream(templatePath);
//        JxlsHelper.getInstance().processTemplate(is, os, context);
        JxlsUtils.exportExcel(is, os, model);
        //转换成excel并输出
//		XLSTransformer transformer = new XLSTransformer();
//		Workbook workbook = transformer.transformXLS(is, model);

        //将内容写入输出流并把缓存的内容全部发出去
//        workbook.write(os);
//        os.flush();
//        os.close();
//        is.close();
        is.close();
    }
}
