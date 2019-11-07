package com.springmvc.entity;

import java.util.Date;

public class UsersCredit {
    private Integer id;
    private Integer user_id;
    private String credit_name;
    private Date credit_date;
    private Integer credit_value;
    private Integer make_type;
    private Integer type;

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

    public String getCredit_name() {
        return credit_name;
    }

    public void setCredit_name(String credit_name) {
        this.credit_name = credit_name;
    }

    public Date getCredit_date() {
        return credit_date;
    }

    public void setCredit_date(Date credit_date) {
        this.credit_date = credit_date;
    }

    public Integer getCredit_value() {
        return credit_value;
    }

    public void setCredit_value(Integer credit_value) {
        this.credit_value = credit_value;
    }

    public Integer getMake_type() {
        return make_type;
    }

    public void setMake_type(Integer make_type) {
        this.make_type = make_type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
