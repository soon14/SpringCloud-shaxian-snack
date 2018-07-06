package cc.likq.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * 全局异常管理
 *
 * @author likq
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger();

    /***
     * 请求不能被处理，或参数错误
     */
    @ExceptionHandler({MethodArgumentNotValidException.class,})
    public Result processValidationError(MethodArgumentNotValidException ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Result responseResult = ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "客户端请求的语法错误，服务器无法理解");
        responseResult.setData(fieldErrors);
        return responseResult;
    }


    /***
     * 服务器已经理解请求，但是拒绝执行它。与401响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。
     * 如果这不是一个 HEAD 请求，而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。当然服务器也可以返回一个404响应，假如它不希望让客户端获得任何信息。
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result processAccessDeniedException(AccessDeniedException ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        return ResponseResult.failResult(ResultCodeEnum.FORBIDDEN, "服务器已经理解请求，但是拒绝执行它");
    }


    /***
     * 请求行中指定的请求方法不能被用于请求相应的资源。该响应必须返回一个Allow 头信息用以表示出当前资源能够接受的请求方法的列表。
     * 鉴于 PUT，DELETE 方法会对服务器上的资源进行写操作，因而绝大部分的网页服务器都不支持或者在默认配置下不允许上述请求方法，对于此类请求均会返回405错误。
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result processMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        return ResponseResult.failResult(ResultCodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 消息转换异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Result messageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "大侠,你传参数格式正确吗？否则找后台去...");
    }

    /***
     *参数绑定异常,多值错误时
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result validExceptionHandler(BindException ex, WebRequest request, HttpServletResponse response) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder responesMsg = new StringBuilder();
        responesMsg.append("[");
        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError fieldError = fieldErrors.get(i);
            responesMsg.append(fieldError.getField() + " -> " + fieldError.getRejectedValue());
            if (i != (fieldErrors.size() - 1)) {
                responesMsg.append(",");
            }
        }
        responesMsg.append("]");
        return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "大侠,你传参数类型正确吗？否则找后台去...提示:" + responesMsg);
    }

    /***
     *参数绑定异常,单值错误时
     */
    @ExceptionHandler({TypeMismatchException.class})
    public Result requestTypeMismatch(TypeMismatchException ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "大侠,你传参数类型正确吗？否则找后台去...提示: [" + ex.getRequiredType() + " -> " + ex.getValue() + " ]");
    }

    /***
     *找不到链接
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundException() {
        return ResponseResult.failResult(ResultCodeEnum.NO_FOUND, "大侠,你请求的链接不在地球上...!");
    }

    /**
     * 其它异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result processRuntimeException(Exception ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            return ResponseResult.other(responseStatus.value().value(), responseStatus.reason());
        } else {
            return ResponseResult.serverError();
        }
    }
}


