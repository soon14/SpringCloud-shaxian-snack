package cc.likq.result;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 结果响应类
 *
 * @author likq
 */
public class ResponseResult extends TransactionAspectSupport {
    private static final Logger logger = LoggerFactory.getLogger(ResponseResult.class);


    /**
     * 成功
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMsg(ResultCodeEnum.SUCCESS.msg);
        return result;
    }

    /***
     * 成功，带结果对象返回
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMsg(ResultCodeEnum.SUCCESS.msg);
        result.setData(data);
        return result;
    }

    public static Result successMsg(String msg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMsg(msg);
        return result;
    }

    /***
     * 失败,带状态及描述
     */
    public static Result failResult(ResultCodeEnum resultCode, String msg) {
        TransactionInfo info = currentTransactionInfo();
        if (info != null && info.getTransactionStatus() != null) {
            logger.warn("事务异常回滚: " + msg);
            info.getTransactionStatus().setRollbackOnly();
        }
        Result result = new Result();
        result.setCode(resultCode.code);
        result.setMsg(msg);
        return result;
    }

    /***
     * 失败,带状态、描述、内容
     */
    public static Result failResult(ResultCodeEnum resultCode, String msg, Object data) {
        TransactionInfo info = currentTransactionInfo();
        if (info != null && info.getTransactionStatus() != null) {
            logger.warn("事务异常回滚: " + msg);
            info.getTransactionStatus().setRollbackOnly();
        }
        Result result = new Result();
        result.setCode(resultCode.code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /***
     * 失败,带状态、描述、内容
     */
    public static Result failResult(ResultCodeEnum resultCode, String msg, Object data, boolean rollback) {
        if (rollback) {
            TransactionInfo info = currentTransactionInfo();
            if (info != null && info.getTransactionStatus() != null) {
                logger.warn("事务异常回滚: " + msg);
                info.getTransactionStatus().setRollbackOnly();
            }
        } else {
            logger.warn("异常,事务不回滚: " + msg);
        }
        Result result = new Result();
        result.setCode(resultCode.code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 失败,枚举状态
     */
    public static Result failResult(ResultCodeEnum resultCode) {
        TransactionInfo info = currentTransactionInfo();
        if (info != null && info.getTransactionStatus() != null) {
            logger.warn("事务异常回滚: " + resultCode.msg);
            info.getTransactionStatus().setRollbackOnly();
        }
        Result result = new Result();
        result.setCode(resultCode.code);
        result.setMsg(resultCode.msg);
        return result;

    }

    /***
     * 服务器异常
     */
    public static Result serverError() {
        TransactionInfo info = currentTransactionInfo();
        if (info != null && info.getTransactionStatus() != null) {
            logger.warn("事务异常回滚: " + ResultCodeEnum.SERVER_ERROR.msg);
            info.getTransactionStatus().setRollbackOnly();
        }
        Result result = new Result();
        result.setCode(ResultCodeEnum.SERVER_ERROR.code);
        result.setMsg(ResultCodeEnum.SERVER_ERROR.msg);
        return result;
    }

    /***
     * 参数不能为空
     */
    public static Result paramNotNull(String msg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.BAD_REQUEST.code);
        result.setMsg(msg);
        return result;
    }

    /***
     * 其它
     * @param code 状态码
     * @param msg 状态描述
     * @return 结果返回
     */
    public static Result other(int code, String msg) {
        TransactionInfo info = currentTransactionInfo();
        if (info != null && info.getTransactionStatus() != null) {
            logger.warn("事务异常回滚: " + msg);
            info.getTransactionStatus().setRollbackOnly();
        }
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
