package cc.likq.result;

import java.util.Objects;

/**
 * 响应状态类
 *
 * @author likq
 */
public class Result {
    /***
     * 状态码
     */
    private int code;
    /***
     * 状态描述
     */
    private String msg;
    /***
     * 响应结果内容
     */
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return this.code == result.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
