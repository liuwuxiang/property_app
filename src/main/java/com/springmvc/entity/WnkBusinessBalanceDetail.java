package com.springmvc.entity;

import java.util.Date;

public class WnkBusinessBalanceDetail {
    private Integer id;
    private Integer business_id;
    private String name;
    private Double transaction_amount;
    private Date join_time;
    private Double after_balance;
    private Integer type;
    private Integer state;
    private Integer is_withdraw;
    private Integer withdraw_id;

    public Integer getIs_withdraw() {
        return is_withdraw;
    }

    public void setIs_withdraw(Integer is_withdraw) {
        this.is_withdraw = is_withdraw;
    }

    public Integer getWithdraw_id() {
        return withdraw_id;
    }

    public void setWithdraw_id(Integer withdraw_id) {
        this.withdraw_id = withdraw_id;
    }

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

    public Double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(Double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public Double getAfter_balance() {
        return after_balance;
    }

    public void setAfter_balance(Double after_balance) {
        this.after_balance = after_balance;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
