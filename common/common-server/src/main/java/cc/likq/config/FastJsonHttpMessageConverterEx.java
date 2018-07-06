package cc.likq.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 泥水佬
 * @date 2017/6/5
 */
public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {

    public FastJsonHttpMessageConverterEx() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 自定义时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 正常转换 null 值
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        // 正常转换 null 值
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        this.setSupportedMediaTypes(supportedMediaTypes);
        this.setFastJsonConfig(fastJsonConfig);
    }

    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    @Override
    protected boolean supports(Class<?> clazz) {
        return super.supports(clazz);
    }
}
