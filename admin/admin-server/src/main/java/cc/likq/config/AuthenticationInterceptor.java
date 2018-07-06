package cc.likq.config;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.likq.base.domain.dto.SystemAdminTokenDTO;
import cc.likq.constant.RedisKeyConstant;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * @author likq
 */
@Component
@ConfigurationProperties(prefix = "authenticationInterceptor")
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);


    /***
     * 拦截器排除拦截路径(类)
     */
    private List<String> excludeClassNameList = new ArrayList<>();
    /***
     * 拦截器排除拦截路径(方法)
     */
    private List<String> excludeClassMethodList = new ArrayList<>();

    @Resource
    private RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("**********Controller请求前拦截**********");

        //管理员信息校验
//        authentication(request, response, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        System.out.println("**********Controller请求后拦截**********");
        //请求完毕，对线程共享信息移除
        CurrentAdminManager.close();
    }

    /***
     * 登录认证
     * @param request
     * @param response
     * @return
     */
    private boolean authentication(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (StringUtils.isBlank(request.getParameter("adminId"))) {
            returnJson(response, ResponseResult.paramNotNull("adminId不能为空"));
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("token"))) {
            returnJson(response, ResponseResult.paramNotNull("token不能为空"));
            return false;
        }
        //管理员ID
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        //会话密钥
        String token = request.getParameter("token");

        //Redis中获取会员信息
        String adminTokenKey = RedisKeyConstant.getAdminTokenKey(adminId);
        String adminInfoJson = redisClient.get(adminTokenKey);

        //校对认证信息
        if (StringUtils.isBlank(adminInfoJson)) {
            return authFail(response);
        }
        SystemAdminTokenDTO systemAdminTokenDTO = JSON.parseObject(adminInfoJson, SystemAdminTokenDTO.class);
        if (!token.trim().equals(systemAdminTokenDTO.getToken())) {
            return authFail(response);
        }

        //检查权限
        checkPermission(response, handler);

        //存储信息到当前线程进行共享
        CurrentAdminManager.setAdminInfo(systemAdminTokenDTO);

        //每次校验登录信息成功，重置Token时间
        redisClient.getResource().expire(adminTokenKey, 1800);

        return true;
    }

    /***
     * 权限检查
     * @param response
     * @param handler
     * @return
     */
    private boolean checkPermission(HttpServletResponse response, Object handler) {
        String classMethod;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String className = handlerMethod.getBeanType().getName();
            classMethod = className + "#" + handlerMethod.getMethod().getName();
            //排除不拦截URL
            if (excludeClassNameList.contains(className) || excludeClassMethodList.contains(classMethod)) {
                return true;
            }
        } else {
            returnJson(response, ResponseResult.failResult(ResultCodeEnum.METHOD_NOT_ALLOWED));
            return false;
        }

        return true;
    }

    /***
     * 会话认证失败
     * @param response
     * @return
     */
    public boolean authFail(HttpServletResponse response) {
        returnJson(response, ResponseResult.other(ResultCodeEnum.BAD_REQUEST.code, "会话认证失败,用重新登录"));
        return false;
    }

    /***
     * 结果刷出
     * @param response
     * @param result
     */
    private void returnJson(HttpServletResponse response, Result result) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSON(result));
        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public List<String> getExcludeClassNameList() {
        return excludeClassNameList;
    }

    public void setExcludeClassNameList(List<String> excludeClassNameList) {
        this.excludeClassNameList = excludeClassNameList;
    }

    public List<String> getExcludeClassMethodList() {
        return excludeClassMethodList;
    }

    public void setExcludeClassMethodList(List<String> excludeClassMethodList) {
        this.excludeClassMethodList = excludeClassMethodList;
    }
}
