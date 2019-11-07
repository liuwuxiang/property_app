package com.springmvc.entity;

import java.util.Date;

public class ConsumptionIntegralIncome {
    private Integer id;
    private String name;
    private Date income_date;
    private Double income_amount;
    private Double income_after_balance;
    private Integer user_id;
    private Integer income_type;

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

    public Double getIncome_after_balance() {
        return income_after_balance;
    }

    public void setIncome_after_balance(Double income_after_balance) {
        this.income_after_balance = income_after_balance;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getIncome_type() {
        return income_type;
    }

    public void setIncome_type(Integer income_type) {
        this.income_type = income_type;
    }
}
