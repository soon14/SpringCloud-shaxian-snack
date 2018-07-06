package cc.likq.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 泥水佬
 */
public class StringToDateConverterEx implements Converter<String, Date> {
    final static List<String> formatSet = new ArrayList<String>() {{
        add("yyyy-MM-dd'T'HH:mm:ss.SSS");
        add("yyyy-MM-dd HH:mm:ss.SSS");
        add("yyyy-MM-dd'T'HH:mm:ss");
        add("yyyy-MM-dd HH:mm:ss");
        add("yyyy-MM-dd HH:mm");
        add("yyyy-MM-dd");
        add("MM-dd");
    }};

    @Override
    public Date convert(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }
        dateTime = dateTime.replaceAll("\\.", "\\-").replaceAll("\\/", "\\-").trim();
        formatSet.forEach(format -> {

        });
        for (String fomat : formatSet) {
            try {
                return new SimpleDateFormat(fomat).parse(dateTime);
            } catch (ParseException e) {
                continue;
            }
        }
        return null;
    }
}
