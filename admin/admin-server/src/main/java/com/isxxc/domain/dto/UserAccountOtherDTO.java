package com.isxxc.domain.dto;


/**
 * @author 吴坚
 * @date 2018/05/30
 */
public class UserAccountOtherDTO {
    /***
     * 总和
     */
    private Integer sum;
    /***
     * 时间
     */
    private String date;
    /***
     * 姓名
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserAccountOtherDTO() {
    }

    public UserAccountOtherDTO(Integer sum, String date, String name) {
        this.sum = sum;
        this.date = date;
        this.name = name;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
