package com.springmvc.entity;

import java.util.Date;

public class SilverCoinDetailed {
    private Integer id;
    private String name;
    private Date record_date;
    private Integer transaction_amount;
    private Integer transaction_after_balance;
    private Integer income_expenditure_type;
    private Integer transaction_type;
    private Integer user_id;
    private Integer withdraw_state;
    private Integer service_charge;

    public Integer getService_charge() {
        return service_charge;
    }

    public void setService_charge(Integer service_charge) {
        this.service_charge = service_charge;
    }

    public Integer getWithdraw_state() {
        return withdraw_state;
    }

    public void setWithdraw_state(Integer withdraw_state) {
        this.withdraw_state = withdraw_state;
    }

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

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public Integer getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(Integer transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Integer getTransaction_after_balance() {
        return transaction_after_balance;
    }

    public void setTransaction_after_balance(Integer transaction_after_balance) {
        this.transaction_after_balance = transaction_after_balance;
    }

    public Integer getIncome_expenditure_type() {
        return income_expenditure_type;
    }

    public void setIncome_expenditure_type(Integer income_expenditure_type) {
        this.income_expenditure_type = income_expenditure_type;
    }

    public Integer getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Integer transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
