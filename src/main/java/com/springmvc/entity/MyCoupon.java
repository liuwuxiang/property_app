package com.springmvc.entity;

import java.util.Date;

public class MyCoupon {
    private Integer id;
    private String coupon_name;
    private Date term_validity;
    private Integer consumption_amount;
    private Integer coupon_amount;
    private Integer mode_consumption;
    private String instructions;
    private Integer user_id;
    private Date get_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public Date getTerm_validity() {
        return term_validity;
    }

    public void setTerm_validity(Date term_validity) {
        this.term_validity = term_validity;
    }

    public Integer getConsumption_amount() {
        return consumption_amount;
    }

    public void setConsumption_amount(Integer consumption_amount) {
        this.consumption_amount = consumption_amount;
    }

    public Integer getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(Integer coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public Integer getMode_consumption() {
        return mode_consumption;
    }

    public void setMode_consumption(Integer mode_consumption) {
        this.mode_consumption = mode_consumption;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getGet_time() {
        return get_time;
    }

    public void setGet_time(Date get_time) {
        this.get_time = get_time;
    }
}
