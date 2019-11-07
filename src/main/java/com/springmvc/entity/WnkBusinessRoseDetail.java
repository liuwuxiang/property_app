package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 23:46
 * @Description:万能卡商家玫瑰明细实体类
 */
public class WnkBusinessRoseDetail {
    private Integer id;
    private Integer business_id;
    private String name;
    private Integer integral_number;
    private Date transactions_date;
    private Integer transactions_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIntegral_number() {
        return integral_number;
    }

    public void setIntegral_number(Integer integral_number) {
        this.integral_number = integral_number;
    }

    public Date getTransactions_date() {
        return transactions_date;
    }

    public void setTransactions_date(Date transactions_date) {
        this.transactions_date = transactions_date;
    }

    public Integer getTransactions_type() {
        return transactions_type;
    }

    public void setTransactions_type(Integer transactions_type) {
        this.transactions_type = transactions_type;
    }
}
