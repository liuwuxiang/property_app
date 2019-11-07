package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/11/2 16:50
 * @Description:用户赠送积分余额明细实体类
 */
public class WnkSendIntegralDetailUser {
    private Integer id;
    private String name;
    private Date income_date;
    private Double income_amount;
    private Integer user_id;
    private Integer type;
    private Integer business_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIncome_date() {
        return income_date;
    }

    public void setIncome_date(Date income_date) {
        this.income_date = income_date;
    }

    public Double getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(Double income_amount) {
        this.income_amount = income_amount;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }
}
