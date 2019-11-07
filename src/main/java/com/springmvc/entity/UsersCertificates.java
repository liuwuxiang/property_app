package com.springmvc.entity;

import java.util.Date;

public class UsersCertificates {
    private Integer id;
    private String number;
    private String certificate_photo_id;
    private Integer user_id;
    private String user_name;
    private Integer amount;
    private Integer option_equity;
    private Date create_time;
    private Integer record_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCertificate_photo_id() {
        return certificate_photo_id;
    }

    public void setCertificate_photo_id(String certificate_photo_id) {
        this.certificate_photo_id = certificate_photo_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOption_equity() {
        return option_equity;
    }

    public void setOption_equity(Integer option_equity) {
        this.option_equity = option_equity;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }
}
