package com.isxxc.domain.dto;

/**
 * 通用异常
 *
 * @author 泥水佬
 * @date 2018/2/4
 */
public class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
    }
}