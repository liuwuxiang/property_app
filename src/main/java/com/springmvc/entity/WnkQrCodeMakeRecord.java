package com.springmvc.entity;

import java.util.Date;

public class WnkQrCodeMakeRecord {
    private Integer id;
    private Integer business_id;
    private Integer user_id;
    private Date make_time;
    private Integer business_type_id;
    private String order_no;
    private Integer make_number;
    private Integer commodity_id;
    private String order_qrcode;
    private Integer state;
    private Date line_order_date;
    private Integer guige_id;

    public Integer getGuige_id() {
        return guige_id;
    }

    public void setGuige_id(Integer guige_id) {
        this.guige_id = guige_id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getOrder_qrcode() {
        return order_qrcode;
    }

    public void setOrder_qrcode(String order_qrcode) {
        this.order_qrcode = order_qrcode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getLine_order_date() {
        return line_order_date;
    }

    public void setLine_order_date(Date line_order_date) {
        this.line_order_date = line_order_date;
    }

    public Integer getMake_number() {
        return make_number;
    }

    public void setMake_number(Integer make_number) {
        this.make_number = make_number;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getMake_time() {
        return make_time;
    }

    public void setMake_time(Date make_time) {
        this.make_time = make_time;
    }
}
