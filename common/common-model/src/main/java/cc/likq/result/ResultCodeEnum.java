package cc.likq.result;

/**
 * 响应状态码
 *
 * @author likq
 */
public enum ResultCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "操作成功"),
    /***
     * 服务异常
     */
    SERVER_ERROR(500, "服务异常,请联系管理员"),
    /***
     * 1、语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求。 　　
     * 2、请求参数有误。
     */
    BAD_REQUEST(400, "请求参数有误或语义有误，当前请求无法被服务器理解"),
    /***
     *
     * (未授权)
     * 当没有提供或提供了无效认证细节时。如果从浏览器使用API，也可以用来触发弹出一次认证请求
     */
    UNAUTHORIZED(401, "当没有提供或提供了无效认证细节"),
    /***
     * (禁止访问)
     * 当认证成功但是认证用户无权访问该资源时
     */
    FORBIDDEN(403, "当认证成功但是认证用户无权访问该资源"),
    /***
     * 资源链接不存在
     */
    NO_FOUND(404, "资源链接不存在"),
    /***
     *请求行中指定的请求方法不能被用于请求相应的资源
     */
    METHOD_NOT_ALLOWED(405, "请求行中指定的请求方法不能被用于请求相应的资源");
    public int code;
    public String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
