package com.isxxc.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * @author 泥水佬
 * @date 2017/12/18
 */
@Component
public class ResponseBodyFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyFilter.class);

    /***
     * 过滤器
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /***
     *  优先级，数字越大，优先级越低
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
    }

    /***
     * 是否执行该过滤器，true代表需要过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulResponseHeader("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return null;
    }
}
