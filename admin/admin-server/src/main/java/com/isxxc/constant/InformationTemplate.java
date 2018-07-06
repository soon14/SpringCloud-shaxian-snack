package com.isxxc.constant;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 泥水佬
 * @date 2017/12/28
 */
@Component
public class InformationTemplate {

    @Resource
    private ResourceLoader resourceLoader;

    private static String indexHtmlStr;

    private static String indexJsStr;

    private static String indexCssStr;


    @PostConstruct
    public void init() throws IOException {
        this.setIndexHtmlStr(new BufferedReader(new InputStreamReader(this.resourceLoader.getResource("classpath:informationTemplate/index.html").getInputStream())).lines().collect(Collectors.joining(System.lineSeparator())));
        this.setIndexJsStr(new BufferedReader(new InputStreamReader(this.resourceLoader.getResource("classpath:informationTemplate/index.js").getInputStream())).lines().collect(Collectors.joining(System.lineSeparator())));
        this.setIndexCssStr(new BufferedReader(new InputStreamReader(this.resourceLoader.getResource("classpath:informationTemplate/index.css").getInputStream())).lines().collect(Collectors.joining(System.lineSeparator())));
    }

    public static String getIndexHtmlStr() {
        return indexHtmlStr;
    }

    public static void setIndexHtmlStr(String indexHtmlStr) {
        InformationTemplate.indexHtmlStr = indexHtmlStr;
    }

    public static String getIndexJsStr() {
        return indexJsStr;
    }

    public static void setIndexJsStr(String indexJsStr) {
        InformationTemplate.indexJsStr = indexJsStr;
    }

    public static String getIndexCssStr() {
        return indexCssStr;
    }

    public static void setIndexCssStr(String indexCssStr) {
        InformationTemplate.indexCssStr = indexCssStr;
    }
}
