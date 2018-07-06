package cc.likq.util;

/**
 * HTTP 请求工具类, 基于 JAVA-8, HttpClient-4.5.2 开发
 *
 * @author fanlychie
 */
public final class HttpUtilResponse {

    /***
     * 状态码
     */
    private int code;
    /***
     * 响应内容
     */
    private String content;
    /***
     * 状态描述
     */
    private String msg;

    public HttpUtilResponse() {
    }

    public HttpUtilResponse(Integer code, String content, String msg) {
        this.code = code;
        this.content = content;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}