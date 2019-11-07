package com.springmvc.entity;

import java.util.Date;

public class IntegralDetail {
    private Integer id;
    private String name;
    private Integer type;
    private Integer user_id;
    private Double transaction_integral_number;
    private Date transaction_date;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getTransaction_integral_number() {
        return transaction_integral_number;
    }

    public void setTransaction_integral_number(Double transaction_integral_number) {
        this.transaction_integral_number = transaction_integral_number;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }
}
