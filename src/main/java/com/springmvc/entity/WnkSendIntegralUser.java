package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/11/2 16:07
 * @Description:商家赠送给用户的积分余额(用于记录用户在某个商家处“赠送积分”的余额)实体类
 */
public class WnkSendIntegralUser {
    private Integer id;
    private Integer user_id;
    private Integer business_id;
    private Double integral;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }
}
