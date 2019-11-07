package com.springmvc.entity;

import java.util.Date;

public class RechargeOrder {
    private Integer id;
    private Integer user_id;
    private Double recharge_amount;
    private Integer recharge_type;
    private Integer pay_type;
    private Integer state;
    private Date recharge_time;
    private String system_order_no;
    private String pay_system_order_no;
    private Date pay_time;
    private Double integral_number;

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

    public Double getRecharge_amount() {
        return recharge_amount;
    }

    public void setRecharge_amount(Double recharge_amount) {
        this.recharge_amount = recharge_amount;
    }

    public Integer getRecharge_type() {
        return recharge_type;
    }

    public void setRecharge_type(Integer recharge_type) {
        this.recharge_type = recharge_type;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getRecharge_time() {
        return recharge_time;
    }

    public void setRecharge_time(Date recharge_time) {
        this.recharge_time = recharge_time;
    }

    public String getSystem_order_no() {
        return system_order_no;
    }

    public void setSystem_order_no(String system_order_no) {
        this.system_order_no = system_order_no;
    }

    public String getPay_system_order_no() {
        return pay_system_order_no;
    }

    public void setPay_system_order_no(String pay_system_order_no) {
        this.pay_system_order_no = pay_system_order_no;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Double getIntegral_number() {
        return integral_number;
    }

    public void setIntegral_number(Double integral_number) {
        this.integral_number = integral_number;
    }
}
