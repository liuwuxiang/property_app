package com.springmvc.entity;

import javax.persistence.*;

/**
 *
 * 万能卡订单信息扩展类 - 酒店类商家
 *
 * @author 杨新杰
 */
public class WnkOrdersExpandHotel {
    private int id;
    private Integer user_id;
    private Integer business_id;
    private Integer commodity_id;
    private Integer order_id;
    private String mobile;
    private Double general_integral_number;
    private Double send_integral_number;
    private Double coupon;
    private String register_people;
    private String register_time;
    private Double register_start_time_stamp;
    private Double register_end_time_stamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getGeneral_integral_number() {
        return general_integral_number;
    }

    public void setGeneral_integral_number(Double general_integral_number) {
        this.general_integral_number = general_integral_number;
    }

    public Double getSend_integral_number() {
        return send_integral_number;
    }

    public void setSend_integral_number(Double send_integral_number) {
        this.send_integral_number = send_integral_number;
    }

    public Double getCoupon() {
        return coupon;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public String getRegister_people() {
        return register_people;
    }

    public void setRegister_people(String register_people) {
        this.register_people = register_people;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public Double getRegister_start_time_stamp() {
        return register_start_time_stamp;
    }

    public void setRegister_start_time_stamp(Double register_start_time_stamp) {
        this.register_start_time_stamp = register_start_time_stamp;
    }

    public Double getRegister_end_time_stamp() {
        return register_end_time_stamp;
    }

    public void setRegister_end_time_stamp(Double register_end_time_stamp) {
        this.register_end_time_stamp = register_end_time_stamp;
    }
}
