package cc.likq.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

/**
 * @author NanZheng
 * @projectName shaxian
 * @since 2018/5/22
 * jxls2 导入导出工具类
 */
public class JxlsUtils {
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = PoiTransformer.createInitialContext();
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);
        //获得配置
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        //设置静默模式，不报警告
        //evaluator.getJexlEngine().setSilent(true);
        //函数强制，自定义功能
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("utils", new JxlsUtils());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
        //必须要这个，否者表格函数统计会错乱
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
    }

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
        exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }

    public static void exportExcel(String templatePath, OutputStream os, Map<String, Object> model) throws Exception {
        File template = getTemplate(templatePath);
        if (template != null) {
            exportExcel(new FileInputStream(template), os, model);
        } else {
            throw new Exception("Excel 模板未找到。");
        }
    }

//    public static <T> void importExcel(Class<T> c, Map<String, Object> beans, MultipartFile file, String xmlPath) {
//        try {
//            InputStream inputXML = new BufferedInputStream(JxlsUtils.class.getClassLoader().getResourceAsStream(xmlPath));
//            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
//            InputStream inputXLS = new BufferedInputStream(file.getInputStream());
//            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
//            if (readStatus.isStatusOK()) {
//                System.out.println("jxls读取Excel成功！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        return videoInfoList;
//    }

    //获取jxls模版文件
    public static File getTemplate(String path) {
        File template = new File(path);
        if (template.exists()) {
            return template;
        }
        return null;
    }

    // 日期格式化
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 秒数转字符串  yyyy-MM-dd hh:mm:ss
     *
     * @param mills
     * @return
     */
    public String dateFmtByLong(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if (mills != 0) {
                Date date = new Date((long) mills * 1000L);
                String dateStr = sdf.format(date);
                return dateStr;
            } else {
                return "0";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 秒数转字符串
     *
     * @param mills
     * @param format
     * @return
     */
    public String dateFmtByLong(long mills, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            if (mills != 0) {
                Date date = new Date((long) mills * 1000L);
                String dateStr = sdf.format(date);
                return dateStr;
            } else {
                return "0";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 每期额度
     *
     * @return
     */
    public BigDecimal cycleMoney(BigDecimal surplusmoney, Integer cycle) {
        if (surplusmoney == null || cycle == null) {
            return null;
        }
        return surplusmoney.divide(BigDecimal.valueOf(cycle), 2, BigDecimal.ROUND_HALF_UP);
    }

    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }

    public String checkType(short type) {
        String result = null;
        switch (type) {
            case 0:
                result = "待审核";
                break;
            case 1:
                result = "审核通过";
                break;
            case 2:
                result = "不通过";
                break;

        }
        return result;
    }
}
