package cc.likq.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class PayCommonUtil {
    /**
     * 定义签名，微信根据参数字段的ASCII码值进行排序 加密签名,故使用SortMap进行参数排序
     */
    public static String createSign(SortedMap<String, String> parameters, String partnerKey) {
        StringBuffer sb = new StringBuffer();
        for (String k : parameters.keySet()) {
            Object v = parameters.get(k);
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        // 最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
        sb.append("key=" + partnerKey);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }

    /**
     * 将封装好的参数转换成Xml格式类型的字符串
     */
    public static String getRequestXml(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (String k : parameters.keySet()) {
            String v = parameters.get(k);
            if (!"sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("<sign>" + parameters.get("sign") + "</sign>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     */
    public static SortedMap<String, String> doXMLParse(String strxml) {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        SortedMap<String, String> paramsMap = new TreeMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(strxml);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        Element root = doc.getRootElement();
        List<Element> listElement = root.elements();
        for (Element element : listElement) {
            String k = element.getName();
            String v = "";
            List<Element> children = element.elements();
            if (children.isEmpty()) {
                v = element.getTextTrim();
            } else {
                v = getChildrenText(children);
            }

            paramsMap.put(k, v);
        }
        return paramsMap;
    }

    /**
     * 获取子结点的xml
     *
     * @return String
     */
    public static String getChildrenText(List<Element> children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            for (Element e : children) {
                String name = e.getName();
                String value = e.getTextTrim();
                List<Element> list = e.elements();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(children));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }
}
