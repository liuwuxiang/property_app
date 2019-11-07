package com.springmvc.entity;

import java.util.Date;

public class WnkOrders {
    private Integer id;
    private Integer business_id;
    private Integer user_id;
    private String order_no;
    private Date submit_time;
    private Date pay_time;
    private Double amount;
    private Integer pay_way;
    private Integer state;
    private String order_qrcode;

    public String getOrder_qrcode() {
        return order_qrcode;
    }

    public void setOrder_qrcode(String order_qrcode) {
        this.order_qrcode = order_qrcode;
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

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPay_way() {
        return pay_way;
    }

    public void setPay_way(Integer pay_way) {
        this.pay_way = pay_way;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
