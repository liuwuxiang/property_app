package com.springmvc.entity;

import java.util.Date;

public class ConsumptionIntegralExpenditure {
    private Integer id;
    private String name;
    private Date expenditure_date;
    private Double expenditure_amount;
    private Double expenditure_after_balance;
    private Integer user_id;
    private Integer expenditure_type;

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

    public Date getExpenditure_date() {
        return expenditure_date;
    }

    public void setExpenditure_date(Date expenditure_date) {
        this.expenditure_date = expenditure_date;
    }

    public Double getExpenditure_amount() {
        return expenditure_amount;
    }

    public void setExpenditure_amount(Double expenditure_amount) {
        this.expenditure_amount = expenditure_amount;
    }

    public Double getExpenditure_after_balance() {
        return expenditure_after_balance;
    }

    public void setExpenditure_after_balance(Double expenditure_after_balance) {
        this.expenditure_after_balance = expenditure_after_balance;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getExpenditure_type() {
        return expenditure_type;
    }

    public void setExpenditure_type(Integer expenditure_type) {
        this.expenditure_type = expenditure_type;
    }
}
