package com.isxxc.constant;

/**
 * 商品通用状态
 *
 * @author 泥水佬
 * @date 2018/1/12
 */
public class ProductStateEnum {

    /***
     * 运费模版类型
     */
    public enum FreightTemplate {
        /***
         * 统一运费
         */
        UNIFY(0),
        /***
         * 自定义
         */
        CUSTOM(1);

        public int type;

        FreightTemplate(int code) {
            this.type = code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    /***
     * 上下架状态
     */
    public enum IsShelves {
        /***
         * 下架
         */
        OFF(0),
        /***
         * 上架
         */
        ON(1);
        public int code;

        IsShelves(int code) {
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
     * 价格定义类型
     */
    public enum PriceType {
        /***
         * 单个
         */
        SINGLE(0),
        /***
         * 多个
         */
        MULTI(1);

        PriceType(int type) {
            this.type = type;
        }

        public int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
