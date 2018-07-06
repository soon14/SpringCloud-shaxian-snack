package com.isxxc.config;

import com.alibaba.fastjson.JSON;
import com.isxxc.constant.FunctionUrlConstant;
import com.isxxc.service.UserFunctionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.Resource;

/**
 * 初始化功能菜单
 *
 * @author 泥水佬
 * @date 2017/12/25
 */
@Component
public class InitFunctionUrl implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Resource
    private UserFunctionService userFunctionService;

    @Override
    public void run(String... strings) throws Exception {
        List<String> ignoreUrl = userFunctionService.getIgnoreUrl();
        FunctionUrlConstant.setIgnoreUrl(ignoreUrl);
        logger.info("***********************初始化排除鉴权菜单***************************");
        logger.info(JSON.toJSONString(ignoreUrl));
        logger.info("******************************************************************");
    }
}
