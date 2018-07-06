package com.isxxc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * @author 泥水佬
 * @date 2017/12/14
 */
@RestController
public class BaseErrorController extends AbstractErrorController {

    @Autowired
    public BaseErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result error(HttpServletRequest request) {
        return ResponseResult.failResult(ResultCodeEnum.SERVER_ERROR, "网关服务异常,请联系管理员");
    }

}