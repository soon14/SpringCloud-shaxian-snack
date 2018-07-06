package com.isxxc.constant;

/***
 * 状态静态枚举
 * @author 泥水佬
 */
public class CommonStateEnum {
    /***
     * 删除状态
     */
    public enum IsDeleted {
        /***
         * 未删除
         */
        NOT_DELETED(0),
        /***
         * 已删除
         */
        HAVE_DELETED(1);

        public int code;

        IsDeleted(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 禁用状态
     */
    public enum IsDisable {
        /***
         * 未禁用
         */
        NOT_DISABLE(0),
        /***
         * 已禁用
         */
        HAVE_DISABLE(1);

        public int code;

        IsDisable(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 发布状态
     */
    public enum PublishState {
        /***
         * 未发布
         */
        UNPUBLISHED(0),
        /***
         * 已发布
         */
        PUBLISHED(1),
        /***
         *下线
         */
        OFFLINE(2);
        public int code;

        PublishState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /**
     * 审核状态
     */
    public enum AuditState {
        /***
         * 未审核
         */
        UNREVIEWED(0),
        /***
         * 已通过
         */
        PASSED(1),
        /***
         * 已驳回
         */
        REJECT(2);
        public int code;

        AuditState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 审核状态日志
     */
    public enum AuditStateLog {
        /***
         * 已通过
         */
        PASSED(0),
        /***
         * 已驳回
         */
        REJECT(1);
        public int code;

        AuditStateLog(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static boolean isInclude(int code) {
            if (PASSED.code == code || REJECT.code == code) {
                return true;
            } else {
                return false;
            }
        }

    }

    /***
     * 是否推荐
     */
    public enum IsRecommend {
        /***
         * 不推荐
         */
        NO(0),
        /***
         * 推荐
         */
        YES(1);
        public int code;

        IsRecommend(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 是否显示
     */
    public enum IsShow {
        /***
         * 不显示
         */
        NO(0),
        /***
         * 显示
         */
        YES(1);
        public int code;

        IsShow(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 是否鉴权
     */
    public enum IsAuth {
        /***
         * 不鉴权
         */
        NO(0),
        /***
         * 鉴权
         */
        YES(1);
        public int code;

        IsAuth(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 是否成功
     */
    public enum IsSuccess {
        /***
         * 失败
         */
        FAIL(0),
        /***
         * 成功
         */
        SUCCEED(1);
        public int code;

        IsSuccess(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 是否完善
     */
    public enum IsComplete {
        /***
         * 待完善
         */
        WANE(0),
        /***
         * 已完善
         */
        FULL(1);

        IsComplete(int code) {
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

    /***
     * 是否默认
     */
    public enum IsDefault {
        /***
         * 否
         */
        NO(0),
        /***
         * 是
         */
        YES(1);
        public int code;

        IsDefault(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /***
     * 是否结算
     */
    public enum SettlementState {
        /***
         * 否
         */
        NO(0),
        /***
         * 是
         */
        YES(1);
        public int code;

        SettlementState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
