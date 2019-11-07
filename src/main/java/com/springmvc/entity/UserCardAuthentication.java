package com.springmvc.entity;

import java.util.Date;

public class UserCardAuthentication {

    private Integer id;
    private String card_number;
    private String mobile;
    private String real_name;
    private Integer user_id;
    private Date submit_date;
    private Date authentication_finish_date;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(Date submit_date) {
        this.submit_date = submit_date;
    }

    public Date getAuthentication_finish_date() {
        return authentication_finish_date;
    }

    public void setAuthentication_finish_date(Date authentication_finish_date) {
        this.authentication_finish_date = authentication_finish_date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
