package com.isxxc.domain.dto;

import com.isxxc.domain.entity.UserAccountDO;

import java.util.Date;


/**
 * @author 
 */
public class UserAccountDTO extends UserAccountDO {
    /***
     * 手机注册用户统计数
     */
    private Integer countUser;
    /***
     * 查询 开始时间字段
     */
    private String startTime;

    /***
     * 查询 结束时间字段
     */
    private String endTime;

    public Integer getJishu() {
        return jishu;
    }

    public void setJishu(Integer jishu) {
        this.jishu = jishu;
    }

    private Integer jishu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public UserAccountDTO(Integer countUser, String startTime, String endTime, Integer jishu, String name) {
        this.countUser = countUser;
        this.startTime = startTime;
        this.endTime = endTime;
        this.jishu = jishu;
        this.name = name;
    }

    public UserAccountDTO() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }
}
