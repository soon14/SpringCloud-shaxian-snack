package com.isxxc.domain.dto;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 泥水佬
 * @date 2018/1/2
 */
public class Pager extends com.baomidou.mybatisplus.plugins.Page {
    private Map<String, Object> paramMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Pager putParam(String key, Object value) {
        if (MapUtils.isNotEmpty(paramMap)) {
            this.paramMap.put(key, value);
        } else {
            this.paramMap = new HashMap<String, Object>() {{
                put(key, value);
            }};
        }
        return this;
    }

}
