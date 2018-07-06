package com.isxxc.config;

import com.alibaba.fastjson.JSON;
import com.isxxc.constant.CommonRedisKeyConstant;
import com.isxxc.domain.dto.UserInfoDTO;
import com.isxxc.service.UserAuthServer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * @author 泥水佬
 * @date 2017/12/14
 */
@Component
@ConfigurationProperties
public class AccessFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /***
     * 商家后台服务
     */
    final static String storeCms = "store-cms";

    @Resource
    private UserAuthServer userAuthServer;

    @Resource
    private RedisClient redisClient;

    /***
     * 前置过滤器
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /***
     *  优先级，数字越大，优先级越低
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;
    }

    /***
     * 是否执行该过滤器，true代表需要过滤
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
       /* //检查是否需要鉴权的URL
        String uri = request.getRequestURI();
        uri = uri.substring(1, uri.length());
        //暂时关闭鉴权
        return !FunctionUrlConstant.ignoreUrl.contains(uri);*/
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //临时作数据转换
        //把UserCode转换为UserId
        String userCode = request.getParameter("userCode");
        //会员信息
        UserInfoDTO userInfoDTO = null;
        //获取会员信息
        if (StringUtils.isNotBlank(userCode)) {
            String userInfoJson = redisClient.get(CommonRedisKeyConstant.getUserInfoKey(userCode));
            userInfoDTO = StringUtils.isNotBlank(userInfoJson) ? JSON.parseObject(userInfoJson, UserInfoDTO.class) : null;
            if (userInfoDTO != null) {
                requestContext.getRequestQueryParams().put("userId", Collections.singletonList(userInfoDTO.getId().toString()));
            }
        }

        //判断当前请求是否有权限访问商店后台
        //请求服务名称
        final String serviceId = (String) requestContext.get("proxy");
        String requestURI = (String) requestContext.get("requestURI");
        if (storeCms.equals(serviceId) && (!"ueditor".equals(requestURI.split("/")[1]))) {
            if (userInfoDTO == null) {
                return responseErrorResult(ResponseResult.failResult(ResultCodeEnum.UNAUTHORIZED, "当前未登录或登录已失效"));
            }
            if (userInfoDTO.getStoreId() == null) {
                return responseErrorResult(ResponseResult.failResult(ResultCodeEnum.FORBIDDEN, "无该操作权限"));
            }
            requestContext.getRequestQueryParams().put("storeId", Collections.singletonList(userInfoDTO.getStoreId().toString()));
        }


       /* logger.info("***************************Token拦截校验******************************");
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            return responseErrorResult(ResponseResult.paramNotNull("Token 不能为空"));
        }
        String userCode = request.getParameter("userCode");
        if (StringUtils.isBlank(userCode)) {
            return responseErrorResult(ResponseResult.paramNotNull("userCode 不能为空"));
        }
        String userAgent = request.getParameter("userAgent");
        if (StringUtils.isBlank(userAgent) || !UserAgentEnum.isInclude(userAgent)) {
            return responseErrorResult(ResponseResult.paramNotNull("userAgent 终端类型错误"));
        }

        if (!checkToken(token, userCode, userAgent)) {
            return null;
        }*/

        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }

    /***
     * Token校验
     */
    private boolean checkToken(String toekn, String userCode, String userAgent) {
        Result result = userAuthServer.checkToken(toekn, userCode, userAgent);
        System.out.println(JSON.toJSONString(result));
        if (!ResponseResult.success().equals(result)) {
            responseErrorResult(result);
            return false;
        }
        return true;
    }

    /***
     * 结果响应
     */
    private Object responseErrorResult(Result result) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //不进行路由
        requestContext.setSendZuulResponse(false);
        requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        requestContext.setResponseStatusCode(result.getCode());
        requestContext.setResponseBody(JSON.toJSONString(result));
        requestContext.set("isSuccess", false);
        return null;
    }
}