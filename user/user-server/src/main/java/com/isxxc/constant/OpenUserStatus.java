package com.isxxc.constant;

/**
 * 授权用户状态
 *
 * @author 泥水佬
 * @date 2018/1/22
 */
public enum OpenUserStatus {
    /***
     * 正常的
     */
    ENABLED(0),
    /***
     * 未绑定
     */
    NOT_BOUND(1),
    /***
     * 已禁用
     */
    DISABLE(2);

    OpenUserStatus(int code) {
        this.code = code;
    }

    public int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
