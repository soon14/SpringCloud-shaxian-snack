package cc.likq.config;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;


/**
 * @author likq
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Resource
    private RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("**********Controller请求前拦截**********");

        //管理员信息校验
        authentication(request, response, handler);
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
//       CurrentAdminManager.close();
    }

    /***
     * 登录认证
     * @param request
     * @param response
     * @return
     */
    private boolean authentication(HttpServletRequest request, HttpServletResponse response, Object handler) {

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
}
