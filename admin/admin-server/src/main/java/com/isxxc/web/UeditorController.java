package com.isxxc.web;

import com.isxxc.ueditor.ActionEnter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 富文本管理
 * @author 泥水佬
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController {

    @Resource
    private ActionEnter actionEnter;

    @RequestMapping("exec")
    public void exe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = actionEnter.exec(request);
        response.getWriter().write(result);
    }

}

