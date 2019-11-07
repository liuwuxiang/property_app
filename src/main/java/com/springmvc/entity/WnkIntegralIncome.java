package com.springmvc.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 杨新杰
 * @Date 2018/10/15 14:37
 */
public class WnkIntegralIncome {
    private Integer id;
    private String name;
    private Timestamp income_date;
    private Double income_amount;
    private Double incomeAfter_balance;
    private Integer user_id;
    private Integer income_type;
    private Integer business_id;


    @Override
    public String toString() {
        return "WnkIntegralIncome{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", income_date=" + income_date +
                ", income_amount=" + income_amount +
                ", incomeAfter_balance=" + incomeAfter_balance +
                ", user_id=" + user_id +
                ", income_type=" + income_type +
                ", business_id=" + business_id +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WnkIntegralIncome that = (WnkIntegralIncome) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(income_date, that.income_date) &&
                Objects.equals(income_amount, that.income_amount) &&
                Objects.equals(incomeAfter_balance, that.incomeAfter_balance) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(income_type, that.income_type) &&
                Objects.equals(business_id, that.business_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, income_date, income_amount, incomeAfter_balance, user_id, income_type, business_id);
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

    public Timestamp getIncome_date() {
        return income_date;
    }

    public void setIncome_date(Timestamp income_date) {
        this.income_date = income_date;
    }

    public Double getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(Double income_amount) {
        this.income_amount = income_amount;
    }

    public Double getIncomeAfter_balance() {
        return incomeAfter_balance;
    }

    public void setIncomeAfter_balance(Double incomeAfter_balance) {
        this.incomeAfter_balance = incomeAfter_balance;
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

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }
}
